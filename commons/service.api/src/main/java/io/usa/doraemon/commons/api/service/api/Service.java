package io.usa.doraemon.commons.api.service.api;

import io.usa.doraemon.commons.api.service.api.exception.ServiceException;



/**
 * 
 * Service的Spring Bean Id如下:category.name.version
 * ServiceId即为:category.name.version
 * 在采用Spring加载的时候,如果Spring Bean Id和ServiceId不一致 会出错。
 * @author Rambo
 *
 */
public interface Service {
	String getCategory();

	String getName();

	String getVersion();

	String getServiceId();

	Object execute(ServiceContext context) throws ServiceException;

}
