package se.lexicon.price.component.service;


import com.so4it.gs.rpc.Broadcast;
import com.so4it.gs.rpc.Routing;
import se.lexicon.price.component.domain.Order;
import se.lexicon.price.component.domain.Orders;

import java.math.BigDecimal;

public interface OrderComponentService {

    String DEFAULT_BEAN_NAME = "orderComponentService";

    Orders getOrders(@Routing String ssn);

    void placeOrder(@Routing("getSsn") Order order);


    @Broadcast(reducer = BigDecimalRemoteResultReducer.class)
    BigDecimal getTotalOrderValueOfAllPrices();

}
