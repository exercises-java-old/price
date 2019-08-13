package se.lexicon.price.component.service;

import se.lexicon.price.component.domain.Money;
import se.lexicon.price.component.domain.Order;
import se.lexicon.price.component.domain.Price;
import se.lexicon.price.componment.dao.OrderDao;
import com.so4it.common.util.object.Required;
import com.so4it.gs.rpc.ServiceExport;
import se.lexicon.price.component.entity.PriceEntity;
import se.lexicon.price.componment.dao.PriceDao;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public BigDecimal placePrice(String instrumentId) {
        Set<PriceEntity> entities = priceDao.readAll();  //PriceEntity = DealEntity && priceDao = dealDao
        Set<Money> values = entities.stream().filter(priceEntity -> priceEntity.getInstrumentId().equals(instrumentId)).map(PriceEntity::getValue).collect(Collectors.toSet());
        BigDecimal total=BigDecimal.ZERO;
        int count = 0;
        for(Money val:values)
        {
            total = total.add(val.getAmount());
            count++;
        }
        total=total.divide(BigDecimal.valueOf(count),3);
        System.out.println("Total " + total);
        return total;
    }
}