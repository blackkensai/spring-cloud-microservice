package com.foo.client;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClientInjector implements SmartLifecycle {
	private boolean started = false;
	@Autowired
	private HttpRequestStoreInterceptor httpRequestStoreInterceptor;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private MetricStore metricStore;

	Object injectRestTemplate() {
		for (ClientHttpRequestInterceptor interceptor : restTemplate.getInterceptors()) {
			if (interceptor instanceof LoadBalancerInterceptor) {
				inject((LoadBalancerInterceptor) interceptor);
			}
		}
		restTemplate.getInterceptors().add(0, httpRequestStoreInterceptor);
		return new Object();
	}

	private void inject(LoadBalancerInterceptor loadBalancerInterceptor) {
		try {
			Field field = LoadBalancerInterceptor.class.getDeclaredField("loadBalancer");
			field.setAccessible(true);
			LoadBalancerClient originalClient = (LoadBalancerClient) field.get(loadBalancerInterceptor);

			Field field2 = RibbonLoadBalancerClient.class.getDeclaredField("clientFactory");
			field2.setAccessible(true);
			SpringClientFactory springClientFactory = (SpringClientFactory) field2.get(originalClient);
			WrappedLoadBalancerClient wrappedLoadBalancerClient = new WrappedLoadBalancerClient(springClientFactory, metricStore);

			field.set(loadBalancerInterceptor, wrappedLoadBalancerClient);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		started = true;
		injectRestTemplate();
	}

	@Override
	public void stop() {
		started = false;
	}

	@Override
	public boolean isRunning() {
		return started;
	}

	@Override
	public int getPhase() {
		return 99;
	}

	@Override
	public boolean isAutoStartup() {
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		callback.run();
		started = false;
	}

}
