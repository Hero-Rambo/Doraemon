package io.usa.doraemon.bus.provider;

import io.usa.doraemon.bus.exception.SeriviceNotFoundException;
import io.usa.doraemon.commons.api.service.api.Service;
import io.usa.doraemon.commons.api.service.api.ServiceContext;

public interface IBeanProvider {
	Object getBean(String beanName);

	Service getService(ServiceContext context) throws SeriviceNotFoundException;
}
