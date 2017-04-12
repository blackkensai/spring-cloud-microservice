package com.foo.client;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

@Component
public class MetricStore {
	private ThreadLocal<HttpRequest> localRequest = new ThreadLocal<>();

	public void save(HttpRequest httpRequest) {
		localRequest.set(httpRequest);
	}

	public HttpRequest getAndDiscard() {
		try {
			return localRequest.get();
		} finally {
			localRequest.remove();
		}
	}
}
