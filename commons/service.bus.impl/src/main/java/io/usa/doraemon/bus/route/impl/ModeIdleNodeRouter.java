package io.usa.doraemon.bus.route.impl;

import org.springframework.stereotype.Component;

import io.usa.doraemon.bus.route.AbstractNodeRouter;
import io.usa.doraemon.commons.api.common.bus.model.ServiceNode;

@Component("router.mostIdle")
public class ModeIdleNodeRouter extends AbstractNodeRouter{

	@Override
	public ServiceNode getTargetServiceNode(String serviceId) {
		// TODO Auto-generated method stub
		return null;
	}

}
