package se.lexicon.price.api.test.integration;

import com.google.common.collect.Sets;
import se.lexicon.price.api.client.PriceApiClient;
import se.lexicon.price.api.client.PriceApiProvider;
import se.lexicon.price.component.domain.PriceBalance;
import se.lexicon.price.component.domain.PriceTransaction;
import se.lexicon.price.component.domain.CreatePriceBalanceRequest;
import se.lexicon.price.component.test.common.domain.BalanceTestBuilder;
import se.lexicon.price.component.test.common.domain.CreatePriceTransactionRequestTestBuilder;
import com.so4it.api.interceptor.request.RequestContextClientInterceptor;
import com.so4it.api.interceptor.request.RequestContextServerInterceptor;
import com.so4it.api.test.common.ApiFrameworkBootstrapTestRule;
import com.so4it.api.test.common.ApiFrameworkCommonTest;
import com.so4it.api.test.common.SatisfiedWhenClientConnected;
import com.so4it.common.bean.BeanContext;
import com.so4it.test.category.IntegrationTest;
import com.so4it.test.common.probe.Poller;
import com.so4it.test.gs.rule.ClearGigaSpaceTestRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.RuleChain;
import org.openspaces.core.GigaSpace;

import java.time.Instant;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
@Category(IntegrationTest.class)
public class PriceApiClientClientIntegrationTest {

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
    public void testCreatingPriceBalance() throws Exception {
        PriceApiClient priceApiClient = BEAN_CONTEXT.getBean(PriceApiClient.class);
        Poller.pollAndCheck(SatisfiedWhenClientConnected.create(priceApiClient));

        PriceBalance priceBalanceOne = priceApiClient.createPriceBalance(CreatePriceBalanceRequest.builder()
                .withArrangementId("1")
                .withBatchId("aaa")
                .withInsertionTimestamp(Instant.parse("2019-06-27T09:00:00.000Z"))
                .withBalances(Sets.newHashSet(BalanceTestBuilder.builder().build())).build());
        PriceBalance priceBalanceTwo = priceApiClient.createPriceBalance(CreatePriceBalanceRequest.builder()
                .withArrangementId("1")
                .withBatchId("bbb")
                .withInsertionTimestamp(Instant.parse("2019-06-27T10:00:00.000Z"))
                .withBalances(Sets.newHashSet(BalanceTestBuilder.builder().build())).build());
        //Optional<PriceBalance> priceBalanceOptional = priceComponentService.getPriceBalance(priceBalanceOne.getArrangementId());
        //Assert.assertTrue(priceBalanceOptional.isPresent());
        //Assert.assertEquals(Integer.valueOf(2),priceBalanceOptional.getPriceBalance().getSequenceNumber());
    }

    @Test
    public void testCreatingPriceTransaction() throws Exception {
        PriceApiClient priceApiClient = BEAN_CONTEXT.getBean(PriceApiClient.class);
        Poller.pollAndCheck(SatisfiedWhenClientConnected.create(priceApiClient));

        PriceTransaction priceTransaction = priceApiClient.createPriceTransaction(CreatePriceTransactionRequestTestBuilder.builder().build());
    }

}