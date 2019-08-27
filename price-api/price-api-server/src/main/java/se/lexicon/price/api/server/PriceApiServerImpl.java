package se.lexicon.price.api.server;

import com.so4it.api.ApiServiceProvider;
import com.so4it.api.Price;
import com.so4it.api.util.StreamObserverErrorHandler;
import com.so4it.common.util.object.Required;
import io.grpc.stub.StreamObserver;
import se.lexicon.price.Money;
import se.lexicon.price.PlaceMarketPriceRequest;
import se.lexicon.price.PlaceMarketPriceResponse;
import se.lexicon.price.PriceApiServiceGrpc;
import se.lexicon.price.api.*;
import se.lexicon.price.component.client.PriceComponentClient;
import se.lexicon.price.component.domain.CreatePriceBalanceRequest;
import se.lexicon.price.component.domain.CreatePriceTransactionRequest;
import se.lexicon.price.component.domain.PriceBalance;
import se.lexicon.price.component.domain.PriceTransaction;

import java.math.BigDecimal;

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
    public void placeMarketPrice(PlaceMarketPriceRequest request, StreamObserver<PlaceMarketPriceResponse> responseObserver) {
        StreamObserverErrorHandler.of(responseObserver).onError(() -> {
        BigDecimal pr = priceComponentClient.placePrice(request.getInstrument());
        responseObserver.onNext(PlaceMarketPriceResponse.newBuilder().setPrice(Money.newBuilder().setAmount(pr.floatValue()).build()).build());
        responseObserver.onCompleted();
        },"Failed creating instrument request price");
    }

    //    @Override
//    public void createPriceBalance(CreatePriceBalanceRequest apiRequest, StreamObserver<PriceBalance> responseObserver) {
//        StreamObserverErrorHandler.of(responseObserver).execute(() -> {
//            com.seb.price.component.domain.CreatePriceBalanceRequest componentRequest = se.lexicon.price.api.mapper.PriceBalanceApiMapper.map(apiRequest);
//            com.seb.price.component.domain.PriceBalance priceBalance = priceComponentClient.createPriceBalance(componentRequest);
//            com.seb.price.api.PriceBalance apiPriceBalance = se.lexicon.price.api.mapper.PriceBalanceApiMapper.map(priceBalance);
//            responseObserver.onNext(apiPriceBalance);
//            responseObserver.onCompleted();
//        }, "Failed creating API balance");
//    }
//
//    @Override
//    public void createPriceTransaction(CreatePriceTransactionRequest apiRequest, StreamObserver<PriceTransaction> responseObserver) {
//        StreamObserverErrorHandler.of(responseObserver).execute(() -> {
//            com.seb.price.component.domain.CreatePriceTransactionRequest componentRequest = se.lexicon.price.api.mapper.PriceTransactionApiMapper.map(apiRequest);
//            com.seb.price.component.domain.PriceTransaction priceTransaction = priceComponentClient.createPriceTransaction(componentRequest);
//            com.seb.price.api.PriceTransaction apiPriceTransaction = se.lexicon.price.api.mapper.PriceTransactionApiMapper.map(priceTransaction);
//            responseObserver.onNext(apiPriceTransaction);
//            responseObserver.onCompleted();
//        }, "Failed creating API Transaction");
//    }

}
