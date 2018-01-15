package com.qihui.o2o.service.impl;

import java.util.Date;
import java.util.List;

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
import com.qihui.o2o.util.PageCalculator;

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
			shop.setShopImg(fileName);
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
		// 调用ImageUtil帮助函数增加图片水印，设置成新的shop图片在目录的相对值路径
		String dest = ImageUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
		shop.setShopImg(shopImgAddr);

	}

	@Override
	public Shop getByShopId(long shopId) {
		return shopDao.queryByShopId(shopId);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName)
			throws RuntimeException {
		// not null check
		if(shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		} else {
			try {
			// 1.check if need to process image， if yes update
			if(shopImgInputStream != null) {
				Shop tempShop = shopDao.queryByShopId(shop.getShopId());
				if(tempShop.getShopImg() != null) {
					ImageUtil.deleteFileOrPath(tempShop.getShopImg());
				}
			} // ImageUtil.deleteFileOrPath(shop.getShopImg()); ?保险起见
			addShopImg(shop, shopImgInputStream, fileName);
			
			// 2. Update shop info
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.updateShop(shop);
			if(effectedNum <= 0) {
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			} else {
				shop = shopDao.queryByShopId(shop.getShopId()); //保险起见重新query一次
				return new ShopExecution(ShopStateEnum.SUCCESS, shop);
			}
			} catch (Exception e) {
				throw new RuntimeException("ModifyShop error:" + e.getMessage());
			}
		}
	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculatorRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if ( shopList != null ) {
			se.setShopList(shopList);
			se.setCount(count);
		} else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}


}
