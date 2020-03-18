 package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Item;
import com.taotao.search.service.SearchService;

@Controller
@RequestMapping("search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	@Value("${SEARCH_TAOTAO_ITEM_ROWS}")
	private Integer rows;

	@RequestMapping(method = RequestMethod.GET)
	public String search(@RequestParam("q") String query, Model model,
			@RequestParam(value = "page", defaultValue = "1") Integer page) {
		try {
			query = new String(query.getBytes("ISO8859-1"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		TaoResult<Item> taoResult = searchService.search(query, page, rows);
		model.addAttribute("query", query);
		model.addAttribute("itemList", taoResult.getRows());
		model.addAttribute("page", page);
		Long total = taoResult.getTotal();
		/*Long pages = total % rows == 0 ? total / rows : total / rows + 1;*/
		long pages = (total + this.rows - 1) / this.rows;
		model.addAttribute("totalPages", pages);
		return "search";
	}

}
