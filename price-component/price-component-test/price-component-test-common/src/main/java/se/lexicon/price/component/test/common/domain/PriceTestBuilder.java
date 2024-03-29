package se.lexicon.price.component.test.common.domain;


import se.lexicon.order.component.domain.Money;
import se.lexicon.price.component.domain.Price;
import com.so4it.common.util.object.Required;
import com.so4it.test.domain.AbstractTestBuilder;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Ahmed Molah
 */
public class PriceTestBuilder extends AbstractTestBuilder<Price> {

    private Price.Builder builder;

    private static int id = 1;

    public PriceTestBuilder(Price.Builder builder) {
        this.builder = Required.notNull(builder, "builder");
        this.builder
                .withPriceId("" + id++)
                .withInstrumentId("inst1")
                .withValue(MoneyTestBuilder.builder().build());
    }

    public PriceTestBuilder builder(String priceId){
        builder.withPriceId(priceId);
        return this;
    }

    public PriceTestBuilder withPriceId(String priceId){
        builder.withPriceId(priceId);
        return this;
    }

    public PriceTestBuilder withValue(Money value){
        builder.withValue(value);
        return this;
    }

    public PriceTestBuilder withInstrumentId(String instrumentId){
        builder.withInstrumentId(instrumentId);
        return this;
    }



    public static PriceTestBuilder builder() {
        return new PriceTestBuilder(Price.builder());
    }

    @Override
    public Price build() {
        return builder.build();
    }
}
