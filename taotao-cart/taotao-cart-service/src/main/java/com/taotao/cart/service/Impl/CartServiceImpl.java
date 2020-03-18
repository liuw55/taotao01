package com.taotao.cart.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.cart.service.CartService;
import com.taotao.cart.service.RedisUtils;
import com.taotao.manager.mapper.ItemMapper;
import com.taotao.manager.pojo.Cart;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.User;

import freemarker.template.utility.ToCanonical;

@Service
public class CartServiceImpl implements CartService {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Value("${TAOTAO_CART_KEY}")
	private String TAOTAO_CART_KEY;
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private ItemMapper itemMapper;

	public void saveItemByCart(Long id, Long itemId, Integer num) {
		List<Cart> list = getCartByUserId(id);
		Cart cart = null;
		// 遍历购物车,商品是否存在
		for (Cart c : list) {
			if (c.getItemId().longValue() == itemId.longValue()) {
				cart = c;
			}
		}

		// 判断商品的购物车是否存在
		if (cart != null) {
			cart.setNum(cart.getNum() + num);
			cart.setUpdated(new Date());
		} else {
			Item item = itemMapper.selectByPrimaryKey(itemId);
			cart = new Cart();
			cart.setUserId(id);
			cart.setItemId(itemId);
			cart.setItemTitle(item.getTitle());
			cart.setItemPrice(item.getPrice().longValue());
			cart.setItemImage(item.getImage());
			cart.setNum(num);
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			list.add(cart);
		}

		// 把添加好的购物车保存在redis中
		try {
			redisUtils.set(TAOTAO_CART_KEY + id, MAPPER.writeValueAsString(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Cart> getCartByUserId(Long id) {
		String json = redisUtils.get(TAOTAO_CART_KEY + id);
		if (StringUtils.isNotBlank(json)) {
			try {
				// json格式数据序列化为集合
				List<Cart> list = MAPPER.readValue(json,
						MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<>();
	}

	public void update(Long id, Integer num, Long itemId) {
		List<Cart> list = getCartByUserId(id);
		for (Cart cart : list) {
			if (cart.getItemId().longValue() == itemId.longValue()) {
				cart.setNum(num);
				cart.setUpdated(new Date());
				try {
					redisUtils.set(TAOTAO_CART_KEY + id, MAPPER.writeValueAsString(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void deleteItemByCart(Long id, Long itemId) {
		List<Cart> list = getCartByUserId(id);
		for (Cart cart : list) {
			if (cart.getItemId().longValue() == itemId.longValue()) {
				list.remove(cart);
				try {
					redisUtils.set(TAOTAO_CART_KEY+id, MAPPER.writeValueAsString(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
