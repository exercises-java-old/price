package se.lexicon.price.api.client;

import com.so4it.api.Price;
import com.so4it.api.ApiClientProvider;

@ApiClientProvider(
        value = Price.NAME,
        specification = Price.PATH,
        version = Price.VERSION,
        beanPublisher = PriceApiProviderBeanPublisher.class)
public class PriceApiProvider {
}
