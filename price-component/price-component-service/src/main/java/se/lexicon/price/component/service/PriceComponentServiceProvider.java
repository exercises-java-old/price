package se.lexicon.price.component.service;

import com.so4it.gs.rpc.Service;
import com.so4it.gs.rpc.ServiceBindingType;
import com.so4it.gs.rpc.ServiceProvider;

@ServiceProvider
public interface PriceComponentServiceProvider {

    @Service(value = ServiceBindingType.GS_REMOTING, name = PriceComponentService.DEFAULT_BEAN_NAME)
    PriceComponentService getInvoiceComponentService();


    @Service(value = ServiceBindingType.GS_REMOTING, name = OrderComponentService.DEFAULT_BEAN_NAME)
    OrderComponentService getOrderComponentService();
}
