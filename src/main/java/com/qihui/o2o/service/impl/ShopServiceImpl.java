package com.qihui.o2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

import com.qihui.o2o.dao.ShopDao;
import com.qihui.o2o.dto.ShopExecution;
import com.qihui.o2o.entity.Shop;
import com.qihui.o2o.enums.ShopStateEnum;
import com.qihui.o2o.service.ShopService;
import com.qihui.o2o.util.ImageUtil;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {		
		// 空值判断
		if(shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		} 

		try {
			// 给店铺信息赋系统初始值，完整信息
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.insertShop(shop);
			// 添加店铺信息
			if(effectedNum <= 0) {
				throw new RuntimeException("店铺创建失败");
			} else {
				if(shopImgInputStream != null) {
					// 存储图片
					try {
						addShopImg(shop, shopImgInputStream, fileName);
					} catch(Exception e) {
						throw new RuntimeException("addShopImg error");
					}
					// 更新店铺的图片地址
					effectedNum = shopDao.updateShop(shop);
					if(effectedNum <= 0) {
						throw new RuntimeException("更新图片地址失败失败");
					} 
				}
			}

		} catch(Exception e) {
			throw new RuntimeException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
		// 获取 shop 图片目录的相对值路径
		String dest = ImageUtil.getShopImagePath(shop.getShopId());
		System.out.println("dest:" + dest);
		String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
		System.out.println("shopImgAddr:" + dest);
		shop.setShopImg(shopImgAddr);

	}


}
