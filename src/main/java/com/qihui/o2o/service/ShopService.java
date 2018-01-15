package com.qihui.o2o.service;

import java.io.InputStream;

import com.qihui.o2o.dto.ShopExecution;
import com.qihui.o2o.entity.Shop;

public interface ShopService {
	/**
	 * 根据shopCondition分页返回相关数据
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
	
	/**
	 * Get shop info via shopId
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	
	/**
	 * Update shop info, including img processing
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws RuntimeException;
	
	/**
	 * Register shop info, including img processing
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws RuntimeException;
}
