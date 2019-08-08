package com.toolbox.website.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.stereotype.Component;

import com.toolbox.model.Html;
import com.toolbox.utils.TemplateUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class IndexPageMaker extends PageMaker {
	public static String NAME = "index";

	@Override
	protected void prepareModel(Html html) {

		super.prepareModel(html);

		Map wheres = new HashMap<String, String>();
		wheres.put("is_menuitem", 1);
		List<Map> menuItems = this.htmlService.getEntitys(wheres, "id asc");

		List<Html> menus = new ArrayList<Html>();
		Map<Integer, Html> parentItems = new HashMap<Integer, Html>();
		for (Map map : menuItems) {
			Html menuItem = new Html(map);
			if (menuItem.getLevel() == 0) {
				continue;
			}
			if (menuItem.getLevel() == 1) {
				menus.add(menuItem);
				parentItems.put(1, menuItem);
				menuItem.setLabel(menus.size() + "");
			} else {
				int menuItemLevel = menuItem.getLevel();
				parentItems.get(menuItemLevel - 1).getChildren().add(menuItem);
				parentItems.put(menuItemLevel, menuItem);

				menuItem.setLabel(parentItems.get(menuItemLevel - 1).getLable() + "."
						+ parentItems.get(menuItemLevel - 1).getChildren().size());
			}
		}
		
		html.put("menus", menus);
	}

	@Override
	protected String makeBody(Html html) {
		String body = super.makeBody(html);
		String menus = makeMenuHtml((List<Html>) html.get("menus"));
		html.put("menus", menus);
		
		return  TemplateUtils.evaluate(body, html); 
	}

	private String makeMenuHtml(List<Html> menus) {

		String menuItemTemplate ="<li class='menuitem' title='$!{keywords}' data-toggle='tooltip' data-placement='top'>\r\n" + 
				"	<a ${href} target='contentFrame' class='${clazz}'>\r\n" + 
				"		<i class='fa hide-fa-check'></i> <b>$!{label}</b> $!{name_cn}\r\n" + 
				"	</a>\r\n" + 
				"	<span style='display: none'>$!{keywords}</span>\r\n" + 
				"	$!{ul}\r\n" + 
				"</li>";
		return menus.stream().map(menuItem -> {

			String ul = makeMenuHtml(menuItem.getChildren());
			if (StringUtils.isNotEmpty(ul))
				ul = "<ul class=''>" + ul + "</ul>";

			String clazz = (StringUtils.isEmpty(ul)) ? "leaf" : "folder-open";
			String href = (StringUtils.isNotEmpty(menuItem.getPath())) ? "href='" + menuItem.getPath() + "'" : "";
			menuItem.put("clazz", clazz);
			menuItem.put("href", href);
			menuItem.put("ul", ul);
			
			return TemplateUtils.evaluate(menuItemTemplate, menuItem);
		}).reduce("", (p1, p2) -> {
			return p1 + "\n" + p2;
		});
	}
}
