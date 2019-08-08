package com.toolbox.website.page;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import com.toolbox.model.Html;
import com.toolbox.utils.TemplateUtils;

@Component
public class IndexPage extends PageMaker {
	public static String NAME = "index";

	@Override
	public String make(Html html) {
		Map wheres = new HashMap<String, String>();
		wheres.put("is_menuitem", 1);
		List<Map> menuItems = this.htmlService.getEntitys(wheres);

		// generate html
		try {
			String htmlStr = FileUtils.readFileToString(new File(basePath + "/index.html"), "utf8");
			int start = htmlStr.indexOf(this.startStr);
			int end = htmlStr.indexOf(this.endStr + this.endStr.length());
			htmlStr = htmlStr.substring(start, end);

			Map model = new HashMap();
			model.put("list", menuItems);
			String body = TemplateUtils.evaluate(htmlStr, model);

			html.put("body", body);
			html.put("timestamp", System.currentTimeMillis());

			String pageStr = this.evaluateSimpleHtml(model);
			
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		
		return null;
	}
}
