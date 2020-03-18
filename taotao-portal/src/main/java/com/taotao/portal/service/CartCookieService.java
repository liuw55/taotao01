package com.taotao.portal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manager.pojo.Cart;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.service.ItemService;
import com.taotao.portal.util.CookieUtils;

@Service
public class CartCookieService {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Value("${TT_CART}")
	private String TT_CART;
	@Autowired
	private ItemService itemService;

	public void saveItemByCookie(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
		List<Cart> list = queryCartByCookie(request);
		Cart cart = null;
		for (Cart c : list) {
			if (c.getItemId().longValue() == itemId.longValue()) {
				cart = c;
			}
		}

		if (cart != null) {
			cart.setNum(cart.getNum() + num);
			cart.setUpdated(new Date());
		} else {
			Item item = itemService.queryById(itemId);
			cart = new Cart();
			cart = new Cart();
			cart.setId(null);
			cart.setUserId(null);
			cart.setItemId(itemId);
			cart.setItemTitle(item.getTitle());
			if (item.getImages() != null) {
				cart.setItemImage(item.getImages()[0]);
			} else {
				cart.setItemImage(null);
			}
			cart.setItemPrice(item.getPrice().longValue());
			cart.setNum(num);
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			list.add(cart);
		}

		try {
			CookieUtils.setCookie(request, response, TT_CART, MAPPER.writeValueAsString(list), 60 * 60 * 24 * 3, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Cart> queryCartByCookie(HttpServletRequest request) {
		String json = CookieUtils.getCookieValue(request, TT_CART, true);
		List<Cart> list = null;
		if (StringUtils.isNotBlank(json)) {
			try {
				list = MAPPER.readValue(json, MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<>();
	}

	public void updateNumByCookie(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
		List<Cart> list = queryCartByCookie(request);
		for (Cart cart : list) {
			if (cart.getItemId().longValue() == itemId.longValue()) {
				cart.setNum(num);
				cart.setUpdated(new Date());

				try {
					CookieUtils.setCookie(request, response, this.TT_CART, MAPPER.writeValueAsString(list),
							60 * 60 * 24 * 3, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	public void deleteItemByCookie(Long itemId, HttpServletRequest request, HttpServletResponse response) {
		List<Cart> list = queryCartByCookie(request);
		for (Cart cart : list) {
			if (cart.getItemId().longValue() == itemId.longValue()) {
				list.remove(cart);
				
				try {
					CookieUtils.setCookie(request, response, this.TT_CART, MAPPER.writeValueAsString(list),
							60 * 60 * 24 * 3, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

}
