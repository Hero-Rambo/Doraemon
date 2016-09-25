package io.usa.doraemon.commons.api.service.locator;

import io.usa.doraemon.commons.api.service.api.Service;

public interface IServiceLocator {
	Service getService(String serviceName);

	Service getService(String category, String name, String version);
}
