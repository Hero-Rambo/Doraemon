package io.usa.doraemon.bus.route;

import io.usa.doraemon.commons.api.common.bus.model.Port;
import io.usa.doraemon.commons.api.common.bus.model.ServiceNode;
import io.usa.doraemon.commons.api.transport.client.ITransportClient;

public interface ITransportClientHolder {
	
	ITransportClient getServiceClient(Port port, ServiceNode node);
}
