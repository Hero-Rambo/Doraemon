package io.doraemon.business.demo.service.echo.v1.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.doraemon.business.demo.service.test.v1.impl.TestService;
import io.usa.doraemon.commons.api.service.api.AbstractSpringService;
import io.usa.doraemon.commons.api.service.api.ServiceContext;
import io.usa.doraemon.commons.api.service.api.exception.ServiceException;

/**
 * 
 * @author Rambo
 * @2013-12-27
 */
@Service("echo.EchoService.v1")
public class EchoService extends AbstractSpringService{
	private static Logger LOG = LoggerFactory.getLogger(TestService.class);

	@Override
	public String getCategory() {
		return "echo";
	}

	@Override
	public String getName() {
		return "EchoService";
	}

	@Override
	public String getVersion() {
		return "v1";
	}

	@Override
	public Object execute(ServiceContext context) throws ServiceException {
		LOG.info("service params ==="+context.getParams());
		return  context.getParams();
	}

}
