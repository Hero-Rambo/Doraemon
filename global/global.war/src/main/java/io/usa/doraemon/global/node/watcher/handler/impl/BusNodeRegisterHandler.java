package io.usa.doraemon.global.node.watcher.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.usa.doraemon.commons.api.common.node.watcher.IZookeeperMessageHandler;
import io.usa.doraemon.global.node.register.biz.service.IBusNodeService;
import io.usa.doraemon.global.node.register.model.BusNode;
 
/**
 * 
 * @author Rambo
 * @date Jan 26, 2014
 */
@Component
public class BusNodeRegisterHandler implements IZookeeperMessageHandler{
	private Logger logger = LoggerFactory.getLogger(BusNodeRegisterHandler.class);
 
	@Autowired
	private ObjectMapper jackson;
	
	@Autowired
	private IBusNodeService busNodeService;
	

	@Override
	public void handle(String path, String message) {
		logger.info("zookeeper path====="+path);
		logger.info("zookeeper message====="+message);
		try {
			BusNode busNode = jackson.readValue(message, BusNode.class);
			if("Start".equals(busNode.getStatus())){
				busNodeService.register(busNode);
				return;
			}else{
				busNodeService.unregister(busNode);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}

}
