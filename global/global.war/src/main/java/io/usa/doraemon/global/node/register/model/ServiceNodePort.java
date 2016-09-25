package io.usa.doraemon.global.node.register.model;

public class ServiceNodePort extends AbstractModel{	
	private String serviceNodeId;
	private String transportType;
	private int    port;
	private String status;
	private String description;
	
	
  
 
	public String getServiceNodeId() {
		return serviceNodeId;
	}
	public void setServiceNodeId(String serviceNodeId) {
		this.serviceNodeId = serviceNodeId;
	}
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
