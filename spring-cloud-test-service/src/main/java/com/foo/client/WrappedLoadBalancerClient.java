package com.foo.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * inject LoadBalancerInterceptor to get metric details
 * 
 * @author blackkensai
 *
 */
public final class WrappedLoadBalancerClient extends RibbonLoadBalancerClient {
	private Logger metricLogger = LoggerFactory.getLogger("metrics");

	private MetricStore metricStore;

	public WrappedLoadBalancerClient(SpringClientFactory clientFactory, MetricStore metricStore) {
		super(clientFactory);
		this.metricStore = metricStore;
	}

	@Override
	public <T> T execute(String serviceId, ServiceInstance serviceInstance, LoadBalancerRequest<T> request) throws IOException {
		// here, return ClientHttpResponse or
		// ListenableFuture<ClientHttpResponse>
		final MetricData metricData = new MetricData();
		metricData.setServiceInstance(serviceInstance);
		metricData.setHttpRequest(metricStore.getAndDiscard());

		T retval = super.execute(serviceId, serviceInstance, request);
		if (retval instanceof ClientHttpResponse) {
			metricData.setHttpResponse((ClientHttpResponse) retval);
			metricLogger.info(metricData.toString());
		} else if (retval instanceof ListenableFuture<?>) {
			@SuppressWarnings("unchecked")
			ListenableFuture<ClientHttpResponse> future = (ListenableFuture<ClientHttpResponse>) retval;
			future.addCallback(new ListenableFutureCallback<ClientHttpResponse>() {

				@Override
				public void onSuccess(ClientHttpResponse result) {
					metricData.setHttpResponse(result);
					metricLogger.info(metricData.toString());
				}

				@Override
				public void onFailure(Throwable ex) {
					metricData.setStatusCode(999);
					metricLogger.info(metricData.toString());
				}
			});
		}
		return retval;
	}

}