package io.usa.doraemon.bus.register;

import io.usa.doraemon.commons.api.common.bus.model.BusNodeInfo;


/**
 * 
 * @author Rambo
 *
 */
public interface IBusInfoProvider {
	
	/**
	 * 
	 * @return
	 */
	public BusNodeInfo getBusNodeInfo();
	
}
