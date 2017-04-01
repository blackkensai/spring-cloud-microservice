package com.foo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {
	@Autowired
	private EurekaInstanceConfigBean configBean;

	@RequestMapping("/hello")
	public @ResponseBody String hello(@RequestParam("n") String name) {
		return String.format("hello, %s from %s", name, configBean.getInstanceId());
	}
}
