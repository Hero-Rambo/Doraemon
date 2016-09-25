package io.usa.doraemon.global.node.watcher.handler.impl;

import io.usa.doraemon.global.node.register.biz.service.IServiceNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.usa.doraemon.commons.api.common.message.register.SlaveRegisterMessage;
import io.usa.doraemon.commons.api.common.node.watcher.IZookeeperMessageHandler;

/**
 * 
 * @author Rambo
 * @date Jan 26, 2014
 */
@Component
public class SlaveNodeRegisterHandler implements IZookeeperMessageHandler {
	private Logger logger = LoggerFactory.getLogger(SlaveNodeRegisterHandler.class);

	@Autowired
	private ObjectMapper jackson;

	@Autowired
	private IServiceNodeService serviceNodeService;


	@Override
	public void handle(String path, String message) {
		try {
			SlaveRegisterMessage node = jackson.readValue(message,SlaveRegisterMessage.class);
			if("Stop".equals(node.getStatus())){
				serviceNodeService.unregister(node);
				return ;
			}else{
				serviceNodeService.register(node);	
				return ;
			}			
 		} catch (Exception e) {
 			logger.info("An exception occured!",e);
		}			
	}

}
