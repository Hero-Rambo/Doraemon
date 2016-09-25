package io.usa.doraemon.commons.api.common.utils.impl;

import java.net.InetAddress;
import java.util.List;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.servlet.ServletContext;

import io.usa.doraemon.commons.api.common.utils.IServerInfoProvider;
import io.usa.doraemon.commons.api.common.utils.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sun.jmx.mbeanserver.JmxMBeanServer;



/**
 * 
 * @author Rambo
 * @2013-12-26
 */
@Component
public class ServerInfoProvider implements IServerInfoProvider {
	private ServerInfo serverInfo = null;

	private  Logger logger = LoggerFactory.getLogger(ServerInfoProvider.class);
	
	
	private boolean useHostName;
	

	public boolean isUseHostName() {
		return useHostName;
	}

	public void setUseHostName(boolean useHostName) {
		this.useHostName = useHostName;
	}
  
	
	private String hostUrl;
	
	@Override
	public String getHostUrl()  {
		StringBuilder sb = new StringBuilder(100);
		sb.append("http://");
		if(this.isUseHostName()){
			sb.append(serverInfo.getHostName());
		}else{
			sb.append(serverInfo.getIp());
		}
		sb.append(':');
		sb.append(serverInfo.getPort());
		sb.append(this.serverInfo.getContextRoot());
		hostUrl = sb.toString();
		return hostUrl;
	}

	@Override
	public String getHostName() {
		return this.serverInfo.getHostName();
	}
 
	
 	@Override
	public void setServletContext(ServletContext servletContext) {		
		
		serverInfo = new ServerInfo();
		
		String contextRoot = servletContext.getContextPath();	
		logger.info("contextRoot================================="+contextRoot);

		serverInfo.setContextRoot(contextRoot);
		
		try {
			int port = getHttpPort("HTTP/1.1", "http");
			logger.info("port================================="+port);
			serverInfo.setPort(port);
			InetAddress ip = LocalInetAddressHelper.getRealIp();
			serverInfo.setIp(ip.getHostAddress());
			serverInfo.setHostName(ip.getCanonicalHostName());
 			logger.info("getRealIP===="+ip.getHostAddress());
		} catch (Exception e) {
 			e.printStackTrace();
		}
 
	}
	
 
	
	@Override
	public ServerInfo getServerInfo() {
		return this.serverInfo;
	}
	
    protected static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }
    
 
	/**
	 * 根据协议和scheme获取服务端口号
	 * 
	 * @return 端口号
	 * @throws Exception 
	 */
	private static int getHttpPort(String protocol, String scheme) throws Exception {
		MBeanServer mBeanServer = null;
		if (MBeanServerFactory.findMBeanServer(null).size() > 0) {
			mBeanServer = (MBeanServer) MBeanServerFactory.findMBeanServer(null).get(0);
		}

		Set<ObjectName> names = mBeanServer.queryNames(new ObjectName("Catalina:type=Connector,*"), null);
 
		for (ObjectName oname : names) {		
			try {
				String pvalue = (String) mBeanServer.getAttribute(oname, "protocol");
				String svalue = (String) mBeanServer.getAttribute(oname, "scheme");
				if (protocol.equals(pvalue) && scheme.equals(svalue)) {
					return ((Integer) mBeanServer.getAttribute(oname, "port"));
				}
			} catch (AttributeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstanceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MBeanException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ReflectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		throw new Exception("Can not get Server port!");
	}
	
	/**
	 * 根据协议和scheme获取服务端口号
	 * 
	 * @param protocol
	 * @param scheme
	 * @return
	 */
	protected Integer getPortByMBean(String protocol, String scheme) {
		MBeanServer mBeanServer = null;
		List<MBeanServer> mBeanServers = MBeanServerFactory.findMBeanServer(null);

		int serverType = 1;
		if (mBeanServers.size() > 0) {
			for (MBeanServer _mBeanServer : mBeanServers) {
				if (_mBeanServer instanceof JmxMBeanServer) {
					mBeanServer = _mBeanServer;
					break;
				} else if ("jboss".equals(_mBeanServer.getDefaultDomain())) {
					mBeanServer = _mBeanServer;
					serverType = 2;
				} else {
					throw new IllegalStateException("无法识别JVM中关联的MBeanServer.");
				}
			}
		}
		if (mBeanServer == null) {
			throw new IllegalStateException("没有发现JVM中关联的MBeanServer.");
		}

		logger.info("serverType==============="+serverType);
		Set<ObjectName> objectNames = null;

		try {
			if (serverType == 1) {
				objectNames = mBeanServer.queryNames(new ObjectName("Catalina:type=Connector,*"), null);
			} else if (serverType == 2) {
				objectNames = mBeanServer.queryNames(new ObjectName("jboss.web:type=Connector,*"), null);
			}
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		
		logger.info("objectNames==============="+objectNames);
		if (objectNames == null || objectNames.size() <= 0) {
			throw new IllegalStateException("没有发现JVM中关联的MBeanServer : "
					+ mBeanServer.getDefaultDomain() + " 中的对象名称.");
		}

		try {
			for (ObjectName objectName : objectNames) {
				Object _protocol = mBeanServer.getAttribute(objectName,"protocol");
				Object _scheme = mBeanServer.getAttribute(objectName, "scheme");

				logger.info("_protocol==============="+_protocol);
				logger.info("_scheme==============="+_scheme);

				if (protocol.equals(_protocol) && scheme.equals(_scheme)) {
					return (Integer) mBeanServer.getAttribute(objectName,"port");
				}
			}
		} catch (AttributeNotFoundException e) {
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (MBeanException e) {
			e.printStackTrace();
		} catch (ReflectionException e) {
			e.printStackTrace();
		}

		return null;
	}

 
}
