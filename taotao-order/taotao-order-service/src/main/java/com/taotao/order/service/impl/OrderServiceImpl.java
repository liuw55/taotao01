package com.taotao.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.manager.mapper.OrderItemMapper;
import com.taotao.manager.mapper.OrderMapper;
import com.taotao.manager.mapper.OrderShippingMapper;
import com.taotao.manager.pojo.Order;
import com.taotao.manager.pojo.OrderItem;
import com.taotao.manager.pojo.OrderShipping;
import com.taotao.order.service.OrderService;
import com.taotao.order.service.RedisUtils;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Value("${ORDER_TAOTAO_ORDERID_INCR}")
	private String ORDER_TAOTAO_ORDERID_INCR;
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;

	public String saveOrder(Order order) {
		String orderId = "" + order.getUserId() + redisUtils.incr(ORDER_TAOTAO_ORDERID_INCR);
		order.setOrderId(orderId);
		order.setStatus(1);
		order.setCreateTime(new Date());
		order.setUpdateTime(order.getCreateTime());
		orderMapper.insertSelective(order);
		
		List<OrderItem> list = order.getOrderItems();
		for (OrderItem orderItem : list) {
			orderItem.setOrderId(orderId);
			orderItemMapper.insertSelective(orderItem);
		}
		
		OrderShipping orderShipping = order.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(order.getCreateTime());
		orderShipping.setUpdated(orderShipping.getCreated());
		orderShippingMapper.insertSelective(orderShipping);
		return orderId;
	}

	public Order queryOrderByOrderId(String orderId) {
		Order order = orderMapper.selectByPrimaryKey(orderId);
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(orderId);
		List<OrderItem> items = orderItemMapper.select(orderItem);
		order.setOrderItems(items);
		OrderShipping orderShipping = orderShippingMapper.selectByPrimaryKey(orderId);
		order.setOrderShipping(orderShipping);
		return order;
	}

}
