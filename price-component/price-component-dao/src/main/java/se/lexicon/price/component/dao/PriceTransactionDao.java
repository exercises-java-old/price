package se.lexicon.price.component.dao;

import se.lexicon.price.component.entity.PriceTransactionEntity;
import com.so4it.component.GenericDao;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public interface PriceTransactionDao extends GenericDao<PriceTransactionEntity, String> {
    PriceTransactionEntity getLatest(String arrangementId);
}

