package se.lexicon.price.component.service;

import se.lexicon.price.component.domain.Price;
import se.lexicon.price.componment.dao.OrderDao;
import com.so4it.common.util.object.Required;
import com.so4it.gs.rpc.ServiceExport;
import se.lexicon.price.component.entity.PriceEntity;
import se.lexicon.price.componment.dao.PriceDao;

import java.math.BigDecimal;
import java.util.Set;

@ServiceExport({PriceComponentService.class})
public class PriceComponentServiceImpl implements PriceComponentService {

    private final PriceDao priceDao;



    private final OrderDao orderDao;

    public PriceComponentServiceImpl(PriceDao priceDao,OrderDao orderDao) {
        this.priceDao = Required.notNull(priceDao, "priceDao");
        this.orderDao = Required.notNull(orderDao, "orderDao");
    }

    @Override
    public void createPrice(Price price) {
        PriceEntity priceEntity = PriceEntity.builder()
                .withId(price.getSsn())
                .withAmount(price.getAmount()).build();
        priceDao.insert(priceEntity);
    }


    @Override
    public Price getPrice(String ssn) {
        PriceEntity priceEntity = priceDao.readByIdIfExists(ssn);

        return Price.builder().withSsn(ssn).withAmount(priceEntity.getAmount()).build();

    }


    @Override
    public BigDecimal getTotalAmountOnPrices() {
        Set<PriceEntity> entities = priceDao.readAll();
        return BigDecimal.valueOf( entities.stream().map( rr -> rr.getAmount().doubleValue()).reduce(0.0,Double::sum));
    }
}