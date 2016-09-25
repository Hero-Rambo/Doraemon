package io.usa.doraemon.manage.service.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * 
 * @author Rambo
 *
 */
public class LocalIpAddressTest {
	@Test
	public void test() throws UnknownHostException{
		InetAddress addr = InetAddress.getLocalHost();
		String ip=addr.getHostAddress();//获得本机IP
		String host=addr.getHostName();//获得本机名称
	
		
		System.out.println(ip);
		System.out.println(host);
		
		InetAddress inet = InetAddress.getLocalHost();

		System.out.println("HostAddress=" + inet.getHostAddress());
		System.out.println("HostName=" + inet.getHostName());
		System.out.println("CanonicalHostName=" + inet.getCanonicalHostName());
//		System.out.println("LocalHost=" + inet.getLocalHost()); 
	}
}
