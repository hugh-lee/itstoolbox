package com.toolbox.website.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageMakerFactory {

	@Autowired
	private DefaultPageMaker defaultPageMaker;

	@Autowired
	private IndexPageMaker indexPageMaker;

	@Autowired
	private HomePageMaker homePageMaker;

	@SuppressWarnings("static-access")
	public PageMaker getHtmlMaker(String maker) {
		if (indexPageMaker.NAME.equalsIgnoreCase(maker)) {
			return indexPageMaker;
		}

		if (homePageMaker.NAME.equalsIgnoreCase(maker)) {
			return homePageMaker;
		}

		return defaultPageMaker;
	}
}
