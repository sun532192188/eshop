package com.roncoo.eshop.inventory.mapper;

import com.roncoo.eshop.inventory.model.ProductInventory;

/**
 * 库存数量mapper
 * @author Sunxiaorong
 *
 */
public interface ProductInventoryMapper {

	/**
	 * 更新商品库存
	 * @param productInventory
	 */
	public void updateProductInventory(ProductInventory productInventory);
	
	/**
	 * 根据商品id查询商品库存
	 * @param productId
	 * @return
	 */
	public ProductInventory findProductInventory(Integer productId);
}
