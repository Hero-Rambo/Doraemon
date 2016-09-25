package io.usa.doraemon.bus.event.listener.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.usa.doraemon.commons.api.common.node.watcher.NodePathHelper;
import io.usa.doraemon.commons.api.common.utils.IServerInfoProvider;
import io.usa.doraemon.commons.api.common.utils.ServerInfo;
import io.usa.doraemon.commons.zookeeper.client.ZookeeperClient;
import io.usa.doraemon.commons.zookeeper.client.ZookeeperClientException;
 
/**
 * 
 * @author Rambo
 *
 */
@Component
public class ApplicationContextListener implements ApplicationListener<ApplicationEvent> {
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationContextListener.class);
	
	@Autowired
	private IServerInfoProvider serverInfoProvider;
	
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			LOG.info("begin register bus...................." );
			register();
			LOG.info("end register bus...................." );
		}

		if (event instanceof ContextClosedEvent) {
			LOG.info("begin unregister bus...................." );
			unregister();
			LOG.info("end unregister bus...................." );
		}

	}
  
	
	@Autowired
	private ZookeeperClient client;
	  
	@Autowired
	private ObjectMapper jackson;


	/**
	 * 
	 */
	protected void register(){				
		ServerInfo serverInfo = serverInfoProvider.getServerInfo();
		String path = this.getBusNodePath(serverInfo);
		try {
			client.ensurePath(NodePathHelper.getBusRegisterPath());
			if(!client.isExist(NodePathHelper.getBusRegisterPath())){
				client.create(NodePathHelper.getBusRegisterPath());
			}
			
			String busInfo = jackson.writeValueAsString(serverInfo);
			LOG.info("begin register bus...................." );
			LOG.info("path...................."+path );
			LOG.info("busInfo...................."+busInfo );
			
			client.setData(path, busInfo.getBytes());

//			if(!client.isExist(path)){
//				client.create(path, busInfo.getBytes());
//			}else{
//			}
			LOG.info("A bus node is registered on path:"+path);
		} catch (Exception e) {
			LOG.error("Exception occured!", e);
		}

	}
	 
	private String getBusNodePath(ServerInfo serverInfo){
		String context = serverInfo.getContextRoot().substring(1, serverInfo.getContextRoot().length());
		return NodePathHelper.getBusRegisterPath()+"/"+serverInfo.getIp()+"_"+serverInfo.getPort()+"_"+context;
	}
	
	
	/**
	 * 
	 */
	protected void unregister(){
		ServerInfo serverInfo = serverInfoProvider.getServerInfo();
		String path = this.getBusNodePath(serverInfo);
		LOG.info("begin unregister bus...................." );
		LOG.info("path...................."+path );
 		try {
			client.delete(path);
			LOG.info("A bus is unregistered on path:"+path);
		} catch (ZookeeperClientException e) {
			throw new RuntimeException(e);
		}
		LOG.info("end unregister bus...................." );

	}
 
	
}