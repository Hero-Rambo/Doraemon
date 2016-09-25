package io.usa.doraemon.provider.transport.impl;

import io.usa.doraemon.commons.api.common.utils.ITransportProvider;

/**
 * 
 * @author Rambo
 * @2013-12-26
 */
	
public class TransportProvider implements ITransportProvider {
	private String type;
	private int port;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
