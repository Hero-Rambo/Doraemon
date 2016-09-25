package io.usa.doraemon.provider.register.impl;

import org.springframework.beans.factory.annotation.Autowired;

import io.usa.doraemon.commons.api.common.message.register.ApplicationMetadata;
import io.usa.doraemon.commons.api.common.utils.IServerInfoProvider;
import io.usa.doraemon.provider.register.IApplicationInfoProvider;


/**
 * 
 * @author Rambo
 *
 */
public class ApplicationInfoProvider implements IApplicationInfoProvider {
	@Autowired
	private IServerInfoProvider serverInfoProvider;
	
	
	@Override
	public ApplicationMetadata getApplicationMetadata() {
		ApplicationMetadata am = new ApplicationMetadata();
		am.setAppId("paas.provider");
		am.setContextRoot(serverInfoProvider.getServerInfo().getContextRoot());
		am.setDisplayName("Paas Service Slave Node");
		am.setPassword("111");
		return am;
	}

}
