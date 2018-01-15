package com.qihui.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qihui.o2o.BaseTest;
import com.qihui.o2o.dto.ShopExecution;
import com.qihui.o2o.entity.Area;
import com.qihui.o2o.entity.PersonInfo;
import com.qihui.o2o.entity.Shop;
import com.qihui.o2o.entity.ShopCategory;
import com.qihui.o2o.enums.ShopStateEnum;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("Test3 store service");		
		shop.setShopDesc("New3 test store"); 
		shop.setShopAddr("New3 test store");
		shop.setPhone("New test number");
		shop.setShopImg("New test image");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		
		File shopImg = new File("/Users/qihuiyu/Desktop/pizza.jpg");
		InputStream is = new FileInputStream(shopImg);
		ShopExecution se = shopService.addShop(shop, is, shopImg.getName());
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
	
	@Test
	@Ignore
	public void testModifyShop() throws FileNotFoundException {
		Shop shop = shopService.getByShopId(7L);
		shop.setShopName("修改后的店铺名称");
		File shopImg = new File("/Users/qihuiyu/Desktop/burger.jpeg");
		InputStream is = new FileInputStream(shopImg);
		ShopExecution se = shopService.modifyShop(shop, is, shopImg.getName());
	}
	
	@Test
	public void testGetShopList() {
		Shop shopCondition = new Shop();
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(2L);
		shopCondition.setShopCategory(sc);
		ShopExecution se = shopService.getShopList(shopCondition, 2, 2);
		System.out.println("店铺列表数为：" + se.getShopList().size());
		System.out.println("店铺总数为：" + se.getCount());
	}
}
