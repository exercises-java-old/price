package se.lexicon.price.component.client;


import com.so4it.gs.rpc.Routing;
import se.lexicon.price.component.domain.Price;

import java.math.BigDecimal;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public interface PriceComponentClient {



    void createPrice(Price price);

    BigDecimal placePrice(String instrumentId);
}
