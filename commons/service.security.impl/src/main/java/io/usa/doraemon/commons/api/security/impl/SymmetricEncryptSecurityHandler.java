package io.usa.doraemon.commons.api.security.impl;

import org.springframework.stereotype.Component;

import io.usa.doraemon.bus.filter.FilterContext;
import io.usa.doraemon.bus.filter.FilterResult;
import io.usa.doraemon.commons.api.security.AbstractSecurityHandler;


/**
 * 
 * @author Rambo
 *对称加密实现
 *根据应用系统的注册的密码，采用对称加密。
 *应用系统在注册时，注册了密码。该密码用于通信等方面的。
 */
@Component("security.level.2")
public class SymmetricEncryptSecurityHandler extends AbstractSecurityHandler{

	@Override
	public FilterResult checkSecurity(FilterContext filterContext) {
		logger.info("security.level.2...........");
		return null;
	}

}
