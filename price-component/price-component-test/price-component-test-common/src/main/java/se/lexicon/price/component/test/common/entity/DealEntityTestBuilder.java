package se.lexicon.price.component.test.common.entity;

import com.so4it.common.util.object.Required;
import com.so4it.test.domain.AbstractTestBuilder;
import se.lexicon.price.component.domain.Money;
import se.lexicon.price.component.entity.DealEntity;
import se.lexicon.price.component.test.common.domain.MoneyTestBuilder;

import java.math.BigDecimal;

/**
 * @author Ahmed Molah
 */
public class DealEntityTestBuilder extends AbstractTestBuilder<DealEntity> {

    private DealEntity.Builder builder;


    public DealEntityTestBuilder(DealEntity.Builder builder) {
        this.builder = Required.notNull(builder, "builder");
        this.builder
                .withId("1111")
                .withInstrument("123")
                .withClosed(false)
                .withValue(MoneyTestBuilder.builder().build());
    }

    public static DealEntityTestBuilder builder() {
        return new DealEntityTestBuilder(DealEntity.builder());
    }

    @Override
    public DealEntity build() {
        return builder.build();
    }
}
