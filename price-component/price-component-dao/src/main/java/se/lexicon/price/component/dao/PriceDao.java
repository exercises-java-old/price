package se.lexicon.price.component.dao;


import com.so4it.component.GenericDao;
import se.lexicon.price.component.entity.PriceEntity;

import java.math.BigDecimal;
import java.util.Set;
public interface PriceDao extends GenericDao<PriceEntity, String> {


    BigDecimal sum();
}

