package io.usa.doraemon.provider.register.registry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.common.message.register.SlaveRegisterMessage;
import io.usa.doraemon.provider.register.IServiceInfoProvider;
import io.usa.doraemon.provider.register.ISlaveRegisterMessageProvider;

@Component
public class RegistryRegisterClient  {

	@Autowired
	protected IServiceInfoProvider serviceInfoProvider;

	@Autowired
	protected ISlaveRegisterMessageProvider serviceNodeInfoProvider;
	 
	protected Object getServiceParams() {
		SlaveRegisterMessage node = serviceNodeInfoProvider.getSlaveRegisterMessage();
		node.setStatus("Start");
		node.setServices(this.serviceInfoProvider.getServiceInfos());
		return node;
	}
	
	 
}
