package se.lexicon.price.api.client;


import se.lexicon.price.component.domain.PriceBalance;
import se.lexicon.price.component.domain.PriceTransaction;
import se.lexicon.price.component.domain.CreatePriceBalanceRequest;
import se.lexicon.price.component.domain.CreatePriceTransactionRequest;

public interface PriceApiClient {
    String DEFAULT_API_BEAN_NAME = "priceApiClient";

    PriceBalance createPriceBalance(CreatePriceBalanceRequest request);
    PriceTransaction createPriceTransaction(CreatePriceTransactionRequest request);

}
