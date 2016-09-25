package io.usa.doraemon.bus.node;

import java.util.List;

import io.usa.doraemon.commons.api.common.bus.model.ServiceNode;

public interface IServiceDeployNodeProvider {

	List<ServiceNode> getServiceDeployNodes(String serviceId);
}
