package io.usa.doraemon.bus.logger.impl;

import io.usa.doraemon.bus.filter.FilterContext;
import io.usa.doraemon.bus.log.AbstractServiceLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultServiceLogger extends AbstractServiceLogger {
	private Logger logger = LoggerFactory.getLogger(DefaultServiceLogger.class);
	@Override
	public void log(FilterContext context) {
		logger.info("appId==="+context.getAppId());
		logger.info("category==="+context.getCategory());
		logger.info("name==="+context.getName());
		logger.info("version==="+context.getVersion());
		logger.info("params==="+context.getParams());
	}

}
