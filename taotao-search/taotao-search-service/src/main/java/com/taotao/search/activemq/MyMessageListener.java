package com.taotao.search.activemq;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.search.service.SearchService;

public class MyMessageListener implements MessageListener {
	
	@Autowired
	private SearchService searchService;

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				TextMessage textMessage = (TextMessage) message;
				if (StringUtils.isNotBlank(textMessage.getText())) {
					Long id = Long.parseLong(textMessage.getText());
					searchService.saveItem(id);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
