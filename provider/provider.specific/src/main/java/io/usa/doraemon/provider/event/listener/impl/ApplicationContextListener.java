package io.usa.doraemon.provider.event.listener.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.usa.doraemon.commons.api.common.message.register.SlaveRegisterMessage;
import io.usa.doraemon.commons.api.common.node.watcher.NodePathHelper;
import io.usa.doraemon.commons.api.common.utils.IServerInfoProvider;
import io.usa.doraemon.commons.api.common.utils.ServerInfo;
import io.usa.doraemon.commons.zookeeper.client.ZookeeperClient;
import io.usa.doraemon.commons.zookeeper.client.ZookeeperClientException;
import io.usa.doraemon.provider.register.IServiceInfoProvider;
import io.usa.doraemon.provider.register.ISlaveRegisterMessageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Rambo
 *
 */
@Component
public class ApplicationContextListener implements ApplicationListener<ApplicationEvent> {
	private Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);
	

	
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			logger.info("begin register provider...................." );
			register();
			logger.info("end register salve...................." );
		}

		if (event instanceof ContextClosedEvent) {
			logger.info("begin unregister provider...................." );
			unregister();
			logger.info("end unregister provider...................." );
		}
	}
	@Autowired
	private IServerInfoProvider serverInfoProvider;
	
	@Autowired
	protected IServiceInfoProvider serviceInfoProvider;

	@Autowired
	protected ISlaveRegisterMessageProvider serviceNodeInfoProvider;
	  
 

	
	@Autowired
	private ZookeeperClient client;
	  
	@Autowired
	private ObjectMapper jackson;
 
	protected void register(){	
		
		SlaveRegisterMessage slaveRegisterMessage = serviceNodeInfoProvider.getSlaveRegisterMessage();
		slaveRegisterMessage.setStatus("Start");
		slaveRegisterMessage.setServices(this.serviceInfoProvider.getServiceInfos());
		
		ServerInfo serverInfo = serverInfoProvider.getServerInfo();
		String path = this.getSlaveNodePath(serverInfo);
		try {
			client.ensurePath(NodePathHelper.getSlaveRegisterPath());
			if(!client.isExist(NodePathHelper.getSlaveRegisterPath())){
				client.create(NodePathHelper.getSlaveRegisterPath());
			}
			
			String slaveInfo = jackson.writeValueAsString(slaveRegisterMessage);
			logger.info("begin register provider...................." );
			logger.info("path...................."+path );
			logger.info("slaveInfo...................."+slaveInfo );
			if(this.client.isExist(path)){
				logger.info("provider register path is exist! so delete first!");
				this.client.delete(path);
			}
			this.client.create(path, slaveInfo.getBytes());
			logger.info("A provider is registered on path:"+path);
		} catch (Exception e) {
			logger.error("Exception occured!", e);
		}

	}
	 
	private String getSlaveNodePath(ServerInfo serverInfo){
		String context = serverInfo.getContextRoot().substring(1, serverInfo.getContextRoot().length());
		return NodePathHelper.getSlaveRegisterPath()+"/"+serverInfo.getIp()+"_"+serverInfo.getPort()+"_"+context;
	}
	
	protected String getSlaveStatusPath(ServerInfo serverInfo){
		String context = serverInfo.getContextRoot().substring(1, serverInfo.getContextRoot().length());
		return NodePathHelper.getSlaveStatusPath()+"/"+serverInfo.getIp()+"_"+serverInfo.getPort()+"_"+context;
	}
	 
 
	/**
	 * 
	 */
	protected void unregister(){
//		SlaveRegisterMessage slaveRegisterMessage = serviceNodeInfoProvider.getSlaveRegisterMessage();
//		slaveRegisterMessage.setStatus("Stop");
//		slaveRegisterMessage.setServices(this.serviceInfoProvider.getServiceInfos());
		
		ServerInfo serverInfo = serverInfoProvider.getServerInfo();
		String path = this.getSlaveNodePath(serverInfo);
		logger.info("begin to unregister provider...................." );
		logger.info("provider register path...................."+path );
 		try {
//			String slaveInfo = jackson.writeValueAsString(slaveRegisterMessage);
			client.delete(path);
//			client.setData(path, slaveInfo.getBytes());
			logger.info("A provider is unregistered on path:"+path);
		} catch (ZookeeperClientException e) {
			logger.error("Slave unregister error:",e);
		}  
		

		String slaveStatusPath = this.getSlaveStatusPath(serverInfo);
		logger.info("begin to remove provider status path...................." );
		logger.info("provider report status path...................."+slaveStatusPath );
		try {
			this.client.delete(slaveStatusPath);
		} catch (ZookeeperClientException e) {
			logger.error("Slave report status path remove error:",e);
		}
	}

	
	
}