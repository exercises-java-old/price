package se.lexicon.price.component.dao;

import com.j_spaces.core.client.SQLQuery;
import se.lexicon.price.component.entity.PriceTransactionEntity;
import com.so4it.component.dao.gs.AbstractSpaceDao;
import org.openspaces.core.GigaSpace;

import static org.openspaces.extensions.QueryExtension.maxEntry;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public class PriceTransactionDaoImpl extends AbstractSpaceDao<PriceTransactionEntity, String> implements PriceTransactionDao {



    public PriceTransactionDaoImpl(GigaSpace gigaSpace) {
        super(gigaSpace);
    }


    /**
     * Fetches the latest (e.g. highest insertion timestamp) {@code PriceTransactionEntity} available in the space.
     *
     *
     * @param arrangementId
     * @return
     */
    @Override
    public PriceTransactionEntity getLatest(String arrangementId) {
        SQLQuery<PriceTransactionEntity> sqlQuery = new SQLQuery<>(PriceTransactionEntity.class,"arrangementId = ?").setParameter(1,arrangementId);
        return maxEntry(getGigaSpace(),sqlQuery,"insertionTimestamp");
    }
}



