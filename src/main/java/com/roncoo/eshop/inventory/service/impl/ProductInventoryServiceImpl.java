package com.roncoo.eshop.inventory.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.roncoo.eshop.inventory.dao.RedisDao;
import com.roncoo.eshop.inventory.mapper.ProductInventoryMapper;
import com.roncoo.eshop.inventory.model.ProductInventory;
import com.roncoo.eshop.inventory.service.ProductInventoryService;

/**
 * 商品库存Service实现类
 * @author Administrator
 *
 */
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService{

	@Resource
    ProductInventoryMapper productInventoryMapper;
	
	@Resource
	private RedisDao redisDao;
	
	@Override
	public void updateProductInventory(ProductInventory productInventory) {
		productInventoryMapper.updateProductInventory(productInventory);
	}

	@Override
	public void removeProductInventoryCache(ProductInventory productInventory) {
		String key = "product:inventory:" + productInventory.getProductId();
		redisDao.delete(key);
	}

	@Override
	public ProductInventory findProductInventory(Integer productId) {
		return productInventoryMapper.findProductInventory(productId);
	}

	@Override
	public void setProductInventoryCache(ProductInventory productInventory) {
		String key = "product:inventory:" + productInventory.getProductId();
		redisDao.set(key, String.valueOf(productInventory.getInventoryCnt()));
	}

}
