package se.lexicon.price.api.client;

import com.so4it.api.ApiClientProvider;
import com.so4it.api.Price;


@ApiClientProvider(
        value = Price.NAME,
        specification = Price.PATH,
        version = Price.VERSION,
        beanPublisher = PriceApiProviderBeanPublisher.class)
public class PriceApiProvider {
}
