package io.usa.doraemon.commons.api.service.rest.client;

import java.util.Map;

/**
 * 
 * @author Rambo
 * @date Jan 23, 2014
 */
public interface IRestServiceClient {
	
	public String invokeByRest(String appId, String category, String name,
			String version, String params, Map<String, String> headers,String httpMethod)
			throws Exception ;
}


