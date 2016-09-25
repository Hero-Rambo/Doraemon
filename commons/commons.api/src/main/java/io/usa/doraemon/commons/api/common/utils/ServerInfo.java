package io.usa.doraemon.commons.api.common.utils;

/**
 * 
 * @author Rambo
 * @2013-12-26
 */
public class ServerInfo {
	private String hostName;
	private String ip;
	private int port;
	private String contextRoot;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getContextRoot() {
		return contextRoot;
	}

	public void setContextRoot(String contextRoot) {
		this.contextRoot = contextRoot;
	}

	
	
}
