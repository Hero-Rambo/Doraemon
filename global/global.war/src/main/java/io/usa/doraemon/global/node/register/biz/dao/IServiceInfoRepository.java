package io.usa.doraemon.global.node.register.biz.dao;

import java.util.List;

import io.usa.doraemon.global.node.register.model.ServiceInfo;

public interface IServiceInfoRepository{

	void register(List<ServiceInfo> serviceInfos);

}
