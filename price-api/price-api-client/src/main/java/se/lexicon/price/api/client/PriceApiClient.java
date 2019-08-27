package se.lexicon.price.api.client;


import se.lexicon.price.Money;

public interface PriceApiClient {
    String DEFAULT_API_BEAN_NAME = "priceApiClient";

    Money placeMarketPrice(String instrument) ;

}
