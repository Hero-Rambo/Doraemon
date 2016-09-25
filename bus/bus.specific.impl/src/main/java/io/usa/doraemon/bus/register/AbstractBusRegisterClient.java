package io.usa.doraemon.bus.register;

import io.usa.doraemon.commons.api.service.api.ServiceContext;


/**
 * 
 * @author Rambo
 *
 */
abstract public class AbstractBusRegisterClient {
	protected ServiceContext getTargetService() {
		ServiceContext sm = new ServiceContext();
		sm.setCategory("registry");
		sm.setName("BusNodeRegisterService");
		sm.setVersion("v1");
		return sm;
	}
 
	public void handleResult(Object result) {
 		
	}
	
	
	 
	protected String getAppId() {
		return "paas.bus";
	}
 
	protected String getAppToken() {
		// TODO Auto-generated method stub
		return null;
	}


}
