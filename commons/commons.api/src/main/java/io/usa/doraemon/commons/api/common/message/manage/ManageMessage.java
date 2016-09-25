package io.usa.doraemon.commons.api.common.message.manage;

import io.usa.doraemon.commons.api.common.message.Message;

public class ManageMessage extends Message {

	private String commandType;
	
	private String command;
	
 

	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	

}
