package com.foo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

@RestController
@RefreshScope
public class ServiceController {
	@Autowired
	private EurekaInstanceConfigBean configBean;

	DynamicStringProperty nameprop = DynamicPropertyFactory.getInstance().getStringProperty("my.name", "unknown one");

	@RequestMapping("/hello")
	public @ResponseBody String hello(@RequestParam("n") String name) {
		return String.format("hello, %s from %s at %s", name, nameprop.get(), configBean.getInstanceId());
	}
}
