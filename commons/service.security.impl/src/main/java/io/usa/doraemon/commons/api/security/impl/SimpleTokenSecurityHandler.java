package io.usa.doraemon.commons.api.security.impl;

import org.springframework.stereotype.Component;

import io.usa.doraemon.bus.filter.FilterContext;
import io.usa.doraemon.bus.filter.FilterResult;
import io.usa.doraemon.commons.api.security.AbstractSecurityHandler;

/**
 * 使用token进行安全验证，token=appId
 * @author Rambo
 *
 */
@Component("security.level.1")
public class SimpleTokenSecurityHandler  extends AbstractSecurityHandler{

	@Override
	public FilterResult checkSecurity(FilterContext filterContext) {		
		logger.info("security.level.1.........................");
		return null;
	}

}
