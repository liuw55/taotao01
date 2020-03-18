package com.taotao.cart.service;

import java.util.List;

import com.taotao.manager.pojo.Cart;

public interface CartService {

	void saveItemByCart(Long id, Long itemId, Integer num);

	List<Cart> getCartByUserId(Long id);

	void update(Long id, Integer num, Long itemId);

	void deleteItemByCart(Long id, Long itemId);

}
