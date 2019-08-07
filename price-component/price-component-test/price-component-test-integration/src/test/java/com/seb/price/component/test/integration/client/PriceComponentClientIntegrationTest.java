package com.seb.price.component.test.integration.client;


import com.seb.price.component.test.integration.service.PriceComponentServiceIntegrationTestSuite;
import com.so4it.test.category.IntegrationTest;
import com.so4it.test.gs.rule.ClearGigaSpaceTestRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.RuleChain;
import org.openspaces.core.GigaSpace;
import se.lexicon.price.component.client.PriceComponentClient;
import se.lexicon.price.component.entity.PriceEntity;
import se.lexicon.price.component.test.common.domain.PriceTestBuilder;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
@Category(IntegrationTest.class)
public class PriceComponentClientIntegrationTest {

    @ClassRule
    public static final RuleChain SUITE_RULE_CHAIN = PriceComponentServiceIntegrationTestSuite.SUITE_RULE_CHAIN;

    @Rule
    public ClearGigaSpaceTestRule clearGigaSpaceTestRule = new ClearGigaSpaceTestRule(PriceComponentServiceIntegrationTestSuite.getExportContext().getBean(GigaSpace.class));

    @Test
    public void testCreatingPrice(){
        PriceComponentClient priceComponentClient = PriceComponentServiceIntegrationTestSuite.getImportContext().getBean(PriceComponentClient.class);
        priceComponentClient.createPrice(PriceTestBuilder.builder().build());


        Assert.assertEquals(1, PriceComponentServiceIntegrationTestSuite.getExportContext().getBean(GigaSpace.class).count(PriceEntity.templateBuilder().build()));

    }

}
