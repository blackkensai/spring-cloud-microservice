package com.foo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.foo.domain.CouponList;
import com.foo.domain.IOrder;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class FrontendSampleController {
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/create")
	public IOrder createOrder(@RequestBody IOrder order) {
		// do business logic here...

		// invoke order service
		return restTemplate.getForObject("http://spring-cloud-zuul/spring-cloud-order-service/o/c", IOrder.class, order);
	}

	@HystrixCommand(fallbackMethod = "getCouponListByUserFallback")
	public CouponList getCouponListByUser(String userId) {
		return restTemplate.getForObject("http://spring-cloud-zuul/spring-cloud-coupon-service/getCouponListByUser?uid={uid}", CouponList.class,
				userId);
	}

	public CouponList getCouponListByUserFallback(String userId) {
		return new CouponList();
	}

	DynamicStringProperty property = DynamicPropertyFactory.getInstance().getStringProperty("business.id1", "ERROR");

	@RequestMapping("/test1")
	public String test1() {
		return String.format(">>> %s", property.get());
	}
}
