package se.lexicon.price.component.test.integration.service;

import se.lexicon.price.component.domain.Money;
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
        priceComponentService.createPrice(PriceTestBuilder.builder().withPriceId("22").withInstrumentId("price1").withValue(Money.builder().withAmount(BigDecimal.valueOf(100)).build()).build());
        priceComponentService.createPrice(PriceTestBuilder.builder().withPriceId("33").withInstrumentId("price2").withValue(Money.builder().withAmount(BigDecimal.valueOf(100)).build()).build());
        Assert.assertEquals(BigDecimal.valueOf(200.0), priceComponentService.getTotalAmountOnPrices());
    }

    @Test
    public void testPlacePrice(){
        PriceComponentService priceComponentService = PriceComponentServiceIntegrationTestSuite.getImportContext().getBean(PriceComponentService.class);

        priceComponentService.createOrderDeal(OrderDealTestBuilder.builder().withId("22").withInstrument("inst2").withPrice(se.lexicon.order.component.domain.Money.builder().withAmount(BigDecimal.valueOf(1000)).withCurrency(Currency.getInstance("SEK")).build()).build());
        priceComponentService.createOrderDeal(OrderDealTestBuilder.builder().withId("20").withInstrument("inst1").withPrice(se.lexicon.order.component.domain.Money.builder().withAmount(BigDecimal.valueOf(2000)).withCurrency(Currency.getInstance("SEK")).build()).build());
        priceComponentService.createOrderDeal(OrderDealTestBuilder.builder().withId("23").withInstrument("inst1").withPrice(se.lexicon.order.component.domain.Money.builder().withAmount(BigDecimal.valueOf(1000)).withCurrency(Currency.getInstance("SEK")).build()).build());
        priceComponentService.createOrderDeal(OrderDealTestBuilder.builder().withId("26").withInstrument("inst1").withPrice(se.lexicon.order.component.domain.Money.builder().withAmount(BigDecimal.valueOf(1500)).withCurrency(Currency.getInstance("SEK")).build()).build());

        Assert.assertEquals(BigDecimal.valueOf(1500), priceComponentService.placePrice("inst1"));
    }

}
