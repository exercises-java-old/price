package se.lexicon.price.component.test.common.domain;

import com.so4it.common.util.object.Required;
import com.so4it.test.domain.AbstractTestBuilder;
import se.lexicon.price.component.domain.Money;

import java.math.BigDecimal;
import java.util.Currency;

public class MoneyTestBuilder extends AbstractTestBuilder<Money> {

    private Money.Builder builder;

    private Currency currency;

    public MoneyTestBuilder(Money.Builder builder){
        this.builder = Required.notNull(builder, "builder");
        this.builder
                .withAmount(BigDecimal.valueOf(100d))
                .withCurrency(Currency.getInstance("SEK"))
                .build();
    }

    public MoneyTestBuilder withCurrency(String currency){
        this.currency = Currency.getInstance(currency);
        return this;
    }

    public static MoneyTestBuilder builder() { return new MoneyTestBuilder(Money.builder()); }

    @Override
    public Money build() {
        return builder.build();
    }
}
