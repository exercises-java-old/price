package se.lexicon.account.api.server;

import com.so4it.api.ApiServiceProvider;
import com.so4it.api.Price;
import com.so4it.api.util.StreamObserverErrorHandler;
import com.so4it.common.util.object.Required;
import com.so4it.price.api.mapper.PriceBalanceApiMapper;
import com.so4it.price.api.mapper.PriceTransactionApiMapper;
import io.grpc.stub.StreamObserver;
import se.lexicon.price.api.*;
import se.lexicon.price.component.client.PriceComponentClient;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */

@ApiServiceProvider(
        value = Price.NAME,
        specification = Price.PATH,
        version = Price.VERSION,
        properties = Price.PROPERTIES)
public class PriceApiServerImpl extends PriceApiServiceGrpc.PriceApiServiceImplBase{

    private PriceComponentClient priceComponentClient;

    public PriceApiServerImpl(PriceComponentClient priceComponentClient) {
        this.priceComponentClient = Required.notNull(priceComponentClient,"priceComponentClient");
    }

    @Override
    public void createPriceBalance(CreatePriceBalanceRequest apiRequest, StreamObserver<PriceBalance> responseObserver) {
        StreamObserverErrorHandler.of(responseObserver).execute(() -> {
            com.seb.price.component.domain.CreatePriceBalanceRequest componentRequest = PriceBalanceApiMapper.map(apiRequest);
            com.seb.price.component.domain.PriceBalance priceBalance = priceComponentClient.createPriceBalance(componentRequest);
            com.seb.price.api.PriceBalance apiPriceBalance = PriceBalanceApiMapper.map(priceBalance);
            responseObserver.onNext(apiPriceBalance);
            responseObserver.onCompleted();
        }, "Failed creating API balance");
    }

    @Override
    public void createPriceTransaction(CreatePriceTransactionRequest apiRequest, StreamObserver<PriceTransaction> responseObserver) {
        StreamObserverErrorHandler.of(responseObserver).execute(() -> {
            com.seb.price.component.domain.CreatePriceTransactionRequest componentRequest = PriceTransactionApiMapper.map(apiRequest);
            com.seb.price.component.domain.PriceTransaction priceTransaction = priceComponentClient.createPriceTransaction(componentRequest);
            com.seb.price.api.PriceTransaction apiPriceTransaction = PriceTransactionApiMapper.map(priceTransaction);
            responseObserver.onNext(apiPriceTransaction);
            responseObserver.onCompleted();
        }, "Failed creating API Transaction");
    }

}
