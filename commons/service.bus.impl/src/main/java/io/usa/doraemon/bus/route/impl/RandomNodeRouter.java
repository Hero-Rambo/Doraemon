package io.usa.doraemon.bus.route.impl;

import io.usa.doraemon.bus.route.AbstractNodeRouter;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.common.bus.model.ServiceNode;

@Component("router.random")
public class RandomNodeRouter extends AbstractNodeRouter {

	@Override
	public ServiceNode getTargetServiceNode(String serviceId) {
		// TODO Auto-generated method stub
		return null;
	}


}
