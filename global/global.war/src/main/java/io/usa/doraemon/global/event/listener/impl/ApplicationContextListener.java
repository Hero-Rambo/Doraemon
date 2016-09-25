package io.usa.doraemon.global.event.listener.impl;

import org.apache.curator.framework.api.CuratorWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.zookeeper.client.ZookeeperClient;
import io.usa.doraemon.commons.zookeeper.client.ZookeeperClientException;
 

/**
 * 
 * @author Rambo
 *
 */
@Component
public class ApplicationContextListener implements ApplicationListener<ApplicationEvent> {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	@Autowired
	private ZookeeperClient client;	
	
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			logger.info("begin register bus...................." );
			start();
			logger.info("end register bus...................." );
		}

		if (event instanceof ContextClosedEvent) {
			logger.info("begin unregister bus...................." );
			stop();
			logger.info("end unregister bus...................." );
		}

	}
 
	protected void start() {
		registerWatcher();
	}
 
	
	protected void registerWatcher() {
		String busPath = "/microservice/bus";
		String slavePath = "/microservice/provider";
		CuratorWatcher watcher = (CuratorWatcher) applicationContext.getBean("globalNodeWatcher");
		try {
			client.reigsterWather(busPath, watcher);
			client.reigsterWather(slavePath, watcher);
		} catch (ZookeeperClientException e) {
			throw new RuntimeException(e);
		} 
	}



	protected void stop() {
		 
 	}

 
 
	
}