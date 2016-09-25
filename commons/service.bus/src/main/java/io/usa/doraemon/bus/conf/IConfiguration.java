package io.usa.doraemon.bus.conf;

import java.util.List;


/**
 * 
 * @author Rambo
 * @2014-1-12
 */
public interface IConfiguration {

	List<String> getCommonFiltersChain();

	List<String> getRestFiltersChain() ;

	List<String> getSoapFiltersChain() ;
	
	boolean isRemoteServicePriority();

	String getRouteAlgorithm();
	
	String getServiceLogger();
}
