package se.lexicon.price.api.mapper;

import se.lexicon.price.component.domain.PriceBalance;
import com.so4it.api.mapper.ValueMapper;
import com.so4it.common.util.Mapper;


/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public final class PriceBalanceApiMapper {

    public static PriceBalance map(com.seb.price.api.PriceBalance priceBalance){

        return Mapper.of(priceBalance,PriceBalance:: builder)
                .map(com.seb.price.api.PriceBalance::getId,PriceBalance.Builder::withId)
                .map(com.seb.price.api.PriceBalance::getArrangementId,PriceBalance.Builder::withArrangementId)
                .map(com.seb.price.api.PriceBalance::getBatchId,PriceBalance.Builder::withBatchId)
                .map(com.seb.price.api.PriceBalance::getInsertionTimestamp, ValueMapper::map, PriceBalance.Builder::withInsertionTimestamp)
                .map(com.seb.price.api.PriceBalance::getBalanceList,BalanceApiMapper::map,PriceBalance.Builder::withBalances)
                .build(PriceBalance.Builder::build);

    }

    public static  com.seb.price.api.PriceBalance map(PriceBalance priceBalance){
        return Mapper.of(priceBalance,com.seb.price.api.PriceBalance::newBuilder)
                .map(PriceBalance::getId,com.seb.price.api.PriceBalance.Builder::setId)
                .map(PriceBalance::getArrangementId,com.seb.price.api.PriceBalance.Builder::setArrangementId)
                .map(PriceBalance::getBatchId,com.seb.price.api.PriceBalance.Builder::setBatchId)
                .map(PriceBalance::getInsertionTimestamp, ValueMapper::map, com.seb.price.api.PriceBalance.Builder::setInsertionTimestamp)
                .map(PriceBalance::getBalances,BalanceApiMapper::map,com.seb.price.api.PriceBalance.Builder::addAllBalance)
                .build(com.seb.price.api.PriceBalance.Builder::build);

    }




    public static com.seb.price.component.domain.CreatePriceBalanceRequest map(com.seb.price.api.CreatePriceBalanceRequest request){

        return Mapper.of(request,com.seb.price.component.domain.CreatePriceBalanceRequest::builder)
                .map(com.seb.price.api.CreatePriceBalanceRequest::getArrangementId,com.seb.price.component.domain.CreatePriceBalanceRequest.Builder::withArrangementId)
                .map(com.seb.price.api.CreatePriceBalanceRequest::getBatchId,com.seb.price.component.domain.CreatePriceBalanceRequest.Builder::withBatchId)
                .map(com.seb.price.api.CreatePriceBalanceRequest::getInsertionTimestamp, ValueMapper::map, com.seb.price.component.domain.CreatePriceBalanceRequest.Builder::withInsertionTimestamp)
                .map(com.seb.price.api.CreatePriceBalanceRequest::getBalanceList,BalanceApiMapper::map,com.seb.price.component.domain.CreatePriceBalanceRequest.Builder::withBalances)
                .build(com.seb.price.component.domain.CreatePriceBalanceRequest.Builder::build);

    }

    public static com.seb.price.api.CreatePriceBalanceRequest map(com.seb.price.component.domain.CreatePriceBalanceRequest request){

        return Mapper.of(request,com.seb.price.api.CreatePriceBalanceRequest::newBuilder)
                .map(com.seb.price.component.domain.CreatePriceBalanceRequest::getArrangementId,com.seb.price.api.CreatePriceBalanceRequest.Builder::setArrangementId)
                .map(com.seb.price.component.domain.CreatePriceBalanceRequest::getBatchId,com.seb.price.api.CreatePriceBalanceRequest.Builder::setBatchId)
                .map(com.seb.price.component.domain.CreatePriceBalanceRequest::getInsertionTimestamp, ValueMapper::map, com.seb.price.api.CreatePriceBalanceRequest.Builder::setInsertionTimestamp)
                .map(com.seb.price.component.domain.CreatePriceBalanceRequest::getBalances,BalanceApiMapper::map,com.seb.price.api.CreatePriceBalanceRequest.Builder::addAllBalance)
                .build(com.seb.price.api.CreatePriceBalanceRequest.Builder::build);

    }

}
