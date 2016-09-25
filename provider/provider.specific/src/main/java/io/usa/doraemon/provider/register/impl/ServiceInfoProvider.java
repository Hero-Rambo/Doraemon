package io.usa.doraemon.provider.register.impl;

import java.util.ArrayList;
import java.util.List;

import io.usa.doraemon.commons.api.common.message.register.ServiceMetadata;
import io.usa.doraemon.commons.api.service.api.Service;
import io.usa.doraemon.provider.register.IServiceInfoProvider;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Rambo
 *
 */
@Component
public class ServiceInfoProvider implements IServiceInfoProvider {
	
	private  Logger logger = LoggerFactory.getLogger(ServiceInfoProvider.class);

	@Autowired
	private ApplicationContext applicationContext;
	
	
	@Override
	public List<ServiceMetadata> getServiceInfos() {
		List<ServiceMetadata> sms = new ArrayList<ServiceMetadata>();

		String[]serviceNames = applicationContext.getBeanNamesForType(Service.class);
		for(String serviceName:serviceNames){
			logger.info("deployed service name=="+serviceName);
			String[] serviceNameArray = StringUtils.split(serviceName, ".");
			ServiceMetadata sm = new ServiceMetadata();
			sm.setCategory(serviceNameArray[0]);
			sm.setName(serviceNameArray[1]);
			sm.setVersion(serviceNameArray[2]);
			sms.add(sm);
		}
		return sms;
	}
	
  

}
