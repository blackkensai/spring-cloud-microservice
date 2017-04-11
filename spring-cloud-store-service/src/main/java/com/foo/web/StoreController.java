package com.foo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foo.service.StoreService;

@RestController
public class StoreController {
	@Autowired
	private StoreService storeService;

	@RequestMapping("s")
	public String getStorenameByCode(@RequestParam("c") String code) {
		return storeService.getStorenameByCode(code);
	}

}
