package io.usa.doraemon.bus.invoker;

import io.usa.doraemon.commons.api.service.api.ServiceContext;
import io.usa.doraemon.commons.api.service.api.ServiceResult;


/**
 * @author Rambo
 * @date Jan 23, 2014
 */
public interface IServiceInvoker {
	
	ServiceResult invoke(ServiceContext busContext);

	
}
