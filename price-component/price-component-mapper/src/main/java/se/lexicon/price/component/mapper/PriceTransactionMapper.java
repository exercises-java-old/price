package se.lexicon.price.component.mapper;

import se.lexicon.price.component.entity.PriceTransactionEntity;
import se.lexicon.price.component.domain.PriceTransaction;
import se.lexicon.price.component.domain.CreatePriceTransactionRequest;
import com.so4it.common.util.Mapper;


public final class PriceTransactionMapper {



    public static PriceTransactionEntity map(CreatePriceTransactionRequest createPriceTransactionRequest){
        return Mapper.of(createPriceTransactionRequest, PriceTransactionEntity::builder)
                .map(CreatePriceTransactionRequest::getArrangementId,PriceTransactionEntity.Builder::withArrangementId)
                .map(CreatePriceTransactionRequest::getBatchId,PriceTransactionEntity.Builder::withBatchId)
                .map(CreatePriceTransactionRequest::getInsertionTimestamp,PriceTransactionEntity.Builder::withInsertionTimestamp)
                .map(CreatePriceTransactionRequest::getAddress,PriceTransactionEntity.Builder::withAddress)
                .map(CreatePriceTransactionRequest::getPhase,PriceTransactionEntity.Builder::withPhase)
                .map(CreatePriceTransactionRequest::getAmount, PriceTransactionEntity.Builder::withAmount)
                .build(PriceTransactionEntity.Builder::build);
    }


    public static PriceTransaction map(PriceTransactionEntity entity){
        return entity != null ? Mapper.of(entity, PriceTransaction::builder)
                .map(PriceTransactionEntity::getId,PriceTransaction.Builder::withId)
                .map(PriceTransactionEntity::getArrangementId,PriceTransaction.Builder::withArrangementId)
                .map(PriceTransactionEntity::getBatchId,PriceTransaction.Builder::withBatchId)
                .map(PriceTransactionEntity::getInsertionTimestamp,PriceTransaction.Builder::withInsertionTimestamp)
                .map(PriceTransactionEntity::getAddress,PriceTransaction.Builder::withAddress)
                .map(PriceTransactionEntity::getPhase,PriceTransaction.Builder::withPhase)
                .map(PriceTransactionEntity::getAmount,PriceTransaction.Builder::withAmount)
                .build(PriceTransaction.Builder::build) : null;
    }
}
