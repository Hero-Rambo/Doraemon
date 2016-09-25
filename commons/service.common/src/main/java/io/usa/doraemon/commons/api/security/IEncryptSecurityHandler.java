package io.usa.doraemon.commons.api.security;

public interface IEncryptSecurityHandler {
	Object encrypt();
	Object decrypt();
	String getSecurityLevel();
}
