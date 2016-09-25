package io.usa.doraemon.commons.api.common.node.watcher;

import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import io.usa.doraemon.commons.zookeeper.client.ZookeeperClient;
import io.usa.doraemon.commons.zookeeper.client.ZookeeperClientException;


/**
 * 
 * @author Rambo
 * @2014-1-7
 */
public abstract class AbstractNodeWatcher implements INodeWatcher,InitializingBean  {

	private Logger logger = LoggerFactory.getLogger(AbstractNodeWatcher.class);
		
	@Autowired
	private ZookeeperClient client;	
 

	@Override
	public void process(WatchedEvent watchedEvent) {
		String path = watchedEvent.getPath();
		logger.info("watchedEvent path===" + path);
		logger.info("watchedEvent getType===" + watchedEvent.getType());
		logger.info("watchedEvent getState===" + watchedEvent.getState());
		logger.info("watchedEvent getWrapper===" + watchedEvent.getWrapper());
		try {
			if(EventType.None.equals(watchedEvent.getType())){
				registerWatcher();
				return;
			}else if (EventType.NodeCreated.equals(watchedEvent.getType())) {
				this.logger.info("NodeCreated............");
				this.processNodeCreated(path, client.getData(path));
			} else if (EventType.NodeDeleted.equals(watchedEvent.getType())) {
				this.logger.info("NodeDeleted............");
				this.processNodeDeleted(path, client.getData(path));
			} else if (EventType.NodeDataChanged.equals(watchedEvent.getType())) {
				this.logger.info("NodeDataChanged............");
				this.processNodeDataChanged(path, client.getData(path));
			} else if (EventType.NodeChildrenChanged.equals(watchedEvent.getType())) {
				this.logger.info("NodeChildrenChanged............");
				this.processNodeChildren(path,client.watchedGetChildren(path));
			}
		} catch (ZookeeperClientException e) {
			logger.error("An exception occured!", e);
		}
		if(path!=null){
			this.registerWatcher(path);
		}
	}



	protected void processNodeDataChanged(String path, byte[] data){
		
	}

	protected void processNodeDeleted(String path, byte[] data){
		
	}
	
	protected void processNodeCreated(String path, byte[] data) {
		
	}

	/**
	 * 
	 * @param parentPath
	 * @param children
	 * @throws ZookeeperClientException 
	 */
	protected void processNodeChildren(String parentPath,List<String> children) throws ZookeeperClientException{
		
	}
	

	protected ZookeeperClient getZookeeperClient() {
		return client;
	}

 

	protected boolean isBusRegister(String path) {
		return path.startsWith(NodePathHelper.getBusRegisterPath());
	}

	protected boolean isGlobalRegister(String path) {
		return path.startsWith(NodePathHelper.getGlobalRegisterPath());
			
	}

	protected boolean isSlaveRegister(String path){
		return path.startsWith(NodePathHelper.getSlaveRegisterPath());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		registerWatcher();
	}

	protected void registerWatcher(){
		logger.info("begin to registerWatcher.....");
		
		logger.info("begin to processExistData.....");
		this.processExistData();
		logger.info("end to processExistData.....");
		
		for(String path:this.getWatchedPath()){
			this.registerWatcher(path);
		}
		logger.info("end to registerWatcher.....");
	}
	 
	private void registerWatcher(String path) {
		logger.info("path to register watcher.....:"+path);
		try {
			this.client.reigsterChildrenWather(path, this);
		} catch (ZookeeperClientException e) {
			e.printStackTrace();
			logger.error("Exception occured when register Watcher", e);		
		}
	}

	
	protected void processExistData(){
		for(String path:this.getWatchedPath()){
			this.processExistNode(path);
			this.processExistNodeChildren(path);			
		}		
	}
	
	protected void processExistNode(String path) {
 		try {
			this.processNodeCreated(path, this.getZookeeperClient().getData(path));
		} catch (ZookeeperClientException e) {
			logger.error("An exception occured!",e);
		}
	}

	protected void processExistNodeChildren(String path){
		try {			
			List<String> children = this.client.getChildrenPahts(path);
			this.processNodeChildren(path, children);
		} catch (ZookeeperClientException e) {
			logger.error("An exception occured!",e);
		}	
	}



	abstract protected String[] getWatchedPath();
	
	
}
