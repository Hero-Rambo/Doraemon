package io.usa.doraemon.commons.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.usa.doraemon.bus.filter.FilterContext;
import io.usa.doraemon.bus.filter.FilterResult;
import io.usa.doraemon.bus.security.ISecurityHandler;

public class AbstractSecurityHandler implements ISecurityHandler{
	protected Logger logger = LoggerFactory.getLogger(AbstractSecurityHandler.class);
	@Override
	public FilterResult checkSecurity(FilterContext filterContext) {
		
		return null;
	}

}
