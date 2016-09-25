package io.usa.doraemon.bus.register.impl;

import io.usa.doraemon.bus.register.IBusInfoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.common.bus.model.BusNodeInfo;
import io.usa.doraemon.commons.api.common.utils.IServerInfoProvider;


/**
 * 
 * @author Rambo
 *
 */
@Component
public class BusInfoProvider implements IBusInfoProvider {
	@Autowired
	private IServerInfoProvider serverInfoProvider;
	
	public BusNodeInfo getBusNodeInfo(){
		BusNodeInfo info = new BusNodeInfo();
		info.setUrl(serverInfoProvider.getHostUrl());
		info.setHost(serverInfoProvider.getHostName());
		info.setName(serverInfoProvider.getHostName());
		return info;
	}
}
