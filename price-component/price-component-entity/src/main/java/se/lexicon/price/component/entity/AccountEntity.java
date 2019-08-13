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
public class AccountEntity extends IdEntity<String> {

    //The arrangement id of this price balance
    private String id;

    // Unique id for the posting
    private Integer amount;

    private AccountEntity() {
    }

    private AccountEntity(Builder builder) {
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

    public Integer getAmount() {
        return amount;
    }

    private void setAmount(Integer amount) {
        this.amount = amount;
    }

    public static Builder builder() {
        return new Builder(false);
    }

    public static Builder templateBuilder() {
        return new Builder(true);
    }


    public static class Builder extends AbstractEntityBuilder<AccountEntity> {

        private String id;

        // Unique id for the posting
        private Integer amount;

        public Builder(boolean template) {
            super(template);
        }

        public AccountEntity.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public AccountEntity.Builder withAmount(Integer amount) {
            this.amount = amount;
            return this;
        }


        @Override
        public AccountEntity build() {
            return new AccountEntity(this);
        }
    }
}
