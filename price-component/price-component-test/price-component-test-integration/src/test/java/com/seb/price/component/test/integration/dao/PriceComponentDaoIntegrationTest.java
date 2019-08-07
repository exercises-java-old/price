package com.seb.price.component.test.integration.dao;

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
import se.lexicon.price.component.entity.PriceEntity;
import se.lexicon.price.component.test.common.entity.PriceEntityTestBuilder;
import se.lexicon.price.componment.dao.PriceDao;

import javax.inject.Inject;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
@Category(IntegrationTest.class)
public class PriceComponentDaoIntegrationTest {

    @ClassRule
    public static final RuleChain SUITE_RULE_CHAIN = PriceComponentDaoIntegrationTestSuite.SUITE_RULE_CHAIN;

    @Rule
    public ClearGigaSpaceTestRule clearGigaSpaceTestRule = new ClearGigaSpaceTestRule(PriceComponentServiceIntegrationTestSuite.getExportContext().getBean(GigaSpace.class));



    @Test
    public void testInsertingPrice() {
        PriceDao priceDao = PriceComponentDaoIntegrationTestSuite.getExportContext().getBean(PriceDao.class);
        PriceEntity priceEntity = priceDao.insert(PriceEntityTestBuilder.builder().build());
        Assert.assertEquals(priceEntity,priceDao.read(priceEntity.getId()));
    }

}
