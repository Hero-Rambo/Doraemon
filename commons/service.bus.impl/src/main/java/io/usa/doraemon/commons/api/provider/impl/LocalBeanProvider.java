package io.usa.doraemon.commons.api.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import io.usa.doraemon.bus.exception.SeriviceNotFoundException;
import io.usa.doraemon.bus.provider.IBeanProvider;
import io.usa.doraemon.commons.api.service.api.Service;
import io.usa.doraemon.commons.api.service.api.ServiceContext;

@Component("beanProvider")
public class LocalBeanProvider implements IBeanProvider{
	@Autowired
	private ApplicationContext applicationContext;
	
	
	@Override
	public Object getBean(String beanName) {
		if(this.applicationContext.containsBean(beanName)){
			return applicationContext.getBean(beanName);
		}
		throw new RuntimeException("No bean found!Bean name:"+beanName);
	}
	
	@Override
	public Service getService(ServiceContext serviceContext) throws SeriviceNotFoundException {
		StringBuilder sb = new StringBuilder(64);
		sb.append(serviceContext.getCategory());
		sb.append('.');
		sb.append(serviceContext.getName());
		sb.append('.');
		sb.append(serviceContext.getVersion());
		String beanName = sb.toString();
		if(this.applicationContext.containsBean(beanName)){
			throw new SeriviceNotFoundException("No Service found!Service name:"+beanName);
		}
		Service service = (Service) this.getBean(beanName);
		return service;
	}
 

}
