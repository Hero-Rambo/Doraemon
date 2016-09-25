package io.usa.doraemon.commons.api.common.node.watcher;

/**
 * 
 * @author Rambo
 * @2014-1-21
 */
public class NodePathHelper {
	private static final String ROOT_PATH="/microservice";
	private NodePathHelper(){
		
	}
	
	public static String getGlobalRegisterPath(){
		return ROOT_PATH+"/global/nodes";
	}
	
	public static String getBusRegisterPath(){
		return ROOT_PATH+"/bus/nodes";
	}
	
	public static String getSlaveRegisterPath(){
		return ROOT_PATH+"/provider/nodes";
	}
	
//	public static String getSlaveReportPath(){
//		return ROOT_PATH+"/provider/report";
//	}
	
	public static String getSlaveStatusPath(){
		return ROOT_PATH+"/provider/status";
	}
	
	
}
