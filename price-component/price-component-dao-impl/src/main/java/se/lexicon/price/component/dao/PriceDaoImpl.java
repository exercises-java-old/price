package se.lexicon.price.component.dao;

import se.lexicon.price.component.entity.PriceEntity;
import se.lexicon.price.componment.dao.PriceDao;
import com.so4it.component.dao.gs.AbstractSpaceDao;
import org.openspaces.core.GigaSpace;

import static org.openspaces.extensions.QueryExtension.maxEntry;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public class PriceDaoImpl extends AbstractSpaceDao<PriceEntity, String> implements PriceDao {



    public PriceDaoImpl(GigaSpace gigaSpace) {
        super(gigaSpace);
    }

}



