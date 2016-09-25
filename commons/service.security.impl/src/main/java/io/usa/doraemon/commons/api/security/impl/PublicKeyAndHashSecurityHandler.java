package io.usa.doraemon.commons.api.security.impl;

import org.springframework.stereotype.Component;

import io.usa.doraemon.bus.filter.FilterContext;
import io.usa.doraemon.bus.filter.FilterResult;
import io.usa.doraemon.commons.api.security.AbstractSecurityHandler;

/**
 * 采用公钥机制加密，并使用一致性保护的。
 * 应用系统在注册时，注册了公钥。
 * 通过应用系统的注册信息可以获取到公钥。
 * @author Rambo
 *
 */
@Component("security.level.4")
public class PublicKeyAndHashSecurityHandler extends AbstractSecurityHandler{

	@Override
	public FilterResult checkSecurity(FilterContext filterContext) {
		logger.info("security.level.4...........");
		FilterResult  result = new FilterResult();
		result.setPass(false);
		result.setErrorMessage("error.11");
		result.setErrorMessage("errorMessage");
//		return result;
		return null;
	}

}
