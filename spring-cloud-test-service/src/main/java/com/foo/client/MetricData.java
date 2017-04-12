package com.foo.client;

import java.io.IOException;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

public class MetricData {
	private HttpRequest httpRequest;

	private ClientHttpResponse httpResponse;

	private long startTime;

	private ServiceInstance serviceInstance;

	private int statusCode;

	public MetricData() {
		super();
		start();
	}

	public HttpRequest getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(HttpRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	public ClientHttpResponse getHttpResponse() {
		return httpResponse;
	}

	public void setHttpResponse(ClientHttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	public ServiceInstance getServiceInstance() {
		return serviceInstance;
	}

	public void setServiceInstance(ServiceInstance serviceInstance) {
		this.serviceInstance = serviceInstance;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void start() {
		startTime = System.nanoTime();
	}

	@Override
	public String toString() {
		if (this.statusCode == 0) {
			try {
				statusCode = httpResponse.getStatusCode().value();
			} catch (IOException e) {
			}
		}
		return String.format("%s %s via %s:%d <%d> %dns", httpRequest.getMethod(), httpRequest.getURI(), serviceInstance.getHost(),
				serviceInstance.getPort(), statusCode, System.nanoTime() - startTime);
	}

}
