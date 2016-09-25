package io.usa.doraemon.bus.route;

import io.usa.doraemon.commons.api.common.bus.model.ServiceNode;
import io.usa.doraemon.commons.api.service.api.ServiceContext;



public interface INodeRouter {	
	
	ServiceNode getTargetServiceNode(String serviceId);

	ServiceNode getTargetServiceNode(ServiceContext context);
	
}
