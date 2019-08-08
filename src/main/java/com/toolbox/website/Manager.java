package com.toolbox.website;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toolbox.model.Html;
import com.toolbox.service.HtmlService;
import com.toolbox.service.SettingService;
import com.toolbox.website.page.PageMakerFactory;

@Component
public class Manager {

	@Autowired
	private HtmlService htmlService;

	@Autowired
	private SettingService settingService;

	@Autowired
	private PageMakerFactory htmlMakerFactory;

	/**
	 * build the whole website
	 */
	public void build() {

	}

	/**
	 * build the special tool
	 * 
	 * @param tool
	 */
	public void build(String tool) {

	}

	/**
	 * deploy the whole website
	 */
	public void deploy() {

	}

	/**
	 * deploy special tool
	 * 
	 * @param tool
	 */
	public void deployd(String tool) {

	}

	public void make(Map data) {
		List<Map> htmls = htmlService.getEntitys(data);
		for (int i = 0, size = htmls.size(); i < size; i++) {
			Html html = new Html(htmls.get(i));
			htmlMakerFactory.getHtmlMaker(html.getName()).make(html);
		}
	}
}
