package com.toolbox.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * use goolge Closure to compile the js and css
 * 
 * @author lihu
 *
 */
public class Html extends HashMap {

	private String basePath;
	private String outputPath;

	private String id;
	private String name;
	private String name_cn;
	private String parent;
	private String path;

	private String keywords;

	private String description;

	private String title;

	private JSONObject data;

	private boolean disabled;
	private boolean is_menuitem;

	private String maker;

	public Html() {

	}

	public Html(Map map) {
		this.setId((String) map.get("id"));
		this.setName((String) map.get("name"));
		this.setName_cn((String) map.get("name_cn"));
		this.setParent((String) map.get("parent"));
		this.setPath((String) map.get("path"));
		this.setKeywords((String) map.get("keywords"));
		this.setDescription((String) map.get("description"));
		this.setTitle((String) map.get("title"));
		this.setData((String) map.get("data"));
		if (map.get("disabled") != null)
			this.setDisabled((Boolean) map.get("disabled"));
		if (map.get("is_menuitem") != null)
			this.setIs_menuitem((Boolean) map.get("is_menuitem"));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_cn() {
		return name_cn;
	}

	public void setName_cn(String name_cn) {
		this.name_cn = name_cn;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	private void setData(String data) {
		this.data = JSONObject.fromObject(data);
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		if (disabled == null)
			this.disabled = false;
		this.disabled = disabled;
	}

	public boolean isIs_menuitem() {
		return is_menuitem;
	}

	public void setIs_menuitem(Boolean is_menuitem) {
		if (is_menuitem == null)
			this.is_menuitem = false;

		this.is_menuitem = is_menuitem;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getHtmlFolder() {
		return new File(this.getHtmlPath()).getParent();
	}

	public String getHtmlPath() {
		return this.basePath + this.getPath();
	}

	public String getCssPath() {
		return getHtmlFolder() + "/index.css";

	}

	public String getJsPath() {
		return getHtmlFolder() + "/index.js";
	}

	public String getImagePath() {
		return getHtmlFolder() + "/img";
	}
	public String getOutputFolder() {
		return new File(this.getOutputHtmlPath()).getParent();
	}

	public String getOutputHtmlPath() {
		return this.outputPath + this.getPath();
	}

	public String getOutputCssPath() {
		return getOutputFolder() + "/index.css";
	}

	public String getOutputJsPath() {
		return getOutputFolder() + "/index.js";
	}

	public String getOutputImagePath() {
		return getOutputFolder() + "/img";
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String toHtml() {
		return null;
	}
}
