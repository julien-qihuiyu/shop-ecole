package com.qihui.o2o.dto;

import java.util.List;

import com.qihui.o2o.entity.Shop;
import com.qihui.o2o.enums.ShopStateEnum;

public class ShopExecution {
	// result state
	private int state;
	
	// state indicator
	private String stateInfo;
	
	// count of shops
	private int count;
	
	// operating shop (used in add,delete,update)
	private Shop shop;
	
	// shop list(used in read)
	private List<Shop> shopList;
	
	public ShopExecution() {
		
	}
	
	// 店铺操作失败的时候使用的构造器
	public ShopExecution(ShopStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	// 店铺操作成功的时候使用的构造器
	public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}
	
	// 店铺操作成功的时候使用的构造器
	public ShopExecution(ShopStateEnum stateEnum, List <Shop> shopList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	

}
