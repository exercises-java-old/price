package se.lexicon.price.component.service;


import se.lexicon.order.component.domain.OrderDeal;
import se.lexicon.price.component.domain.Price;
import com.so4it.gs.rpc.Broadcast;
import com.so4it.gs.rpc.Routing;

import java.math.BigDecimal;

public interface PriceComponentService {

    String DEFAULT_BEAN_NAME = "priceComponentService";

    void createPrice(@Routing("getInstrumentId") Price price);

    void createOrderDeal(@Routing("getInstrument") OrderDeal orderDeal);

    //price.hashCode() % numberOfPartitions
    Price getPrice(@Routing String instrumentId);


    @Broadcast(reducer =  BigDecimalRemoteResultReducer.class)
    BigDecimal getTotalAmountOnPrices();


    BigDecimal placePrice(@Routing String instrumentId);

}
