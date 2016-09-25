package io.usa.doraemon.commons.api.service.rest.client;

import org.junit.Test;

public class GenericTest {
	@Test
	public void test(){
		A<String> a = new A<String>();
		a.a();
	}
}

class A<T>{
	Class<T> clazz;
	public void a(){

		System.out.println(clazz.getCanonicalName());
	}
}