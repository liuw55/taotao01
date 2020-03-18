package com.taotao.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.cart.service.CartService;
import com.taotao.manager.pojo.Cart;
import com.taotao.manager.pojo.Order;
import com.taotao.manager.pojo.User;
import com.taotao.order.service.OrderService;
import com.taotao.portal.util.CookieUtils;
import com.taotao.sso.service.UserService;

@Controller
@RequestMapping("order")
public class OrderController {

	@Value("${TT_TICKET}")
	private String TT_TICKET;
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(HttpServletRequest request, Model model) {
		User user = (User) request.getAttribute("user");
		List<Cart> carts = cartService.getCartByUserId(user.getId());
		model.addAttribute("carts", carts);
		return "order-cart";
	}
	
	@RequestMapping(value = "submit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveOrder(Order order, HttpServletRequest request){
		User user = (User) request.getAttribute("user");
		order.setUserId(user.getId());
		order.setBuyerNick(user.getUsername());
		String orderId = orderService.saveOrder(order);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		map.put("data", orderId);
		return map;
	}
	
	@RequestMapping(value = "success", method = RequestMethod.GET)
	public String success(@RequestParam("id") String orderId, Model model){
		Order order = orderService.queryOrderByOrderId(orderId);
		//获取当前时间的两天后,即送达时间
		String date = new DateTime().plusDays(2).toString("yyyy年MM月dd日HH时mm分ss秒SSS毫秒");
		model.addAttribute("order", order);
		model.addAttribute("date", date);
		return "success";
	}
}
