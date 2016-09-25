package io.usa.doraemon.provider.register.bus.impl;

import io.usa.doraemon.commons.api.common.message.register.SlaveRegisterMessage;
import io.usa.doraemon.provider.register.IServiceInfoProvider;
import io.usa.doraemon.provider.register.ISlaveRegisterMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusUnregisterClient {
 

	@Autowired
	protected IServiceInfoProvider serviceInfoProvider;

	@Autowired
	protected ISlaveRegisterMessageProvider serviceNodeInfoProvider;

	 
	protected Object getServiceParams() {
		SlaveRegisterMessage node = serviceNodeInfoProvider.getSlaveRegisterMessage();
		node.setStatus("Stop");
		node.setServices(this.serviceInfoProvider.getServiceInfos());
		return node;
	}
 
	
}
