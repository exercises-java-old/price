package se.lexicon.price.component.entity;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.so4it.common.util.object.Required;
import com.so4it.component.entity.AbstractEntityBuilder;
import com.so4it.component.entity.IdEntity;

import java.math.BigDecimal;

/**
 *
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
@SpaceClass
public class PriceEntity extends IdEntity<String> {

    //The arrangement id of this price balance
    private String id;

    // Unique id for the posting
    private BigDecimal amount;

    private PriceEntity() {
    }

    private PriceEntity(Builder builder) {
        this.id = builder.id;
        this.amount = Required.notNull(builder.amount,"amount",builder.isTemplate());
    }

    @Override
    @SpaceId(autoGenerate = false)
    @SpaceRouting
    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static Builder builder() {
        return new Builder(false);
    }

    public static Builder templateBuilder() {
        return new Builder(true);
    }


    public static class Builder extends AbstractEntityBuilder<PriceEntity> {

        private String id;

        // Unique id for the posting
        private BigDecimal amount;

        public Builder(boolean template) {
            super(template);
        }

        public PriceEntity.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public PriceEntity.Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }


        @Override
        public PriceEntity build() {
            return new PriceEntity(this);
        }
    }
}
