package io.usa.doraemon.commons.api.common.message.manage.service;

import java.util.List;

public class ChangeStatusCommand {
	private String status;
	
	private List<String> serviceNodeUrls;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getServiceNodeUrls() {
		return serviceNodeUrls;
	}

	public void setServiceNodeUrls(List<String> serviceNodeUrls) {
		this.serviceNodeUrls = serviceNodeUrls;
	}
	
	
	
}
