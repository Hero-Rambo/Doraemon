package io.usa.doraemon.global.node.register.model;

public class NodeService extends AbstractModel{
	
	private String serviceNodeId;
	private String serviceId;
	private String status;
	
 
	 
	public String getServiceNodeId() {
		return serviceNodeId;
	}
	public void setServiceNodeId(String serviceNodeId) {
		this.serviceNodeId = serviceNodeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	
	
}
