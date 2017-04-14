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
		return restTemplate;
	}

}
