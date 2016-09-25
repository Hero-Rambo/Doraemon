package io.usa.doraemon.provider.register.impl;

import java.util.HashMap;
import java.util.Map;

import io.usa.doraemon.commons.api.common.utils.ITransportProvider;
import io.usa.doraemon.provider.register.ISlaveRegisterMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.common.message.register.SlaveRegisterMessage;
import io.usa.doraemon.commons.api.common.utils.IServerInfoProvider;


/**
 * 
 * @author Rambo
 *
 */
@Component
public class SlaveRegisterMessageProvider implements ISlaveRegisterMessageProvider {
	
	private SlaveRegisterMessage serviceNodeInfo;
 	
	@Autowired
	private IServerInfoProvider serverInfoProvider;
	
	@Autowired
	private ITransportProvider transportProvider;
	
	
	@Override
	public SlaveRegisterMessage getSlaveRegisterMessage() {
		if(serviceNodeInfo!=null){
			return serviceNodeInfo;
		}
		init();
		return serviceNodeInfo;
	}
	
	protected void init(){
		serviceNodeInfo = new SlaveRegisterMessage();
		serviceNodeInfo.setHost(serverInfoProvider.getHostName());
		serviceNodeInfo.setUrl(serverInfoProvider.getHostUrl());
		Map<String, Integer> transportInfos = new HashMap<String,Integer>();
		String transportType = transportProvider.getType();
		int port = transportProvider.getPort();
		transportInfos.put(transportType, port);		
		serviceNodeInfo.setTransportInfos(transportInfos);
		serviceNodeInfo.setAvailableTransport(transportType);
	}
	

	@Override
	public void changeStatus(String status) {
		serviceNodeInfo.setStatus(status);
	}
	
}
