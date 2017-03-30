package com.foo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@Controller
@EnableZuulProxy
public class SpringCloudZuulApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudZuulApplication.class).web(true).run(args);
	}
}
