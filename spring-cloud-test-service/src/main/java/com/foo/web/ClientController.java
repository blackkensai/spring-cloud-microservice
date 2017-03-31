package com.foo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/say")
	public @ResponseBody String say(@RequestParam("n") String name) {
		return String.format("say: %s", restTemplate.getForObject("http://spring-cloud-test-service/hello?n={name}", String.class, name));
	}
}
