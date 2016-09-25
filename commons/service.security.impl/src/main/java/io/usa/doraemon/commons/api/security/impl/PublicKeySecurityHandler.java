package io.usa.doraemon.commons.api.security.impl;

import org.springframework.stereotype.Component;

import io.usa.doraemon.bus.filter.FilterContext;
import io.usa.doraemon.bus.filter.FilterResult;
import io.usa.doraemon.commons.api.security.AbstractSecurityHandler;

@Component("security.level.3")
public class PublicKeySecurityHandler extends AbstractSecurityHandler{

	@Override
	public FilterResult checkSecurity(FilterContext filterContext) {
		logger.info("security.level.3...........");
		return super.checkSecurity(filterContext);
	}

}
