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

        Order o1 = Order.builder()
                .withId("12")
                .withAmount(BigDecimal.valueOf(200))
                .build();

        //singleton
        Currency c1 = Currency.getInstance("SEK");

        Currency c2 = Currency.getInstance("$");

        //Builder Patten
        Money m1 = Money.builder()
                .withCurrency(c1)
                .withAmount(BigDecimal.valueOf(1000000))
                .build();

        Money m2 = Money.builder()
                .withCurrency(c1)
                .withAmount(BigDecimal.valueOf(100000))
                .build();
/*
        Money.Builder builder = Money.builder();

        builder = builder.withAmount(BigDecimal.valueOf(100));

        builder = builder.withCurrency(c1);

        Money m1 = builder.build();
*/
        Price p1 = Price.builder()
                .withInstrumentId("price1")
                .withPriceId("priceid")
                .withValue(m1)
/*                Money.builder()
                        .withAmount(BigDecimal.valueOf(100))
                        .withCurrency(Currency.getInstance("SEK"))
                        .build())*/
                .build();

        //Price p2=Price.builder().withInstrumentId("price2");
        //Price p3=Price.builder().withInstrumentId("price");


        Price p2= Price.builder().withInstrumentId("price2").withPriceId("priceid").withValue(m2).build();

        Price p3= Price.builder().withInstrumentId("price2").withPriceId("priceid").withValue(m1).build();



// Setting URL
        String url_str = "https://api.exchangerate-api.com/v4/latest/USD";

// Making Request
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

// Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

// Accessing object
        String req_result = jsonobj.get("result").getAsString();

        System.out.println(req_result);

    }



}
