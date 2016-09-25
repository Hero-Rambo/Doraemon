package io.usa.doraemon.global.node.register.biz.dao;

import java.util.List;

import io.usa.doraemon.global.node.register.model.ServiceInfo;
import io.usa.doraemon.global.node.register.model.ServiceNode;

public interface INodeServiceRepository {

	void register(ServiceNode node, List<ServiceInfo> serviceInfos);
	void unregister(ServiceNode node);
}
