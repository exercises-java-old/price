package se.lexicon.price.component.domain;

import com.so4it.annotation.Allowed;
import com.so4it.common.util.object.Required;
import com.so4it.common.util.object.ValueObject;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public class Order extends ValueObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Allowed(value = "Auto generated by GS",types = {Allowed.Type.NULLABLE})
    private String id;

    private String orderBookId;

    private BigDecimal amount;

    private Order() {
    }

    private Order(Builder builder) {
        this.id = builder.id;
        this.amount = Required.notNull(builder.amount,"amount");
        this.orderBookId = builder.orderBookId;
        //this.ssn = Required.notNull(builder.ssn,"ssn");
    }


    public String getId() {
        return id;
    }

    public String getOrderBookId() {
        return orderBookId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    protected Object[] getIdFields() {
        return new Object[]{amount,orderBookId};
    }

    public static Builder builder(){
        return new Builder();
    }


    public static class Builder implements com.so4it.common.builder.Builder<Order>{

        private String id;

        private String orderBookId;

        private BigDecimal amount;

        public Builder withId(String id){
            this.id = id;
            return this;
        }

        public Builder withOrderBookId(String orderBookId){
            this.orderBookId = orderBookId;
            return this;
        }

        public Builder withAmount(BigDecimal amount){
            this.amount = amount;
            return this;
        }

        @Override
        public Order build() {
            return new Order(this);
        }
    }
}
