package com.seb.price.component.test.integration.service;

import com.lexicon.price.component.test.common.domain.OrderTestBuilder;
import com.so4it.test.category.IntegrationTest;
import com.so4it.test.common.Assert;
import com.so4it.test.gs.rule.ClearGigaSpaceTestRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.RuleChain;
import org.openspaces.core.GigaSpace;
import se.lexicon.price.component.domain.Orders;
import se.lexicon.price.component.domain.Price;
import se.lexicon.price.component.service.PriceComponentService;
import se.lexicon.price.component.service.OrderComponentService;
import se.lexicon.price.component.test.common.domain.PriceTestBuilder;

import java.math.BigDecimal;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
@Category(IntegrationTest.class)
public class PriceComponentServiceIntegrationTest {

    @ClassRule
    public static final RuleChain SUITE_RULE_CHAIN = PriceComponentServiceIntegrationTestSuite.SUITE_RULE_CHAIN;
    public static final String SSN = "1111111111";

    @Rule
    public ClearGigaSpaceTestRule clearGigaSpaceTestRule = new ClearGigaSpaceTestRule(PriceComponentServiceIntegrationTestSuite.getExportContext().getBean(GigaSpace.class));

    @Test
    public void testCreatingPriceAndCheckBalanceIsCorrect() {

        PriceComponentService priceComponentService = PriceComponentServiceIntegrationTestSuite.getImportContext().getBean(PriceComponentService.class);
        OrderComponentService orderComponentService = PriceComponentServiceIntegrationTestSuite.getImportContext().getBean(OrderComponentService.class);



        //Create the price
        Price created = PriceTestBuilder.builder().withSsn(SSN).build();
        priceComponentService.createPrice(created);


        //Fetch the price
        Price fetched = priceComponentService.getPrice(SSN);

        //Assert the created and fetched prices are the same (e.g. "equals")
        Assert.assertNotNull(fetched);
        Assert.assertEquals(created,fetched);


        orderComponentService.placeOrder(OrderTestBuilder.builder().withSsn(SSN).withAmount(BigDecimal.ONE).build());
        orderComponentService.placeOrder(OrderTestBuilder.builder().withSsn(SSN).withAmount(BigDecimal.TEN).build());



        Orders orders = orderComponentService.getOrders(SSN);
        Assert.assertEquals(2, orders.size());
        Assert.assertEquals(BigDecimal.valueOf(11.0), orderComponentService.getTotalOrderValueOfAllPrices());




    }


    @Test
    public void testGettingTotalAmount() {
        PriceComponentService priceComponentService = PriceComponentServiceIntegrationTestSuite.getImportContext().getBean(PriceComponentService.class);
        priceComponentService.createPrice(PriceTestBuilder.builder().withSsn("price2").withAmount(BigDecimal.valueOf(100)).build());
        priceComponentService.createPrice(PriceTestBuilder.builder().withSsn("price1").withAmount(BigDecimal.valueOf(100)).build());
        Assert.assertEquals(BigDecimal.valueOf(200.0), priceComponentService.getTotalAmountOnPrices());
    }

}
