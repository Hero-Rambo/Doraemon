package io.usa.doraemon.commons.api.service.rest.client;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CollectionJsonTest {
	@Test
	public void testCollection()throws Exception{
		ObjectMapper jackson = new ObjectMapper();
		List<TestBean> l = new ArrayList<TestBean>();
		{
			TestBean t1 = new TestBean();
			t1.setName("A1");
			t1.setSex("F");
			l.add(t1);
		}
		{
			TestBean t1 = new TestBean();
			t1.setName("A1");
			t1.setSex("F");
			l.add(t1);
		}
		
		String strList = jackson.writeValueAsString(l);
		System.out.println(strList);
		@SuppressWarnings("unchecked")
		List<TestBean> l1 = jackson.readValue(strList,List.class);
		strList = jackson.writeValueAsString(l1);
		System.out.println(strList);
	}
}
