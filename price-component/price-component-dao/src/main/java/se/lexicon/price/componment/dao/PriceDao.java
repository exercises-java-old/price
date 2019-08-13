package se.lexicon.price.componment.dao;

import se.lexicon.price.component.entity.PriceEntity;
import com.so4it.component.GenericDao;

import java.math.BigDecimal;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public interface PriceDao extends GenericDao<PriceEntity, String> {
    BigDecimal sum();
}

