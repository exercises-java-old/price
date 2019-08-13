package se.lexicon.price.component.service;

import se.lexicon.price.component.domain.Price;
import com.so4it.common.util.object.Required;
import com.so4it.gs.rpc.ServiceExport;
import se.lexicon.price.component.entity.PriceEntity;
import se.lexicon.price.component.dao.PriceDao;

import java.math.BigDecimal;
import java.util.Set;

@ServiceExport({PriceComponentService.class})
public class PriceComponentServiceImpl implements PriceComponentService {

    private final PriceDao priceDao;


    public PriceComponentServiceImpl(PriceDao priceDao) {
        this.priceDao = Required.notNull(priceDao, "priceDao");

    }

    @Override
    public void createPrice(Price price) {
        PriceEntity priceEntity = PriceEntity.builder()
                .withId(price.getPriceId())
                .withInstrumentId(price.getInstrumentId())
                .withValue(price.getValue()).build();
        priceDao.insert(priceEntity);
    }


    @Override
    public Price getPrice(String priceId) {
        PriceEntity priceEntity = priceDao.readByIdIfExists(priceId);

        return Price.builder().withPriceId(priceId).withInstrumentId(priceEntity.getInstrumentId()).withValue(priceEntity.getValue()).build();

    }


    @Override
    public BigDecimal getTotalAmountOnPrices() {
        Set<PriceEntity> entities = priceDao.readAll();
        return BigDecimal.valueOf( entities.stream().map( rr -> rr.getValue().getAmount().doubleValue()).reduce(0.0,Double::sum));
    }
}