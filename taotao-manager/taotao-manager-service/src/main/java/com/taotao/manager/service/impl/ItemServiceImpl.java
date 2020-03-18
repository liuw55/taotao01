package com.taotao.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemDesc;
import com.taotao.manager.service.ItemDescService;
import com.taotao.manager.service.ItemService;
@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService{
	
	@Autowired
	private ItemDescService itemDescService;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource
	private Destination topicDestination;	
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public void saveItem(final Item item, String desc) {
		item.setStatus(1);
		super.save(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDescService.save(itemDesc);
		
		jmsTemplate.send(topicDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(item.getId() + "");
			}
		});
		
	}

	public TaoResult<Item> queryItemList(Integer page, Integer rows) {
		PageHelper.startPage(page,rows);
		List<Item> list = super.queryListByWhere(null);
		PageInfo<Item> pageInfo = new PageInfo<>(list);
		TaoResult<Item> taoResult = new TaoResult<>();
		taoResult.setTotal(pageInfo.getTotal());
		taoResult.setRows(list);
		return taoResult;
	}

	public void updateItem(Item item, String desc) {
		super.updateByIdSelective(item);
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDescService.updateByIdSelective(itemDesc);
	}

	

}
