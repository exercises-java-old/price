package se.lexicon.price.api.test.integration;

import com.google.common.collect.Sets;
import com.so4it.api.interceptor.request.RequestContextClientInterceptor;
import com.so4it.api.interceptor.request.RequestContextServerInterceptor;
import com.so4it.api.test.common.ApiFrameworkBootstrapTestRule;
import com.so4it.api.test.common.ApiFrameworkCommonTest;
import com.so4it.api.test.common.SatisfiedWhenClientConnected;
import com.so4it.common.bean.BeanContext;
import com.so4it.test.category.IntegrationTest;
import com.so4it.test.common.Assert;
import com.so4it.test.common.probe.Poller;
import com.so4it.test.gs.rule.ClearGigaSpaceTestRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.RuleChain;
import org.openspaces.core.GigaSpace;
import se.lexicon.price.Money;
import se.lexicon.price.api.client.PriceApiClient;
import se.lexicon.price.api.client.PriceApiProvider;
import se.lexicon.price.component.domain.CreatePriceBalanceRequest;
import se.lexicon.price.component.domain.PriceBalance;
import se.lexicon.price.component.domain.PriceTransaction;
import se.lexicon.price.component.service.PriceComponentService;
import se.lexicon.price.component.test.common.domain.MoneyTestBuilder;
import se.lexicon.price.component.test.common.domain.OrderDealTestBuilder;
import se.lexicon.price.component.test.common.domain.PriceTestBuilder;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
@Category(IntegrationTest.class)
public class PriceApiClientIntegrationTest {

    @ClassRule
    public static final RuleChain SUITE_RULE_CHAIN = PriceApiIntegrationTestSuite.SUITE_RULE_CHAIN;


    @Rule
    public ClearGigaSpaceTestRule clearGigaSpaceTestRule = new ClearGigaSpaceTestRule(PriceApiIntegrationTestSuite.getComponentRule().getBean(GigaSpace.class));




    private static final BeanContext BEAN_CONTEXT = ApiFrameworkCommonTest.createClientBeanContext(PriceApiIntegrationTestSuite.DYNAMIC_CONFIGURATION);



    @Rule
    public ApiFrameworkBootstrapTestRule apiFrameworkBootstrapTestRule = ApiFrameworkBootstrapTestRule.builder()
            .withBeanContext(BEAN_CONTEXT)
            .withDynamicConfiguration(PriceApiIntegrationTestSuite.DYNAMIC_CONFIGURATION)
            .withApiRegistryClient(PriceApiIntegrationTestSuite.API_REGISTRY)
            .withImports(PriceApiProvider.class)
            .withExports()
            .withClientInterceptors(new RequestContextClientInterceptor())
            .withServerInterceptors(new RequestContextServerInterceptor())
            .build();



    @Test
    public void testPlaceMarketPrice() throws InterruptedException {
        PriceApiClient priceApiClient = BEAN_CONTEXT.getBean(PriceApiClient.class);
        PriceComponentService priceComponentService = PriceApiIntegrationTestSuite.getComponentRule().getBean(PriceComponentService.class);
        Poller.pollAndCheck(SatisfiedWhenClientConnected.create(priceApiClient));

        priceComponentService.createOrderDeal(OrderDealTestBuilder.builder()
                .withInstrument("inst2")
                .withPrice(MoneyTestBuilder.builder()
                        .withAmount(BigDecimal.valueOf(500d)).build()).build());

        priceComponentService.createOrderDeal(OrderDealTestBuilder.builder()
                .withInstrument("inst1")
                .withPrice(MoneyTestBuilder.builder()
                        .withAmount(BigDecimal.valueOf(300d)).build()).build());

        priceComponentService.createOrderDeal(OrderDealTestBuilder.builder()
                .withInstrument("inst1")
                .withPrice(MoneyTestBuilder.builder()
                        .withAmount(BigDecimal.valueOf(500d)).build()).build());


        Money price = priceApiClient.placeMarketPrice("inst1");

        Assert.assertEquals(400f,price.getAmount());
    }

}