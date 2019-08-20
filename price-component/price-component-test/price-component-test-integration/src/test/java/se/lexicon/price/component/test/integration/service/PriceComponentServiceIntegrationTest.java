package se.lexicon.price.component.test.integration.service;

import se.lexicon.order.component.domain.OrderDeal;
import se.lexicon.price.component.domain.Money;
import se.lexicon.price.component.test.common.domain.MoneyTestBuilder;
import se.lexicon.price.component.test.common.domain.OrderDealTestBuilder;
import se.lexicon.price.component.test.common.domain.PriceTestBuilder;
import com.so4it.test.category.IntegrationTest;
import com.so4it.test.common.Assert;
import com.so4it.test.gs.rule.ClearGigaSpaceTestRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.RuleChain;
import org.openspaces.core.GigaSpace;
import se.lexicon.price.component.service.PriceComponentService;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
@Category(IntegrationTest.class)
public class PriceComponentServiceIntegrationTest {

    @ClassRule
    public static final RuleChain SUITE_RULE_CHAIN = PriceComponentServiceIntegrationTestSuite.SUITE_RULE_CHAIN;
    public static final String instrumentId = "123";

    @Rule
    public ClearGigaSpaceTestRule clearGigaSpaceTestRule = new ClearGigaSpaceTestRule(PriceComponentServiceIntegrationTestSuite.getExportContext().getBean(GigaSpace.class));



    @Test
    public void testGettingTotalAmount() {
        PriceComponentService priceComponentService = PriceComponentServiceIntegrationTestSuite.getImportContext().getBean(PriceComponentService.class);
        priceComponentService.createPrice(PriceTestBuilder.builder().build());
        priceComponentService.createPrice(PriceTestBuilder.builder().build());
        Assert.assertEquals(BigDecimal.valueOf(200.0), priceComponentService.getTotalAmountOnPrices());
    }

    @Test
    public void testPlacePrice(){
        PriceComponentService priceComponentService = PriceComponentServiceIntegrationTestSuite.getImportContext().getBean(PriceComponentService.class);

        priceComponentService.createOrderDeal(OrderDealTestBuilder.builder()
                .withInstrument("inst1")
                .withPrice(MoneyTestBuilder.builder()
                        .withAmount(BigDecimal.valueOf(500d)).build()).build());

        priceComponentService.createOrderDeal(OrderDealTestBuilder.builder()
                .withInstrument("inst2")
                .withPrice(MoneyTestBuilder.builder()
                        .withAmount(BigDecimal.valueOf(300d)).build()).build());

        priceComponentService.createOrderDeal(OrderDealTestBuilder.builder()
                .withInstrument("inst3")
                .withPrice(MoneyTestBuilder.builder()
                        .withAmount(BigDecimal.valueOf(100d)).build()).build());

        Assert.assertEquals(BigDecimal.valueOf(100d), priceComponentService.placePrice("inst3"));
    }

}
