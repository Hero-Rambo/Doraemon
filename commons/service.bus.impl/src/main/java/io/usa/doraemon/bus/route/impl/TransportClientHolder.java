package io.usa.doraemon.bus.route.impl;

import java.util.HashMap;
import java.util.Map;

import io.usa.doraemon.bus.provider.IBeanProvider;
import io.usa.doraemon.bus.route.ITransportClientHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.common.bus.model.Port;
import io.usa.doraemon.commons.api.common.bus.model.ServiceNode;
import io.usa.doraemon.commons.api.transport.client.ITransportClient;

@Component
public class TransportClientHolder implements ITransportClientHolder {

	private Map<String,ITransportClient> nodeTransportClients = new HashMap<String,ITransportClient>();	
	
	@Autowired
	private IBeanProvider beanProvider;
	
	@Override
	public ITransportClient getServiceClient(Port port, ServiceNode node) {
		ITransportClient transportClient = nodeTransportClients.get(port.getTransportType());
		if(transportClient==null){
			transportClient = this.initTransportClient(port, node);
			nodeTransportClients.put(port.getTransportType(), transportClient);
		}
		return transportClient;
//		if(tcs == null){
//			tcs = new HashMap<String,ITransportClient>();
//			ITransportClient tc = this.initTransportClient(port, node);
//			tcs.put(port.getTransportType(), tc);
//			return tc;
//		}else{
//			ITransportClient tc = tcs.get(port.getTransportType());
//			if(tc==null){
//				tc = this.initTransportClient(port, node);
//				tcs.put(port.getTransportType(), tc);
//				return tc;
//			}
//			return tc;
//		}		
	}
	
	
	protected ITransportClient initTransportClient(Port port,ServiceNode node){
		ITransportClient tc = (ITransportClient) beanProvider.getBean("transport.client."+port.getTransportType());
		System.out.println("port="+port.getPort()+",host="+node.getHost());
		tc.setPort(port.getPort());
		tc.setHost(node.getHost());
		return tc;
	}
}
