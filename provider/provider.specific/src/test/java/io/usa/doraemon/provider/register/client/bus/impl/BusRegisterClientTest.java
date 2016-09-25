package io.usa.doraemon.provider.register.client.bus.impl;

import io.usa.doraemon.provider.register.bus.impl.BusRegisterClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:jbricks/*-beans.xml")
public class BusRegisterClientTest {
	@Autowired
    BusRegisterClient busRegisterClient;
	
	
	@Test
	public void test()throws Exception{
//		busRegisterClient.invokeServiceOverHttp();
		
//		System.out.println(busRegisterClient.getDestinationProvider().getDestinations().get(0).getUrl());
	}
}
