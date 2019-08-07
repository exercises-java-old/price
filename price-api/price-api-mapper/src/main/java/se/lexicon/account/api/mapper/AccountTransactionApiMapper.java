package se.lexicon.price.api.mapper;

import se.lexicon.price.component.domain.PriceTransaction;
import com.so4it.api.mapper.ValueMapper;
import com.so4it.common.util.Mapper;


public final class PriceTransactionApiMapper {

    public static PriceTransaction map(com.seb.price.api.PriceTransaction priceTransaction){

        return Mapper.of(priceTransaction,PriceTransaction:: builder)
                .map(com.seb.price.api.PriceTransaction::getId,PriceTransaction.Builder::withId)
                .map(com.seb.price.api.PriceTransaction::getArrangementId,PriceTransaction.Builder::withArrangementId)
                .map(com.seb.price.api.PriceTransaction::getBatchId,PriceTransaction.Builder::withBatchId)
                .map(com.seb.price.api.PriceTransaction::getInsertionTimestamp, ValueMapper::map, PriceTransaction.Builder::withInsertionTimestamp)
                .map(com.seb.price.api.PriceTransaction::getAddress, PriceTransaction.Builder::withAddress)
                .map(com.seb.price.api.PriceTransaction::getPhase, PhaseApiMapper::map, PriceTransaction.Builder::withPhase)
                .map(com.seb.price.api.PriceTransaction::getAmount, MoneyApiMapper::map, PriceTransaction.Builder::withAmount)
                .build(PriceTransaction.Builder::build);

    }

    public static  com.seb.price.api.PriceTransaction map(PriceTransaction priceTransaction){
        return Mapper.of(priceTransaction,com.seb.price.api.PriceTransaction::newBuilder)
                .map(PriceTransaction::getId,com.seb.price.api.PriceTransaction.Builder::setId)
                .map(PriceTransaction::getArrangementId,com.seb.price.api.PriceTransaction.Builder::setArrangementId)
                .map(PriceTransaction::getBatchId,com.seb.price.api.PriceTransaction.Builder::setBatchId)
                .map(PriceTransaction::getInsertionTimestamp, ValueMapper::map, com.seb.price.api.PriceTransaction.Builder::setInsertionTimestamp)
                .map(PriceTransaction::getAddress, com.seb.price.api.PriceTransaction.Builder::setAddress)
                .map(PriceTransaction::getPhase, PhaseApiMapper::map, com.seb.price.api.PriceTransaction.Builder::setPhase)
                .map(PriceTransaction::getAmount, MoneyApiMapper::map, com.seb.price.api.PriceTransaction.Builder::setAmount)
                .build(com.seb.price.api.PriceTransaction.Builder::build);

    }



    public static com.seb.price.component.domain.CreatePriceTransactionRequest map(com.seb.price.api.CreatePriceTransactionRequest request){

        return Mapper.of(request,com.seb.price.component.domain.CreatePriceTransactionRequest::builder)
                .map(com.seb.price.api.CreatePriceTransactionRequest::getArrangementId,com.seb.price.component.domain.CreatePriceTransactionRequest.Builder::withArrangementId)
                .map(com.seb.price.api.CreatePriceTransactionRequest::getBatchId,com.seb.price.component.domain.CreatePriceTransactionRequest.Builder::withBatchId)
                .map(com.seb.price.api.CreatePriceTransactionRequest::getInsertionTimestamp, ValueMapper::map, com.seb.price.component.domain.CreatePriceTransactionRequest.Builder::withInsertionTimestamp)
                .map(com.seb.price.api.CreatePriceTransactionRequest::getAddress, com.seb.price.component.domain.CreatePriceTransactionRequest.Builder::withAddress)
                .map(com.seb.price.api.CreatePriceTransactionRequest::getPhase, PhaseApiMapper::map, com.seb.price.component.domain.CreatePriceTransactionRequest.Builder::withPhase)
                .map(com.seb.price.api.CreatePriceTransactionRequest::getAmount, MoneyApiMapper::map, com.seb.price.component.domain.CreatePriceTransactionRequest.Builder::withAmount)
                .build(com.seb.price.component.domain.CreatePriceTransactionRequest.Builder::build);

    }

    public static com.seb.price.api.CreatePriceTransactionRequest map(com.seb.price.component.domain.CreatePriceTransactionRequest request){

        return Mapper.of(request,com.seb.price.api.CreatePriceTransactionRequest::newBuilder)
                .map(com.seb.price.component.domain.CreatePriceTransactionRequest::getArrangementId,com.seb.price.api.CreatePriceTransactionRequest.Builder::setArrangementId)
                .map(com.seb.price.component.domain.CreatePriceTransactionRequest::getBatchId,com.seb.price.api.CreatePriceTransactionRequest.Builder::setBatchId)
                .map(com.seb.price.component.domain.CreatePriceTransactionRequest::getInsertionTimestamp, ValueMapper::map, com.seb.price.api.CreatePriceTransactionRequest.Builder::setInsertionTimestamp)
                .map(com.seb.price.component.domain.CreatePriceTransactionRequest::getAddress,com.seb.price.api.CreatePriceTransactionRequest.Builder::setAddress)
                .map(com.seb.price.component.domain.CreatePriceTransactionRequest::getPhase, PhaseApiMapper::map, com.seb.price.api.CreatePriceTransactionRequest.Builder::setPhase)
                .map(com.seb.price.component.domain.CreatePriceTransactionRequest::getAmount, MoneyApiMapper::map, com.seb.price.api.CreatePriceTransactionRequest.Builder::setAmount)
                .build(com.seb.price.api.CreatePriceTransactionRequest.Builder::build);

    }




}
