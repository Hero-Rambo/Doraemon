package io.usa.doraemon.manage.client.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.service.api.ServiceContext;

/**
 * 
 * @author Rambo
 * 
 */
@Component
@Scope("prototype")
public class CommandClient     {
	private ServiceContext targetService;

	private Object serviceParams;

	public void setTargetService(ServiceContext targetService) {
		this.targetService = targetService;
	}

	public void setServiceParams(Object serviceParams) {
		this.serviceParams = serviceParams;
	}

	protected ServiceContext getTargetService() {
		return targetService;
	}

	protected Object getServiceParams() {
		return serviceParams;
	}

	public void handleResult(Object result) {

	}
 

	protected String getAppToken() {
		return null;
	}
 
	protected String getAppId() {
		return null;
	}

}
