package io.usa.doraemon.global.node.register.biz.service;

import io.usa.doraemon.commons.api.common.message.register.SlaveRegisterMessage;
import io.usa.doraemon.commons.api.db.jdbc.ILogicService;
import io.usa.doraemon.global.node.register.model.ServiceNode;


/**
 * 
 * @author Rambo
 * @date Jan 22, 2014
 */
public interface IServiceNodeService extends ILogicService<ServiceNode>{

	Object unregister(SlaveRegisterMessage nm);
	
	
	Object register(SlaveRegisterMessage nm);
}
