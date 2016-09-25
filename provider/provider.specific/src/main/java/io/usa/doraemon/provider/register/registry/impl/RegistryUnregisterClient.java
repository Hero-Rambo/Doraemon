package io.usa.doraemon.provider.register.registry.impl;

import io.usa.doraemon.provider.register.IServiceInfoProvider;
import io.usa.doraemon.provider.register.ISlaveRegisterMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.common.message.register.SlaveRegisterMessage;

@Component
public class RegistryUnregisterClient  {
	@Autowired
	protected IServiceInfoProvider serviceInfoProvider;

	@Autowired
	protected ISlaveRegisterMessageProvider serviceNodeInfoProvider;
 
	protected Object getServiceParams() {
		SlaveRegisterMessage node = serviceNodeInfoProvider.getSlaveRegisterMessage();
		node.setStatus("Stop");
		return node;
	}
 
	  
	
}
