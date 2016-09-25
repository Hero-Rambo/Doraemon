package io.usa.doraemon.global;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

public class UUIDProvider {
	private UUIDProvider(){
		
	}
	private static final TimeBasedGenerator generator = Generators.timeBasedGenerator();
	public static String getUUID(){		
		return generator.generate().toString();
	}
}
