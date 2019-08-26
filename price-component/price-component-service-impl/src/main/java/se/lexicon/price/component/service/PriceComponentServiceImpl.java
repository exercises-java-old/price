package se.lexicon.price.component.service;

import se.lexicon.order.component.domain.OrderDeal;
import se.lexicon.order.component.domain.Money;
import se.lexicon.order.component.entity.OrderDealEntity;
import se.lexicon.order.componment.dao.OrderDealDao;
import se.lexicon.price.component.domain.Price;
import com.so4it.common.util.object.Required;
import com.so4it.gs.rpc.ServiceExport;
import se.lexicon.price.component.entity.PriceEntity;
import se.lexicon.price.component.dao.PriceDao;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ServiceExport({PriceComponentService.class})
public class PriceComponentServiceImpl implements PriceComponentService {

    private final PriceDao priceDao;
    private final OrderDealDao orderDealDao;


    public PriceComponentServiceImpl(PriceDao priceDao, OrderDealDao orderDealDao) {
        this.priceDao = Required.notNull(priceDao, "priceDao");
        this.orderDealDao = Required.notNull(orderDealDao, "orderDealDao");;
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
    public void createOrderDeal(OrderDeal orderDeal) {
        OrderDealEntity orderDealEntity = OrderDealEntity.builder()
                .withId(orderDeal.getId())
                .withInstrument(orderDeal.getInstrument())
                .withPrice(orderDeal.getPrice())
                .withNoOfItems(orderDeal.getNoOfItems())
                .withOrderId(orderDeal.getOrderId())
                .withSsn(orderDeal.getSsn())
                .build();

        orderDealDao.insert(orderDealEntity);
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

        // *** A temp solution until we find the calculation algorithm ***

        //gets all OrderDealEntitites
        Set<OrderDealEntity> entities = orderDealDao.readAll();

        List<Money> values = entities.stream()
                .filter(orderDealEntity -> orderDealEntity.getInstrument().equals(instrumentId))  //filters out those OrderDealEntities that have the same instrumentId as the parameter
                .map(OrderDealEntity::getPrice) //OrderDealEntity --> Money
                .collect(Collectors.toList());

        List<BigDecimal> decimals = values.stream()
                .map(Money::getAmount)//Money --> BigDecimal
                .collect(Collectors.toList());

        BigDecimal result = decimals.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add); //adds the BigDeciamls together
        System.out.println("Result before" + result );
        result = result.divide(BigDecimal.valueOf(decimals.size()),3);
        System.out.println("Size " + decimals.size());
        System.out.println("Result " + result );

        return result;
    }
}