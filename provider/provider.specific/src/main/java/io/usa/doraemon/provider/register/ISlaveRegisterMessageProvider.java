package io.usa.doraemon.provider.register;

import io.usa.doraemon.commons.api.common.message.register.SlaveRegisterMessage;

/**
 * 
 * @author Rambo
 * 
 */
public interface ISlaveRegisterMessageProvider {

	SlaveRegisterMessage getSlaveRegisterMessage();

	void changeStatus(String status);
}
