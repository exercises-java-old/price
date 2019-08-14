package se.lexicon.price.component.dao;

import se.lexicon.price.component.entity.OrderEntity;
import se.lexicon.price.component.entity.PriceEntity;
import com.so4it.component.GenericDao;

import java.math.BigDecimal;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public interface OrderDao extends GenericDao<OrderEntity, String> {

    BigDecimal sum();
}

