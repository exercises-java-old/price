package se.lexicon.price.api.mapper;

import se.lexicon.price.component.domain.Balance;
import com.so4it.common.util.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
public final class BalanceApiMapper {

    public static Set<Balance> map(Collection<com.seb.price.api.Balance> balances) {
        return balances.stream().map(BalanceApiMapper::map).collect(Collectors.toSet());
    }

    public static List<com.seb.price.api.Balance> map(Set<Balance> balances) {
        return balances.stream().map(BalanceApiMapper::map).collect(Collectors.toList());
    }


    public static Balance map(com.seb.price.api.Balance balance) {
        return Mapper.of(balance, Balance::builder)
                .map(com.seb.price.api.Balance::getAddress, Balance.Builder::withAddress)
                .map(com.seb.price.api.Balance::getAmount, MoneyApiMapper::map, Balance.Builder::withAmount)
                .map(com.seb.price.api.Balance::getPhase, PhaseApiMapper::map, Balance.Builder::withPhase)
                .build(Balance.Builder::build);
    }

    public static com.seb.price.api.Balance map(Balance balance) {
        return Mapper.of(balance, com.seb.price.api.Balance::newBuilder)
                .map(Balance::getAddress, com.seb.price.api.Balance.Builder::setAddress)
                .map(Balance::getAmount, MoneyApiMapper::map, com.seb.price.api.Balance.Builder::setAmount)
                .map(Balance::getPhase, PhaseApiMapper::map, com.seb.price.api.Balance.Builder::setPhase)
                .build(com.seb.price.api.Balance.Builder::build);
    }


}
