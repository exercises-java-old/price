package se.lexicon.price.api.client;

import com.so4it.common.util.object.Required;
import com.so4it.ft.core.FaultTolerantBean;
import com.so4it.metric.springframework.MetricsBean;
import se.lexicon.price.Money;
import se.lexicon.price.PlaceMarketPriceRequest;
import se.lexicon.price.PriceApiServiceGrpc;

@FaultTolerantBean(groupKey = PriceApiClientImpl.ACCOUNT_API_CLIENT_NAME)
@MetricsBean(name = PriceApiClientImpl.ACCOUNT_API_CLIENT_NAME)
public class PriceApiClientImpl implements PriceApiClient {

    static final String ACCOUNT_API_CLIENT_NAME = "ACCOUNT_API_CLIENT";

    private final PriceApiServiceGrpc.PriceApiServiceBlockingStub priceApiService;

    PriceApiClientImpl(PriceApiServiceGrpc.PriceApiServiceBlockingStub priceApiService) {
        this.priceApiService = Required.notNull(priceApiService, "priceApiService");
    }

//    @Override
//    public com.seb.price.component.domain.PriceBalance createPriceBalance(com.seb.price.component.domain.CreatePriceBalanceRequest request) {
//        return PriceBalanceApiMapper.map(priceApiServiceBlockingStub.createPriceBalance(PriceBalanceApiMapper.map(request)));
//    }
//
//    @Override
//    public com.seb.price.component.domain.PriceTransaction createPriceTransaction(com.seb.price.component.domain.CreatePriceTransactionRequest request) {
//        return PriceTransactionApiMapper.map(priceApiServiceBlockingStub.createPriceTransaction(PriceTransactionApiMapper.map(request)));
//    }

    @Override
    public Money placeMarketPrice(String instrument) {
        se.lexicon.price.PlaceMarketPriceResponse response = priceApiService.placeMarketPrice(PlaceMarketPriceRequest.newBuilder()
                .setInstrument(instrument)
                .build());

        return response.getPrice();
    }
}
