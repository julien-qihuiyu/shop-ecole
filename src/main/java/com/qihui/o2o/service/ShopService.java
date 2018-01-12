package com.qihui.o2o.service;

import java.io.InputStream;

import com.qihui.o2o.dto.ShopExecution;
import com.qihui.o2o.entity.Shop;

public interface ShopService {
	//ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;
	ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws RuntimeException;
}
