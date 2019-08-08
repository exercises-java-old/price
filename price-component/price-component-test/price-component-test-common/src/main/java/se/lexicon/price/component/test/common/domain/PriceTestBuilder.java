package se.lexicon.price.component.test.common.domain;

import se.lexicon.price.component.domain.Price;
import com.so4it.common.util.object.Required;
import com.so4it.test.domain.AbstractTestBuilder;

import java.math.BigDecimal;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public class PriceTestBuilder extends AbstractTestBuilder<Price> {

    private Price.Builder builder;


    public PriceTestBuilder(Price.Builder builder) {
        this.builder = Required.notNull(builder, "builder");
        this.builder
                .withPriceId("1111111111")
                .withAmount(BigDecimal.TEN);

    }

    public PriceTestBuilder withSsn(String priceId){
        builder.withPriceId(priceId);
        return this;
    }

    public PriceTestBuilder withAmount(BigDecimal amount){
        builder.withAmount(amount);
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
