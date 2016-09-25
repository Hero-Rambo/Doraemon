package io.usa.doraemon.global.node.register.biz.service;

import io.usa.doraemon.global.node.register.model.BusNode;

/**
 * 
 * @author Rambo
 * @date Jan 22, 2014
 */
public interface IBusNodeService {
	
	Object register(BusNode busNode);

	Object unregister(BusNode busNode);
}
