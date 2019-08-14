package se.lexicon.price.component.test.integration.dao;

import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;
import se.lexicon.price.component.dao.PriceDao;
import se.lexicon.price.component.domain.Money;
import se.lexicon.price.component.domain.Order;
import se.lexicon.price.component.domain.Price;
import se.lexicon.price.component.test.integration.service.PriceComponentServiceIntegrationTestSuite;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;

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
        Assert.assertEquals(priceEntity, priceDao.read(priceEntity.getId()));
    }

    @Test
    public void sum() throws IOException {

        Currency c1 = Currency.getInstance("SEK");

        Money m1 = Money.builder()
                .withCurrency(c1)
                .withAmount(BigDecimal.valueOf(1000000))
                .build();

        Money m2 = Money.builder()
                .withCurrency(c1)
                .withAmount(BigDecimal.valueOf(2000))
                .build();

        Price p1 = Price.builder().withInstrumentId("1").withPriceId("1").withValue(m1).build();
        Price p2=Price.builder().withInstrumentId("1").withPriceId("2").withValue(m2).build();
        Price p3=Price.builder().withInstrumentId("1").withPriceId("3").withValue(m1).build();

    }
    
}
