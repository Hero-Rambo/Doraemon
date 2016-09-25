package io.usa.doraemon.commons.api.security;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAHandler implements IEncryptSecurityHandler{

    /** 指定加密算法为RSA */
    private static final String ALGORITHM = "RSA";
    /** 密钥长度，用来初始化 */
    private static final int KEYSIZE = 1024;
 

    private Key publicKey;
    private Key privateKey;
    /**
     * 生成密钥对
     * @throws Exception
     */
    protected void generateKeyPair() throws Exception {
        
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        keyPairGenerator.initialize(KEYSIZE);        
        /** 生成密匙对 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        
        /** 得到公钥 */
        publicKey = keyPair.getPublic();
        
        /** 得到私钥 */
        privateKey = keyPair.getPrivate();
        
        System.out.println("publicKey===="+publicKey);
        System.out.println("privateKey===="+privateKey);
      
    }

    /**
     * 加密方法
     * @param source 源数据
     * @return
     * @throws Exception
     */
    public String encrypt(String source) throws Exception {
        generateKeyPair();
 
        
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b1);
    }

    /**
     * 解密算法
     * @param cryptograph    密文
     * @return
     * @throws Exception
     */
    public String decrypt(String cryptograph) throws Exception {
 
        
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);
        
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }


	@Override
	public Object encrypt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object decrypt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSecurityLevel() {
		// TODO Auto-generated method stub
		return null;
	}
}
 