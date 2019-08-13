package se.lexicon.price.component.dao;

import com.j_spaces.core.client.SQLQuery;
import se.lexicon.price.component.entity.PriceBalanceEntity;
import com.so4it.component.dao.gs.AbstractSpaceDao;
import org.openspaces.core.GigaSpace;

import static org.openspaces.extensions.QueryExtension.*;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public class PriceBalanceDaoImpl extends AbstractSpaceDao<PriceBalanceEntity, String> implements PriceBalanceDao {



    public PriceBalanceDaoImpl(GigaSpace gigaSpace) {
        super(gigaSpace);
    }


    /**
     * Fetches the latest (e.g. highest insertion timestamp) {@code PriceBalanceEntity} available in the space.
     *
     *
     * @param arrangementId
     * @return
     */
    @Override
    public PriceBalanceEntity getLatest(String arrangementId) {
        SQLQuery<PriceBalanceEntity> sqlQuery = new SQLQuery<>(PriceBalanceEntity.class,"arrangementId = ?").setParameter(1,arrangementId);
        return maxEntry(getGigaSpace(),sqlQuery,"insertionTimestamp");
    }
}



