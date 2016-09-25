package io.usa.doraemon.commons.api.common.message.manage.bus;

import io.usa.doraemon.commons.api.common.message.manage.Command;

public class RegisterCommand extends Command {

	@Override
	public String getType() {
		return "Register";
	}

}
