package io.usa.doraemon.commons.api.common.bus.model;

import java.util.Map;

public class ServiceNode {
	private String url;
	private String host;
	private Map<String,Port> ports ;
	private Port availablePort;
	private int weight;
	private String status;
	private double cpuUsePercent;
	private double memoryUsePercent;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	 
	public Map<String,Port> getPorts() {
		return ports;
	}

	public void setPorts(Map<String,Port> ports) {
		this.ports = ports;
	}

	public boolean isAvailable() {
		return true;
	}

 

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getCpuUsePercent() {
		return cpuUsePercent;
	}

	public void setCpuUsePercent(double cpuUsePercent) {
		this.cpuUsePercent = cpuUsePercent;
	}

	public double getMemoryUsePercent() {
		return memoryUsePercent;
	}

	public void setMemoryUsePercent(double memoryUsePercent) {
		this.memoryUsePercent = memoryUsePercent;
	}
 
 
	public Port getAvailablePort() {
		return availablePort;
	}

	public void setAvailablePort(Port availablePort) {
		this.availablePort = availablePort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

 
	
	
	

}
