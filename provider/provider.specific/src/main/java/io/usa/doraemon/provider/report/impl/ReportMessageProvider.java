package io.usa.doraemon.provider.report.impl;

import io.usa.doraemon.commons.api.common.message.Message;
import io.usa.doraemon.commons.api.common.message.report.SlaveStatusMessage;
import io.usa.doraemon.commons.api.common.utils.IServerInfoProvider;
import io.usa.doraemon.provider.report.IReportMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Rambo
 *
 */
@Component
public class ReportMessageProvider implements IReportMessageProvider {
	
	@Autowired
	private IServerInfoProvider serverInfoProvider;
	
	
	protected void buildNodeInfo(SlaveStatusMessage message){
		message.setHost(serverInfoProvider.getHostName());
		message.setUrl(serverInfoProvider.getHostUrl());
		message.setMemoryUsagePercent(0.52);
		message.setCpuUsePercent(0.31);
	}
	
	protected void buildNodeStatus(SlaveStatusMessage message){
		message.setStatus("Start");
	}


	@Override
	public Message getInitReportMessage() {
		SlaveStatusMessage message = new SlaveStatusMessage();
		this.buildNodeStatus(message);
		this.buildNodeInfo(message);
		return message;
	}


	@Override
	public Message getStautsReportMessage() {
		SlaveStatusMessage message = new SlaveStatusMessage();
		this.buildNodeInfo(message);		
		this.buildNodeStatus(message);
		return message;
	}

}
