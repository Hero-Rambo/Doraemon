package io.usa.doraemon.global.node.register.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.usa.doraemon.commons.api.common.message.manage.service.AddBusNodeCommand;
import io.usa.doraemon.commons.api.common.message.manage.service.RemoveBusNodeCommand;
import io.usa.doraemon.commons.api.db.jdbc.AbstractLogicService;
import io.usa.doraemon.global.node.register.biz.dao.IBusNodeRepositoiry;
import io.usa.doraemon.global.node.register.biz.service.IBusNodeService;
import io.usa.doraemon.global.node.register.model.BusNode;


/**
 * 
 * @author Rambo
 * @date Jan 22, 2014
 */
@Service
public class BusNodeService  extends AbstractLogicService<BusNode> implements IBusNodeService{
	
	@Autowired
	private IBusNodeRepositoiry busNodeRepositoiry;
	
	@Override
	public Object register(BusNode busNode) {
		Object registerResult = busNodeRepositoiry.register(busNode);
		this.sendAddBusNodeCommand(busNode);
		return registerResult;
	}

	@Override
	public Object unregister(BusNode busNode) {
		Object registerResult = busNodeRepositoiry.unregister(busNode);
		this.sendRemoveBusNodeCommand(busNode);
		return registerResult;
	}

	protected void sendAddBusNodeCommand(BusNode busNode) {
		AddBusNodeCommand cmd = new AddBusNodeCommand();	
		cmd.setStatus(busNode.getStatus());
		cmd.setUrl(busNode.getUrl());
	}
	protected void sendRemoveBusNodeCommand(BusNode busNode) { 
		RemoveBusNodeCommand cmd = new RemoveBusNodeCommand();
		cmd.setStatus(busNode.getStatus());
		cmd.setUrl(busNode.getUrl());
	}

}
