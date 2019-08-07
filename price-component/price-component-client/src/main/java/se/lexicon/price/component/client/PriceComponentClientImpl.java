package se.lexicon.price.component.client;

import se.lexicon.price.component.domain.Price;
import se.lexicon.price.component.service.PriceComponentService;
import com.so4it.common.util.object.Required;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public class PriceComponentClientImpl implements PriceComponentClient{

    private PriceComponentService priceComponentService;

    public PriceComponentClientImpl(PriceComponentService priceComponentService) {
        this.priceComponentService = Required.notNull(priceComponentService,"priceComponentService");
    }


    @Override
    public void createPrice(Price price) {
        priceComponentService.createPrice(price);
    }
}
