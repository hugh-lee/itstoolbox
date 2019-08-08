package com.toolbox.website.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageMakerFactory {

	@Autowired
	private PageMaker defaultPage;

	@Autowired
	private IndexPage indexPage;

	@Autowired
	private HomePage homePage;

	public PageMaker getHtmlMaker(String maker) {
		if (indexPage.NAME.equals(maker)) {
			return indexPage;
		}

		if (homePage.NAME.equals(maker)) {
			return homePage;
		}

		return defaultPage;
	}
}
