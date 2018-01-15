package com.qihui.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.qihui.o2o.BaseTest;
import com.qihui.o2o.dto.ShopExecution;
import com.qihui.o2o.entity.Area;
import com.qihui.o2o.entity.PersonInfo;
import com.qihui.o2o.entity.Shop;
import com.qihui.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
	@Autowired
	private ShopDao shopDao;
	
	@Test
	@Ignore
	public void testInsertShop() {
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
		shop.setShopName("New test store");		
		shop.setShopDesc("New test store"); 
		shop.setShopAddr("New test store");
		shop.setPhone("New test number");
		shop.setShopImg("New test number");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopDesc("Test Update desc"); 
		shop.setShopAddr("Test Update addr");	
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testQueryById() {
		long shopId = 1L;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaId: " + shop.getArea().getAreaId());
		System.out.println("areaName: " + shop.getArea().getAreaName());
	}
	
	@Test
	@Ignore
	public void testQueryShopListAndCount() {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
		System.out.println("店铺列表的大小：" + shopList.size());
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("店铺总数：" + count);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(2L);
		shopCondition.setShopCategory(sc);
	    shopList = shopDao.queryShopList(shopCondition, 0, 2);
		System.out.println("新店铺列表的大小：" + shopList.size());
		count = shopDao.queryShopCount(shopCondition);
		System.out.println("新店铺总数：" + count);
	}
	
}
