package io.usa.doraemon.provider.register;

import java.util.List;

import io.usa.doraemon.commons.api.common.message.register.ServiceMetadata;

public interface IServiceInfoProvider {
	List<ServiceMetadata> getServiceInfos();
}
