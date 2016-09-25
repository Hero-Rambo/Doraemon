package io.usa.doraemon.commons.zookeeper.client;

import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.utils.EnsurePath;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

/**
 * 
 * @author Rambo
 * @2013-12-29
 */
public class  ZookeeperClient {
	private CuratorFramework client = null;

	private String connectionString;
	private RetryPolicy retryPolicy;
	private int connectionTimeoutMs;
	private int sessionTimeoutMs;
	
	/**
	 * 
	 * @param connectionString
	 * @param retryPolicy
	 * @param connectionTimeoutMs
	 * @param sessionTimeoutMs
	 */
	public ZookeeperClient(String connectionString, RetryPolicy retryPolicy,
			int connectionTimeoutMs, int sessionTimeoutMs) {
		this.connectionString = connectionString;
		this.retryPolicy = retryPolicy;
		this.connectionTimeoutMs = connectionTimeoutMs;
		this.sessionTimeoutMs = sessionTimeoutMs;
		
		client = CuratorFrameworkFactory.newClient(connectionString,
				sessionTimeoutMs, connectionTimeoutMs, retryPolicy);
		client.start();
	}

	
	public String getConnectionString() {
		return connectionString;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	public RetryPolicy getRetryPolicy() {
		return retryPolicy;
	}

	public void setRetryPolicy(RetryPolicy retryPolicy) {
		this.retryPolicy = retryPolicy;
	}

	public int getConnectionTimeoutMs() {
		return connectionTimeoutMs;
	}

	public void setConnectionTimeoutMs(int connectionTimeoutMs) {
		this.connectionTimeoutMs = connectionTimeoutMs;
	}

	public int getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}

	public void setSessionTimeoutMs(int sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}

	public void ensurePath(String path) throws ZookeeperClientException {
		EnsurePath ensurePath = new EnsurePath(path);
		try {
			ensurePath.ensure(client.getZookeeperClient());			
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}
	
	public void create(String path, byte[] payload) throws ZookeeperClientException {
		// this will create the given ZNode with the given data
		try {
			client.create().forPath(path, payload);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}

	public void create(String path) throws ZookeeperClientException {
		try {
			client.create().forPath(path);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}
 
	public void createEphemeral(String path, byte[] payload) throws ZookeeperClientException {
		try {
			client.create().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}

	public String createEphemeralSequential(String path, byte[] payload)
			throws ZookeeperClientException {
		try {
			return client.create().withProtection()
					.withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
					.forPath(path, payload);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}

	public void setData(String path, byte[] payload) throws ZookeeperClientException {
		// set data for the given node
		try {
			this.ensurePath(path);
 			if(!this.isExist(path)){
 				this.create(path);
			}
			client.setData().inBackground().forPath(path, payload);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}

	public void setDataAsync(String path, byte[] payload) throws ZookeeperClientException {
		// this is one method of getting event/async notifications
		CuratorListener listener = new CuratorListener() {
			@Override
			public void eventReceived(CuratorFramework client, CuratorEvent arg1)
					throws Exception {
				// TODO Auto-generated method stub
				
			}
		};
		client.getCuratorListenable().addListener(listener);

		// set data for the given node asynchronously. The completion
		// notification
		// is done via the CuratorListener.
		try {
			client.setData().inBackground().forPath(path, payload);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}

	public void setDataAsyncWithCallback(BackgroundCallback callback,
			String path, byte[] payload) throws ZookeeperClientException {
		// this is another method of getting notification of an async completion
		try {
			client.setData().inBackground(callback).forPath(path, payload);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}

	public void delete(String path) throws ZookeeperClientException {
		// delete the given node
		try {
			if(this.isExist(path)){
				client.delete().forPath(path);
			}
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}

	public void guaranteedDelete(String path) throws ZookeeperClientException {
		try {
			if(this.isExist(path)){
				client.delete().guaranteed().forPath(path);
			}			
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}

	public List<String> watchedGetChildren(String path) throws ZookeeperClientException {
		/**
		 * Get children and set a watcher on the node. The watcher notification
		 * will come through the CuratorListener (see setDataAsync() above).
		 */
		try {
			return client.getChildren().watched().forPath(path);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}

	public List<String> watchedGetChildren(String path, Watcher watcher)
			throws ZookeeperClientException {
		/**
		 * Get children and set the given watcher on the node.
		 */
		try {
			return client.getChildren().usingWatcher(watcher).forPath(path);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}
	
	public byte[] getData(String path) throws ZookeeperClientException{
		try {
			return  client.getData().forPath(path);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}
	 
	public void reigsterWather(String path,CuratorWatcher watcher) throws ZookeeperClientException{
		try {
			client.getData().usingWatcher(watcher).inBackground().forPath(path);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}
	
	public void reigsterWather(String path,Watcher watcher) throws ZookeeperClientException{
		try {
			client.getData().usingWatcher(watcher).inBackground().forPath(path);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}
	
	public void reigsterChildrenWather(String path,CuratorWatcher watcher) throws ZookeeperClientException{
		try {
			client.getChildren().usingWatcher(watcher).inBackground().forPath(path);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}
	
	public void reigsterChildrenWather(String path,Watcher watcher) throws ZookeeperClientException{
		try {
			client.getChildren().usingWatcher(watcher).inBackground().forPath(path);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}
	
	public boolean isExist(String path) throws ZookeeperClientException{
		try {
			return this.client.checkExists().forPath(path) != null;
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}





	/**
	 * 
	 * @param path
	 * @return
	 * @throws ZookeeperClientException 
	 */
	public List<String> getChildrenPahts(String path) throws ZookeeperClientException {
		try {
			return this.client.getChildren().forPath(path);
		} catch (Exception e) {
			throw new ZookeeperClientException(e);
		}
	}
	
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		this.client.close();
		this.client = null;
	}
}
