package io.usa.doraemon.commons.api.transport.client;

import io.usa.doraemon.commons.api.common.exception.TransportException;
import io.usa.doraemon.commons.api.transport.common.RequestWrapper;


/**
 * 
 * @author Rambo
 * @date Jan 23, 2014
 */
public interface ITransportClient {
//	String transport(String message) throws Exception;
 

	String transport(RequestWrapper requestWrapper) throws TransportException;
	
	void setPort(int port);
	
	void setHost(String host);
	
}
