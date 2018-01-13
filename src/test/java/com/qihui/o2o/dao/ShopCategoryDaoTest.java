package com.qihui.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.qihui.o2o.BaseTest;
import com.qihui.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testQueryArea() {
		// without arguments
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
		assertEquals(2,shopCategoryList.size());
		
		// with arguments
		ShopCategory cafe_milktea = new ShopCategory();
		ShopCategory cafe = new ShopCategory();
		cafe_milktea.setShopCategoryId(1L);
		cafe.setParent(cafe_milktea);
		shopCategoryList = shopCategoryDao.queryShopCategory(cafe);
		assertEquals(1,shopCategoryList.size());
	}
}
