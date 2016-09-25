package io.usa.doraemon.commons.api.transport.common;

import io.usa.doraemon.commons.api.service.api.ServiceContext;

public class RequestWrapper {

	private Long id;
	private ServiceContext request;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ServiceContext getRequest() {
		return request;
	}

	public void setRequest(ServiceContext request) {
		this.request = request;
	}

 
}
