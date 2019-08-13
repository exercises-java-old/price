package se.lexicon.price.component.service;


import se.lexicon.price.component.domain.Price;
import com.so4it.gs.rpc.Broadcast;
import com.so4it.gs.rpc.Routing;

import java.math.BigDecimal;

public interface PriceComponentService {

    String DEFAULT_BEAN_NAME = "priceComponentService";

    //price.hashCode() % numberOfPartitions
    void createPrice(@Routing("getInstrumentId") Price price);

    //price.hashCode() % numberOfPartitions
    Price getPrice(@Routing String instrumentId);


    @Broadcast(reducer =  BigDecimalRemoteResultReducer.class)
    BigDecimal getTotalAmountOnPrices();



}
