package io.usa.doraemon.provider.register.client.bus.impl;

import io.usa.doraemon.provider.register.ISlaveRegisterMessageProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.usa.doraemon.commons.api.common.message.register.SlaveRegisterMessage;
import io.usa.doraemon.commons.api.service.api.Service;
import io.usa.doraemon.commons.api.service.api.ServiceContext;
import io.usa.doraemon.provider.register.IServiceInfoProvider;


/**
 * 
 * @author Rambo
 * @2013-12-28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:jbricks/*-beans.xml")
public class ServiceNodeRegisterServiceTest {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private ObjectMapper jackson;
	
	@Autowired
	private IServiceInfoProvider serviceInfoProvider;
	@Autowired
	protected ISlaveRegisterMessageProvider serviceNodeInfoProvider;

 
	
	@Test
	public void testServiceNodeRegistr() throws Exception {
		SlaveRegisterMessage msg =  serviceNodeInfoProvider.getSlaveRegisterMessage();
		msg.setStatus("Start");
		msg.setServices(this.serviceInfoProvider.getServiceInfos());
		ServiceContext context = new ServiceContext();
		String params = jackson.writeValueAsString(msg);
		context.setParams(params);
		Service s = (Service) applicationContext.getBean("registry.ServiceNodeRegisterService.v1");
		s.execute(context);
	}
}
