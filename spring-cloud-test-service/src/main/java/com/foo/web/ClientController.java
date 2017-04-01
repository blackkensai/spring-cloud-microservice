package com.foo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {
	@Autowired
	private EurekaInstanceConfigBean configBean;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/say")
	public @ResponseBody String say(@RequestParam("n") String name,
			@RequestParam(value = "z", required = false, defaultValue = "false") boolean useZuul) {
		String host = useZuul ? "spring-cloud-zuul/spring-cloud-order-service" : "spring-cloud-order-service";
		return String.format("%s say: %s", configBean.getInstanceId(),
				restTemplate.getForObject(String.format("http://%s/hello?n={name}", host), String.class, name));
	}
}
