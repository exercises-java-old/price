package se.lexicon.price.api.client;

import com.so4it.api.AbstractApiClientProviderBeanPublisher;
import com.so4it.api.ApiFrameworkConfiguration;
import com.so4it.api.importer.ApiClientProviderDefinition;
import com.so4it.api.util.StatusRuntimeExceptionBeanProxy;
import com.so4it.common.bean.BeanContext;
import com.so4it.common.bean.BeanProxyInvocationHandler;
import com.so4it.metric.springframework.MetricsTimerBeanProxy;
import com.so4it.request.core.RequestContextBeanProxy;
import io.grpc.ManagedChannel;
import se.lexicon.price.PriceApiServiceGrpc;


public class PriceApiProviderBeanPublisher extends AbstractApiClientProviderBeanPublisher {

    @Override
    public void publish(ApiClientProviderDefinition apiProviderDefinition,
                        BeanContext beanContext,
                        ApiFrameworkConfiguration configuration,
                        ManagedChannel managedChannel) {
        PriceApiServiceGrpc.PriceApiServiceBlockingStub agreementApiServiceBlockingStub = PriceApiServiceGrpc.newBlockingStub(managedChannel);
        PriceApiClient priceApiClientImpl = new PriceApiClientImpl(agreementApiServiceBlockingStub);
        PriceApiClient priceApiClient = BeanProxyInvocationHandler.create(
                PriceApiClient.class,
                priceApiClientImpl,
                StatusRuntimeExceptionBeanProxy.create(),
                MetricsTimerBeanProxy.create(priceApiClientImpl),
                //FaultTolerantBeanProxy.create(priceApiClientImpl, beanContext),
                RequestContextBeanProxy.create());
        beanContext.register(PriceApiClient.DEFAULT_API_BEAN_NAME, priceApiClient);
    }

}
