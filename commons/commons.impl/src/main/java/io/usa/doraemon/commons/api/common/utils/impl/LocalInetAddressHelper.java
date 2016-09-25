package io.usa.doraemon.commons.api.common.utils.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


/**
 * 
 * @author Rambo
 *
 */
public class LocalInetAddressHelper {
	private LocalInetAddressHelper(){
		
	}
	
	
	public static InetAddress getRealIp() throws SocketException {
		InetAddress localip = null;// 本地IP，如果没有配置外网IP则返回它
		InetAddress netip = null;// 外网IP

		Enumeration<NetworkInterface> netInterfaces = NetworkInterface
				.getNetworkInterfaces();
		InetAddress ip = null;
		boolean finded = false;// 是否找到外网IP
		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netip = ip;
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip;
				}
			}
		}

		if (netip != null) {
			return netip;
		} else {
			return localip;
		}
	}
}
