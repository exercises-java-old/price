package se.lexicon.price.component.test.common.domain;

import com.so4it.common.util.object.Required;
import com.so4it.test.domain.AbstractTestBuilder;
import se.lexicon.price.component.domain.DealMock;
import se.lexicon.price.component.domain.Money;

import java.math.BigDecimal;
import java.util.Currency;

/**
     * @author Ahmed Molah
     */
    public class DealMockTestBuilder extends AbstractTestBuilder<DealMock> {

        private DealMock.Builder builder;


        public DealMockTestBuilder(DealMock.Builder builder) {
            this.builder = Required.notNull(builder, "builder");
            this.builder
                    .withId("11111")
                    .withInstrument("123")
                    .withClosed(false)
                    .withValue(Money.builder().withAmount(BigDecimal.valueOf(10)).withCurrency(Currency.getInstance("SEK")).build());
        }

        public se.lexicon.price.component.test.common.domain.DealMockTestBuilder builder(String priceId){
            builder.withId(priceId);
            return this;
        }

        public se.lexicon.price.component.test.common.domain.DealMockTestBuilder withId(String Id){
            builder.withId(Id);
            return this;
        }

        public se.lexicon.price.component.test.common.domain.DealMockTestBuilder withValue(Money value){
            builder.withValue(value);
            return this;
        }

        public se.lexicon.price.component.test.common.domain.DealMockTestBuilder withInstrument(String instrument){
            builder.withInstrument(instrument);
            return this;
        }

        public se.lexicon.price.component.test.common.domain.DealMockTestBuilder withClosed(boolean closed){
            builder.withClosed(closed);
            return this;
        }


        public static se.lexicon.price.component.test.common.domain.DealMockTestBuilder builder() {
            return new se.lexicon.price.component.test.common.domain.DealMockTestBuilder(DealMock.builder());
        }

        @Override
        public DealMock build() {
            return builder.build();
        }
    }
