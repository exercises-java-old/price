package se.lexicon.price.component.dao;

import se.lexicon.price.component.entity.PriceBalanceEntity;
import com.so4it.component.GenericDao;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public interface PriceBalanceDao extends GenericDao<PriceBalanceEntity, String> {
    PriceBalanceEntity getLatest(String arrangementId);
}

