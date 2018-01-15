package com.qihui.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qihui.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 分类查询店铺，可输入的条件有:店铺名（模糊），店铺状态，店铺类别，区域Id，owner
	 * @param shopCondition 查询条件
	 * @param rowIndex 从第几行开始取数据
	 * @param pageSize  返回的条数
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex, 
			@Param("pageSize") int pageSize);
	
	/**
	 * 返回queryShopList 总数
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	
	/**
	 * Look for shop via shopId
	 * @param shopId
	 * @return
	 */
	Shop queryByShopId(long shopId);
	
	/**
	 * insert shop
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	
	/**
	 * update shop
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
