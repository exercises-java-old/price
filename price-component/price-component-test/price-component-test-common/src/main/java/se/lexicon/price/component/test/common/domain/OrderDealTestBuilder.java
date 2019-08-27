package se.lexicon.price.component.test.common.domain;

import com.so4it.common.util.object.Required;
import com.so4it.test.domain.AbstractTestBuilder;
import se.lexicon.order.component.domain.OrderDeal;
import se.lexicon.price.component.domain.Money;
import se.lexicon.price.component.domain.Price;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public class OrderDealTestBuilder extends AbstractTestBuilder<OrderDeal> {

    private OrderDeal.Builder builder;

    private static int id = 1;

    public OrderDealTestBuilder(OrderDeal.Builder builder) {
        this.builder = Required.notNull(builder, "builder");
        this.builder
                .withId("" + id)
                .withInstrument("inst" + id++)
                .withNoOfItems(90)
                .withOrderId("88")
                .withSsn("123")
                .withPrice(MoneyTestBuilder.builder().build());
    }


    public OrderDealTestBuilder withId(String orderDealId){
        builder.withId(orderDealId);
        return this;
    }

    public OrderDealTestBuilder withPrice(se.lexicon.order.component.domain.Money price){
        builder.withPrice(price);
        return this;
    }

    public OrderDealTestBuilder withInstrument(String instrument){
        builder.withInstrument(instrument);
        return this;
    }

    public OrderDealTestBuilder withNoOfItems(Integer noOfItems){
        builder.withNoOfItems(noOfItems);
        return this;
    }

    public OrderDealTestBuilder withMatchingOrderId(String matchingOrderId){
        builder.withOrderId(matchingOrderId);
        return this;
    }

    public static OrderDealTestBuilder builder() {
        return new OrderDealTestBuilder(OrderDeal.builder());
    }

    @Override
    public OrderDeal build() {
        return builder.build();
    }
}
