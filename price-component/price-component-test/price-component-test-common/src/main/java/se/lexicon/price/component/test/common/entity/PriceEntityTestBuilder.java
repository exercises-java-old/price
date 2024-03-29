package se.lexicon.price.component.test.common.entity;

import se.lexicon.price.component.domain.Money;
import se.lexicon.price.component.entity.PriceEntity;
import com.so4it.common.util.object.Required;
import com.so4it.test.domain.AbstractTestBuilder;
import se.lexicon.price.component.test.common.domain.MoneyTestBuilder;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public class PriceEntityTestBuilder extends AbstractTestBuilder<PriceEntity> {

    private PriceEntity.Builder builder;


    public PriceEntityTestBuilder(PriceEntity.Builder builder) {
        this.builder = Required.notNull(builder, "builder");
        this.builder
                .withId("1111111111")
                .withInstrumentId("123123")
                .withValue(MoneyTestBuilder.builder().build());


    }

    public static PriceEntityTestBuilder builder() {
        return new PriceEntityTestBuilder(PriceEntity.builder());
    }

    @Override
    public PriceEntity build() {
        return builder.build();
    }
}
