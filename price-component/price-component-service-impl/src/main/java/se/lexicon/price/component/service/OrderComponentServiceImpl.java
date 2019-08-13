package se.lexicon.price.component.service;

import se.lexicon.price.component.domain.Price;
import se.lexicon.price.component.entity.PriceEntity;
import com.so4it.common.util.object.Required;
import com.so4it.gs.rpc.ServiceExport;
import se.lexicon.price.component.domain.Order;
import se.lexicon.price.component.domain.Orders;
import se.lexicon.price.component.dao.PriceDao;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@ServiceExport({OrderComponentService.class})
public class OrderComponentServiceImpl implements OrderComponentService {



    private PriceDao priceDao;


    public OrderComponentServiceImpl(PriceDao priceDao) {
        this.priceDao = Required.notNull(priceDao,"priceDao");
    }

    @Override
    public Orders getPrices(String instrumentId) {
        return Orders.valueOf(priceDao.readAll(PriceEntity.templateBuilder().withInstrumentId(instrumentId).build()).stream().
                map( entity -> Order.builder()
                        .withId(entity.getId())
                        .withSsn(instrumentId)
                        .withAmount(entity.getValue().getAmount())
                        .withOrderBookId("").build()).collect(Collectors.toSet()));
    }


    @Override
    public void placePrice(Price price) {
        priceDao.insert(PriceEntity.builder().withInstrumentId(price.getInstrumentId()).withValue(price.getValue()).build());
    }


    @Override
    public BigDecimal getTotalPriceValueOfAllPrices() {
        return priceDao.sum();
    }
}