package io.usa.doraemon.global.node.register.biz.dao;

import io.usa.doraemon.global.node.register.model.BusNode;

public interface IBusNodeRepositoiry {
	boolean register(BusNode busNode);
	
	boolean unregister(BusNode busNode);

	boolean isExist(BusNode node);
}
