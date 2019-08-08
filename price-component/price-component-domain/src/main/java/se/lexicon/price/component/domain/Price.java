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

    private Integer amount;

    private BigDecimal lastPrice;

    private BigDecimal marketPrice;

    private String orderBookId;

    private Boolean isSeller;

    private Price() {
    }

    private Price(Builder builder) {
        this.priceId = Required.notNull(builder.priceId,"priceId");
        this.instrumentId = Required.notNull(builder.instrumentId,"instrumentId");
        this.amount = Required.notNull(builder.amount,"amount");
        this.lastPrice = Required.notNull(builder.lastPrice,"lastPrice");
        this.marketPrice = Required.notNull(builder.marketPrice,"marketPrice");
        this.orderBookId = Required.notNull(builder.orderBookId,"orderBookId");
        this.isSeller = Required.notNull(builder.isSeller,"isSeller");
    }


    public String getSsn() {
        return priceId;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getPriceId() {
        return priceId;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public String getOrderBookId() {
        return orderBookId;
    }

    public Boolean getSeller() {
        return isSeller;
    }

    @Override
    protected Object[] getIdFields() {
        return new Object[]{priceId,instrumentId,amount};
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder implements com.so4it.common.builder.Builder<Price>{


        private String priceId;

        private String instrumentId;

        private String orderBookId;

        private Integer amount;

        private BigDecimal lastPrice;

        private BigDecimal marketPrice;

        private Boolean isSeller;

        public Builder withPriceId(String priceId){
            this.priceId = priceId;
            return this;
        }

        public Builder withInstrumentId(String instrumentId){
            this.instrumentId = instrumentId;
            return this;
        }

        public Builder withMarketPrice(BigDecimal marketPrice){
            this.marketPrice = marketPrice;
            return this;
        }

        public Builder withLastPrice(BigDecimal lastPrice){
            this.lastPrice = lastPrice;
            return this;
        }

        public Builder isSeller(Boolean isSeller){
            this.isSeller = isSeller;
            return this;
        }

        public Builder withOrderBookId(String orderBookId){
            this.orderBookId = orderBookId;
            return this;
        }


        public Builder withAmount(Integer amount){
            this.amount = amount;
            return this;
        }

        @Override
        public  Price build() {
            return new Price(this);
        }
    }


}
