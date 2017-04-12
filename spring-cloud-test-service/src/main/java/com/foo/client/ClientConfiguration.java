package com.foo.client;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfiguration {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(final SpringClientFactory clientFactory) {
		RestTemplate restTemplate = new RestTemplate();
		// restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor()
		// {
		// private Logger logger = LoggerFactory.getLogger("metrics");
		//
		// private String formatMetricsLog(HttpRequest request,
		// ClientHttpResponse response, long startTime) {
		// int statusCode = 999;
		// try {
		// statusCode = response.getStatusCode().value();
		// } catch (IOException e) {
		// }
		// return String.format("%s %s <%d> %dns", request.getMethod(),
		// request.getURI(), statusCode, System.nanoTime() - startTime);
		// }
		//
		// @Override
		// public ClientHttpResponse intercept(HttpRequest request, byte[] body,
		// ClientHttpRequestExecution execution) throws IOException {
		// long startTime = System.nanoTime();
		//
		// ClientHttpResponse response = null;
		// try {
		// response = execution.execute(request, body);
		// return response;
		// } finally {
		// logger.info(this.formatMetricsLog(request, response, startTime));
		// }
		// }
		// });
		return restTemplate;
	}

	// @Bean
	// Object injectRestTemplate(RestTemplate restTemplate, MetricStore
	// metricStore) {
	// for (ClientHttpRequestInterceptor interceptor :
	// restTemplate.getInterceptors()) {
	// if (interceptor instanceof LoadBalancerInterceptor) {
	// inject((LoadBalancerInterceptor) interceptor, metricStore);
	// }
	// }
	// restTemplate.getInterceptors().add(0, httpRequestStoreInterceptor);
	// return new Object();
	// }
	//
	// private void inject(LoadBalancerInterceptor loadBalancerInterceptor,
	// MetricStore metricStore) {
	// try {
	// Field field =
	// LoadBalancerInterceptor.class.getDeclaredField("loadBalancer");
	// field.setAccessible(true);
	// LoadBalancerClient originalClient = (LoadBalancerClient)
	// field.get(loadBalancerInterceptor);
	//
	// Field field2 =
	// RibbonLoadBalancerClient.class.getDeclaredField("clientFactory");
	// SpringClientFactory springClientFactory = (SpringClientFactory)
	// field2.get(originalClient);
	// WrappedLoadBalancerClient wrappedLoadBalancerClient = new
	// WrappedLoadBalancerClient(springClientFactory, metricStore);
	//
	// field.set(loadBalancerInterceptor, wrappedLoadBalancerClient);
	// } catch (NoSuchFieldException | SecurityException |
	// IllegalArgumentException | IllegalAccessException e) {
	// e.printStackTrace();
	// }
	// }
}
