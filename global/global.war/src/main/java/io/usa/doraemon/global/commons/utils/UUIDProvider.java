package io.usa.doraemon.global.commons.utils;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * 
 * @author Rambo
 * @date Jan 26, 2014
 */
public class UUIDProvider {
	private UUIDProvider(){
		
	}
	private static final TimeBasedGenerator generator = Generators.timeBasedGenerator();
	public static String getUUID(){		
		return generator.generate().toString();
	}
}
