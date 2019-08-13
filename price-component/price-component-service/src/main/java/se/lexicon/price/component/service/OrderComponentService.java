package se.lexicon.price.component.service;


import com.so4it.gs.rpc.Broadcast;
import com.so4it.gs.rpc.Routing;
import org.springframework.security.access.method.P;
import se.lexicon.price.component.domain.Order;
import se.lexicon.price.component.domain.Orders;
import se.lexicon.price.component.domain.Price;

import java.math.BigDecimal;

public interface OrderComponentService {

    String DEFAULT_BEAN_NAME = "orderComponentService";

    Orders getPrices(@Routing String instrumentId);

    void placePrice(@Routing("getInstrumentId") Price price);


    @Broadcast(reducer = BigDecimalRemoteResultReducer.class)
    BigDecimal getTotalPriceValueOfAllPrices();

}
