package io.usa.doraemon.provider.report;

import io.usa.doraemon.commons.api.common.message.Message;

/**
 * 
 * @author Rambo
 * @date Jan 22, 2014
 */
public interface IReportMessageProvider {
	
	Message getInitReportMessage();
	
	Message getStautsReportMessage();
	
}
