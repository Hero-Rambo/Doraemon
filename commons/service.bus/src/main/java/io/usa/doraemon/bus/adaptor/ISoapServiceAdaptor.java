package io.usa.doraemon.bus.adaptor;

import javax.jws.WebService;

/**
 * 
 * @author Rambo
 * @date Jan 23, 2014
 */
@WebService
public interface ISoapServiceAdaptor {
	
	
	public String invoke(String appId, String category, String name,
			String version, String params)  ;
	
}
