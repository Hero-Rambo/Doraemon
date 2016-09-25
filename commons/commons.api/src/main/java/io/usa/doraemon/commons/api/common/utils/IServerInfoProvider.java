package io.usa.doraemon.commons.api.common.utils;

import org.springframework.web.context.ServletContextAware;


/**
 * 
 * @author Rambo
 * @2013-12-26
 */
public interface IServerInfoProvider extends ServletContextAware{

	ServerInfo getServerInfo();
	
	String getHostUrl() ;

	String getHostName();
}
