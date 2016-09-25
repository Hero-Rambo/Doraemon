package io.usa.doraemon.bus.filter;


/**
 * IBusFilter的实现类定义Spring Bean ID的规则如下形式：bus.filter.${beanname}
 * @author Rambo
 *
 */
public interface IBusFilter { 
	FilterResult doFilter(FilterContext filterContext);
}
