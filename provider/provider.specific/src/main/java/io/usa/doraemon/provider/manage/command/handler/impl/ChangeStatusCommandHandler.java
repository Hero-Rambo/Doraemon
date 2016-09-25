package io.usa.doraemon.provider.manage.command.handler.impl;

import io.usa.doraemon.provider.register.ISlaveRegisterMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.usa.doraemon.commons.api.common.message.manage.service.ChangeStatusCommand;

@Component("command.handler.ChangeStatusCommand")
public class ChangeStatusCommandHandler  {

	@Autowired
	private ISlaveRegisterMessageProvider serviceNodeInfoProvider;
	
	@Autowired
	private ObjectMapper jackson;

	public Object handle(String command) {
		try {
			ChangeStatusCommand changeStatus = jackson.readValue(command, ChangeStatusCommand.class);
			if(changeStatus.getServiceNodeUrls()!=null){
				if(changeStatus.getServiceNodeUrls().equals(serviceNodeInfoProvider.getSlaveRegisterMessage().getUrl())){
					serviceNodeInfoProvider.changeStatus(changeStatus.getStatus());
				}
			}			 
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error:"+e.getMessage();
		}
	}

}
