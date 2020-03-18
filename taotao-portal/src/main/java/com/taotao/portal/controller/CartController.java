package com.taotao.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.cart.service.CartService;
import com.taotao.manager.pojo.Cart;
import com.taotao.manager.pojo.User;
import com.taotao.portal.service.CartCookieService;
import com.taotao.portal.util.CookieUtils;
import com.taotao.sso.service.UserService;

@Controller
@RequestMapping("cart")
public class CartController {

	@Value("${TT_TICKET}")
	private String TT_TICKET;
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	@Autowired
	private CartCookieService cartCookieService;

	@RequestMapping(value = "{itemId}", method = RequestMethod.GET)
	public String saveItemByCart(@PathVariable("itemId") Long itemId, Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		String ticket = CookieUtils.getCookieValue(request, TT_TICKET);
		User user = userService.queryUserByTicket(ticket);
		if (user != null) {
			cartService.saveItemByCart(user.getId(), itemId, num);
		} else {
			cartCookieService.saveItemByCookie(itemId, num, request, response);
		}
		return "redirect:/cart/show.html";
	}

	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String showCart(Model model, HttpServletRequest request) {
		String ticket = CookieUtils.getCookieValue(request, TT_TICKET);
		User user = userService.queryUserByTicket(ticket);
		List<Cart> cartList = new ArrayList<>();
		if (user != null) {
			cartList = cartService.getCartByUserId(user.getId());
		} else {
			cartList = cartCookieService.queryCartByCookie(request);
		}
		model.addAttribute("cartList", cartList);
		return "cart";
	}

	@RequestMapping(value = "update/num/{itemId}/{num}", method = RequestMethod.POST)
	@ResponseBody
	public void updateNumByCart(@PathVariable("itemId") Long itemId, @PathVariable("num") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		String ticket = CookieUtils.getCookieValue(request, TT_TICKET);
		User user = userService.queryUserByTicket(ticket);
		if (user != null) {
			cartService.update(user.getId(), num, itemId);
		} else {
			cartCookieService.updateNumByCookie(itemId, num, request, response);
		}
	}

	@RequestMapping(value = "delete/{itemId}", method = RequestMethod.GET)
	public String deleteItemByCart(@PathVariable("itemId") Long itemId, HttpServletRequest request,
			HttpServletResponse response) {
		String ticket = CookieUtils.getCookieValue(request, TT_TICKET);
		User user = userService.queryUserByTicket(ticket);
		if (user != null) {
			cartService.deleteItemByCart(user.getId(), itemId);
		} else {
			cartCookieService.deleteItemByCookie(itemId, request, response);
		}
		return "redirect:/cart/show.html";
	}

}
