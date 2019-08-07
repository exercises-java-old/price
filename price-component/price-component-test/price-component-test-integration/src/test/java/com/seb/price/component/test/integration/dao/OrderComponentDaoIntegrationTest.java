package com.seb.price.component.test.integration.dao;

import se.lexicon.price.component.entity.OrderEntity;
import com.lexicon.price.component.test.common.entity.OrderEntityTestBuilder;
import com.seb.price.component.test.integration.service.PriceComponentServiceIntegrationTestSuite;
import se.lexicon.price.componment.dao.OrderDao;
import com.so4it.test.category.IntegrationTest;
import com.so4it.test.gs.rule.ClearGigaSpaceTestRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.RuleChain;
import org.openspaces.core.GigaSpace;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
@Category(IntegrationTest.class)
public class OrderComponentDaoIntegrationTest {

    @ClassRule
    public static final RuleChain SUITE_RULE_CHAIN = PriceComponentDaoIntegrationTestSuite.SUITE_RULE_CHAIN;

    @Rule
    public ClearGigaSpaceTestRule clearGigaSpaceTestRule = new ClearGigaSpaceTestRule(PriceComponentServiceIntegrationTestSuite.getExportContext().getBean(GigaSpace.class));

    @Test
    public void testInsertingOrder(){
        OrderDao priceDao = PriceComponentDaoIntegrationTestSuite.getExportContext().getBean(OrderDao.class);
        OrderEntity priceEntity = priceDao.insert(OrderEntityTestBuilder.builder().build());
    }

}
