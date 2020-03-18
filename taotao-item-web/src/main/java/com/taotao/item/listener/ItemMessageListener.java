package com.taotao.item.listener;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manager.service.ItemDescService;
import com.taotao.manager.service.ItemService;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ItemMessageListener implements MessageListener {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemDescService itemDescService;
	@Value("${ITEM_TAOTAO_ITEM_PATH}")
	private String ITEM_TAOTAO_ITEM_PATH;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				String json = textMessage.getText();
				if (StringUtils.isNotBlank(json)) {
					JsonNode jsonNode = MAPPER.readTree(json);
					String type = jsonNode.get("type").asText();
					long itemId = jsonNode.get("itemId").asLong();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void getHtml(Long itemId) throws Exception {
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		Template template = configuration.getTemplate("item.ftl");
		Map<String, Object> root = new HashMap<>();
		root.put("item", itemService.queryById(itemId));
		root.put("itemDesc", itemDescService.queryById(itemId));
		Writer out = new FileWriter(new File(ITEM_TAOTAO_ITEM_PATH + itemId + ".html")); 
		template.process(root, out);
	}
}
