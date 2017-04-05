package com.foo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudArchaiusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudArchaiusApplication.class, args);
	}
}
