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


    private String ssn;

    private BigDecimal amount;

    private Price() {
    }

    private Price(Builder builder) {
        this.ssn = Required.notNull(builder.ssn,"ssn");
        this.amount = Required.notNull(builder.amount,"amount");
    }


    public String getSsn() {
        return ssn;
    }

    public BigDecimal getAmount() {
        return amount;
    }


    @Override
    protected Object[] getIdFields() {
        return new Object[]{ssn,amount};
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder implements com.so4it.common.builder.Builder<Price>{


        private String ssn;


        private BigDecimal amount;

        public Builder withSsn(String ssn){
            this.ssn = ssn;
            return this;
        }


        public Builder withAmount(BigDecimal amount){
            this.amount = amount;
            return this;
        }

        @Override
        public  Price build() {
            return new Price(this);
        }
    }


}
