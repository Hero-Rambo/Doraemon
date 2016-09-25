package test.ip;

import java.net.InetAddress;
import java.net.SocketException;

import junit.framework.TestCase;

import io.usa.doraemon.commons.api.common.utils.impl.LocalInetAddressHelper;


/**
 * 
 * @author Rambo
 *
 */
public class LocalIPTest extends TestCase {
	public void testGetIP() throws SocketException{
		InetAddress add = LocalInetAddressHelper.getRealIp();
		System.out.println(add.getHostName());
		System.out.println(add.getHostAddress());
	}
 
}
