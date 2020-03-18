package com.taotao.search.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.mapper.ItemMapper;
import com.taotao.manager.pojo.Item;
import com.taotao.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private CloudSolrServer cloudSolrServer;
	@Autowired
	private ItemMapper itemMapper;

	public TaoResult<Item> search(String query, Integer page, Integer rows) {
		SolrQuery solrQuery = new SolrQuery();
		if (StringUtils.isNotBlank(query)) {
			solrQuery.setQuery("item_title:" + query + " AND item_status:1");
		} else {
			solrQuery.setQuery("item_status:1");
		}

		solrQuery.setStart((page - 1) * rows);
		solrQuery.setRows(rows);

		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<font color='red'>");
		solrQuery.setHighlightSimplePost("</font>");

		TaoResult<Item> taoResult = new TaoResult<>();
		try {
			QueryResponse response = cloudSolrServer.query(solrQuery);
			SolrDocumentList results = response.getResults();
			Map<String, Map<String, List<String>>> map = response.getHighlighting();
			List<Item> list = new ArrayList<>();
			for (SolrDocument solrDocument : results) {
				Item item = new Item();
				item.setId(Long.parseLong(solrDocument.get("id").toString()));
				List<String> hlist = map.get(solrDocument.get("id")).get("item_title");
				if (hlist != null && hlist.size() > 0) {
					item.setTitle(hlist.get(0));
				} else {
					item.setTitle(solrDocument.get("item_title").toString());
				}
				item.setImage(solrDocument.get("item_image").toString());
				item.setPrice(Double.parseDouble(solrDocument.get("item_price").toString()));
				item.setCid(Long.parseLong(solrDocument.get("item_cid").toString()));
				list.add(item);
			}
			taoResult.setRows(list);
			taoResult.setTotal(results.getNumFound());
			return taoResult;
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveItem(long itemId) {
		// 1.调用商品服务,根据商品id获取商品信息
		Item item = itemMapper.selectByPrimaryKey(itemId);
		// 2.根据获取到的数据,保存索引库
		SolrInputDocument document = new SolrInputDocument();
		// 商品id
		document.addField("id", item.getId().toString());
		// 商品标题
		document.setField("item_title", item.getTitle());
		// 商品价格
		document.setField("item_price", item.getPrice());
		// 商品图片
		document.setField("item_image", item.getImage());
		// 商品类目id
		document.setField("item_cid", item.getCid());
		// 商品状态
		document.setField("item_status", item.getStatus());
		
		try {
			//保存到索引库中
			cloudSolrServer.add(document);
			cloudSolrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
