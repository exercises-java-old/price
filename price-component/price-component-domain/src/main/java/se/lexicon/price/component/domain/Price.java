package se.lexicon.price.component.domain;

import com.so4it.common.domain.DomainClass;
import com.so4it.common.util.object.Required;
import com.so4it.common.util.object.ValueObject;

import java.math.BigDecimal;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
@DomainClass
public class Price extends ValueObject {

    private static final long serialVersionUID = 1L;


    private String priceId;

    private String instrumentId;

    private Money value;

    private Price() {
    }

    private Price(Builder builder) {
        this.priceId = Required.notNull(builder.priceId,"priceId");
        this.instrumentId = Required.notNull(builder.instrumentId,"instrumentId");
        this.value = Required.notNull(builder.value,"amount");
    }




    public Money getValue() {
        return value;
    }

    public String getPriceId() {
        return priceId;
    }

    public String getInstrumentId() {
        return instrumentId;
    }


    @Override
    protected Object[] getIdFields() {
        return new Object[]{priceId,instrumentId,value};
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder implements com.so4it.common.builder.Builder<Price>{


        private String priceId;

        private String instrumentId;

        private Money value;

        public Builder withPriceId(String priceId){
            this.priceId = priceId;
            return this;
        }

        public Builder withInstrumentId(String instrumentId){
            this.instrumentId = instrumentId;
            return this;
        }


        public Builder withValue(Money value){
            this.value = value;
            return this;
        }

        @Override
        public  Price build() {
            return new Price(this);
        }
    }


}
