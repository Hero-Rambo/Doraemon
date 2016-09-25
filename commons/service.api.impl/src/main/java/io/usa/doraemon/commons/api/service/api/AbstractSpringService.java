package io.usa.doraemon.commons.api.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;

/**
 * 
 * @author Rambo
 * @date Jan 23, 2014
 */
abstract public class AbstractSpringService extends AbstractService implements BeanNameAware{
	
	protected Logger logger = LoggerFactory.getLogger(AbstractSpringService.class);
	
	@Override
	public void setBeanName(String beanName) {
		if(!beanName.equals(this.getServiceId())){
			throw new ServiceNameNotMatchException("Service defined name:"+this.getServiceId()+",but deployed name is:"+beanName);
		}
		
		logger.info("Service:"+this.getServiceId()+" is deployed.................");
	}



}
