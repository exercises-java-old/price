package se.lexicon.price.component.client;

import se.lexicon.price.component.domain.Price;
import se.lexicon.price.component.service.PriceComponentService;
import com.so4it.common.util.object.Required;

import java.math.BigDecimal;

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

    @Override
    public BigDecimal placePrice(String instrumentId) {
        return priceComponentService.placePrice(instrumentId);
    }
}
