package io.usa.doraemon.commons.api.service.locator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.service.api.Service;
import io.usa.doraemon.commons.api.service.locator.IServiceLocator;

@Component("serviceLocator")
public class SpringServiceLocator implements IServiceLocator{
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public Service getService(String serviceName) {
		Object obj = applicationContext.getBean(serviceName);
		if(obj instanceof Service){
			return (Service) obj;	
		}
		throw new RuntimeException("Service name:"+serviceName+" not found!");
	}

	@Override
	public Service getService(String category, String name, String version) {
		StringBuilder sb = new StringBuilder(32);
		sb.append(category);
		sb.append('.');
		sb.append(name);
		sb.append('.');
		sb.append(version);
		Service service = getService(sb.toString());
		return service;
	}

}
