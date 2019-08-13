package se.lexicon.price.component.dao;

import com.j_spaces.core.client.SQLQuery;
import org.openspaces.extensions.QueryExtension;
import se.lexicon.price.component.domain.Price;
import se.lexicon.price.component.entity.PriceEntity;
import se.lexicon.price.component.dao.PriceDao;
import com.so4it.component.dao.gs.AbstractSpaceDao;
import org.openspaces.core.GigaSpace;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.openspaces.extensions.QueryExtension.maxEntry;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public class PriceDaoImpl extends AbstractSpaceDao<PriceEntity, String> implements PriceDao {

    Set<Price> prices = new HashSet<Price>();



    public PriceDaoImpl(GigaSpace gigaSpace) {
        super(gigaSpace);
    }

    /*
    @Override
    public PriceEntity getLastPrice(Long instrumentId) {
        return null;
    }

    @Override
    public Set<PriceEntity> getAllPrices(Long instrumentId) {
        return null;
    }
*/





    @Override
    public BigDecimal sum() {
        return QueryExtension.sum(getGigaSpace(),new SQLQuery<>(PriceEntity.class,""),"amount");
    }




}



