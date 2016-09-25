package io.usa.doraemon.manage.client;

import io.usa.doraemon.commons.api.service.api.ServiceContext;

/**
 * 
 * @author Rambo
 *
 */
public interface ICommandSender {
	void setTargetService(ServiceContext targetService);
		
	void setServiceParams(Object serviceParams) ;
	
	void invokeServiceOverHttp() throws Exception;	
	
}
