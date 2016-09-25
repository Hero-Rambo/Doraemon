package io.usa.doraemon.bus.route;

import io.usa.doraemon.commons.api.common.bus.model.ServiceNode;
import io.usa.doraemon.commons.api.service.api.ServiceContext;


abstract public class AbstractNodeRouter implements INodeRouter{

	@Override
	public ServiceNode getTargetServiceNode(ServiceContext context) {
		return this.getTargetServiceNode(context.getCategory()+'.'+context.getName()+'.'+context.getVersion());
	}

}
