package io.usa.doraemon.bus.register.impl;

import io.usa.doraemon.bus.register.AbstractBusRegisterClient;
import io.usa.doraemon.bus.register.IBusInfoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.common.bus.model.BusNodeInfo;



/**
 * 
 * @author Rambo
 *
 */
@Component
public class RegisterClient extends AbstractBusRegisterClient {

	@Autowired
	private IBusInfoProvider busInfoProvider;
	
	 
	protected Object getServiceParams() {
		BusNodeInfo busNode = busInfoProvider.getBusNodeInfo();
		busNode.setStatus("Start");
		return busNode;
	}

 
  
}
