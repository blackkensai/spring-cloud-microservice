package com.foo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaServer
@EnableAutoConfiguration
@ComponentScan
public class SpringCloudEurekaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudEurekaApplication.class).web(true).run(args);
	}
}
