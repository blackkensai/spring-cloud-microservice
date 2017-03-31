package com.foo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {
	@RequestMapping("/hello")
	public @ResponseBody String hello(@RequestParam("n") String name) {
		return String.format("hello, %s", name);
	}
}
