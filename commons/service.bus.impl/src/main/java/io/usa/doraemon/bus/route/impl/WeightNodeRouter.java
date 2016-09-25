package io.usa.doraemon.bus.route.impl;

import io.usa.doraemon.bus.route.AbstractNodeRouter;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.common.bus.model.ServiceNode;


@Component("router.weight")
public class WeightNodeRouter extends AbstractNodeRouter {

	@Override
	public ServiceNode getTargetServiceNode(String serviceId) {

		return null;
	}

}
