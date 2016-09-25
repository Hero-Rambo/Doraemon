package io.usa.doraemon.global.node.register.biz.dao;

import java.util.List;

import io.usa.doraemon.global.node.register.model.ServiceNode;
import io.usa.doraemon.global.node.register.model.ServiceNodePort;

public interface IServiceNodePortRepository {

	void register(List<ServiceNodePort> nodePorts);
	
	void unregister(ServiceNode node);

}
