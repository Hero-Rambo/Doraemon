package io.usa.doraemon.bus.security;

import io.usa.doraemon.bus.filter.FilterContext;
import io.usa.doraemon.bus.filter.FilterResult;

/**
 * ISecurityHandler 
 * @author Rambo
 *
 */
public interface ISecurityHandler {
	FilterResult checkSecurity(FilterContext filterContext);
}
