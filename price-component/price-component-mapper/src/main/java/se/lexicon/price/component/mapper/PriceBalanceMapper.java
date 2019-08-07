package se.lexicon.price.component.mapper;

import se.lexicon.price.component.entity.PriceBalanceEntity;
import se.lexicon.price.component.domain.PriceBalance;
import se.lexicon.price.component.domain.CreatePriceBalanceRequest;
import com.so4it.common.util.Mapper;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public final class PriceBalanceMapper {



    public static PriceBalanceEntity map(CreatePriceBalanceRequest createPriceBalanceRequest){
        return Mapper.of(createPriceBalanceRequest, PriceBalanceEntity::builder)
                .map(CreatePriceBalanceRequest::getArrangementId,PriceBalanceEntity.Builder::withArrangementId)
                .map(CreatePriceBalanceRequest::getBatchId,PriceBalanceEntity.Builder::withBatchId)
                .map(CreatePriceBalanceRequest::getInsertionTimestamp,PriceBalanceEntity.Builder::withInsertionTimestamp)
                .map(CreatePriceBalanceRequest::getBalances,PriceBalanceEntity.Builder::withBalances)
                .build(PriceBalanceEntity.Builder::build);
    }


    public static PriceBalance map(PriceBalanceEntity entity){
        return entity != null ? Mapper.of(entity, PriceBalance::builder)
                .map(PriceBalanceEntity::getId,PriceBalance.Builder::withId)
                .map(PriceBalanceEntity::getArrangementId,PriceBalance.Builder::withArrangementId)
                .map(PriceBalanceEntity::getBatchId,PriceBalance.Builder::withBatchId)
                .map(PriceBalanceEntity::getInsertionTimestamp,PriceBalance.Builder::withInsertionTimestamp)
                .map(PriceBalanceEntity::getBalances,PriceBalance.Builder::withBalances)
                .build(PriceBalance.Builder::build) : null;
    }
}
