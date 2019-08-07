package se.lexicon.price.api.client;

import se.lexicon.price.api.PriceApiServiceGrpc;
import se.lexicon.price.api.mapper.PriceBalanceApiMapper;
import se.lexicon.price.api.mapper.PriceTransactionApiMapper;
import com.so4it.common.util.object.Required;
import com.so4it.ft.core.FaultTolerantBean;
import com.so4it.metric.springframework.MetricsBean;

@FaultTolerantBean(groupKey = PriceApiClientImpl.ACCOUNT_API_CLIENT_NAME)
@MetricsBean(name = PriceApiClientImpl.ACCOUNT_API_CLIENT_NAME)
public class PriceApiClientImpl implements PriceApiClient {

    static final String ACCOUNT_API_CLIENT_NAME = "ACCOUNT_API_CLIENT";

    private final PriceApiServiceGrpc.PriceApiServiceBlockingStub priceApiServiceBlockingStub;

    PriceApiClientImpl(PriceApiServiceGrpc.PriceApiServiceBlockingStub priceApiServiceBlockingStub) {
        this.priceApiServiceBlockingStub = Required.notNull(priceApiServiceBlockingStub, "priceApiServiceBlockingStub");
    }

    @Override
    public com.seb.price.component.domain.PriceBalance createPriceBalance(com.seb.price.component.domain.CreatePriceBalanceRequest request) {
        return PriceBalanceApiMapper.map(priceApiServiceBlockingStub.createPriceBalance(PriceBalanceApiMapper.map(request)));
    }

    @Override
    public com.seb.price.component.domain.PriceTransaction createPriceTransaction(com.seb.price.component.domain.CreatePriceTransactionRequest request) {
        return PriceTransactionApiMapper.map(priceApiServiceBlockingStub.createPriceTransaction(PriceTransactionApiMapper.map(request)));
    }

}
