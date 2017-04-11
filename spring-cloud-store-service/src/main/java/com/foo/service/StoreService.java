package com.foo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foo.persistence.StoreMapper;

@Service
public class StoreService {
	@Autowired
	private StoreMapper storeMapper;

	public String getStorenameByCode(String code) {
		return storeMapper.getStorenameByCode(code);
	}

}
