package io.usa.doraemon.commons.api.security;
import org.junit.Test;


public class RSAHandlerTest {
	@Test
	public void testRSA() throws Exception{
		RSAHandler handler = new RSAHandler();
	    String source = "恭喜发Z0wH财!";// 要加密的字符串
	    System.out.println("准备用公钥加密的字符串为：" + source);
	    
	    String cryptograph = handler.encrypt(source);// 生成的密文
	    System.out.print("用公钥加密后的结果为:" + cryptograph);
	    System.out.println();

	    String target = handler.decrypt(cryptograph);// 解密密文
	    System.out.println("用私钥解密后的字符串为：" + target);
	    System.out.println();
	}
}
 