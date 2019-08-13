package com.toolbox.website.page;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.toolbox.model.Html;
import com.toolbox.service.HtmlService;
import com.toolbox.service.SettingService;
import com.toolbox.utils.FileUtils;
import com.toolbox.utils.Logger;
import com.toolbox.utils.TemplateUtils;

@Component
public abstract class PageMaker {

	@Value("${app.home.path}")
	protected String homePath;

	@Value("${app.home.path}/base/")
	protected String basePath;

	@Value("${app.home.path}/template")
	protected String templatePath;

	@Value("${app.home.path}/output")
	protected String outputPath;

	@Autowired
	protected HtmlService htmlService;

	@Autowired
	protected SettingService settingService;

	protected String startStr = "<!-- start -->";

	protected String endStr = "<!-- end -->";

	protected Map<String, String> cache = new HashMap<String, String>();

	public String make(Html html) {		
		Logger.info(String.format("[%s] make start",html.getName()));
		long start = System.currentTimeMillis();
		
		// prepare the model
		prepareModel(html);

		if (StringUtils.isEmpty(html.getPath())) {
			Logger.info(String.format("[%s] skipped. Path is null", html.getName()));
			return null;
		}

		if (!new File(html.getHtmlPath()).exists()) {
			Logger.info(String.format("[%s] skipped. Path '%s' is not exiat", html.getName(), html.getHtmlPath()));
			return null;
		}

		// make html
		makeHtml(html);

		// make css
		makeCss(html);

		// make js
		makeJs(html);

		// copy images
		copyImages(html);

		Logger.info(String.format("[%s] make end, spend %s ", html.getName(), System.currentTimeMillis() - start));

		return html.getOutputHtmlPath();
	}

	protected void prepareModel(Html html) {
		html.setBasePath(this.basePath);
		html.setOutputPath(outputPath);
		html.put("hideExample", "hide");
		html.put("templateStart", "-->");
		html.put("templateEnd", "<!--");
		html.put("timestamp", System.currentTimeMillis());
	}

	/**
	 * make html
	 * 
	 * @param html
	 */
	protected void makeHtml(Html html) {			
		// get body
		html.put("body", makeBody(html));

		// make html
		String pageStr = this.evaluateSimpleHtml(html);
		FileUtils.writeStringToFile(html.getOutputHtmlPath(), pageStr);
		
		// copy other htmls
		if (new File(html.getHtmlFolder()).exists()) {
			Collection<File> files = FileUtils.listFiles(html.getHtmlFolder(), "html", false);
			files.forEach(file -> {
				FileUtils.copyFileToDirectory(file.getAbsolutePath(), html.getOutputFolder());
			});
		}
	}
	

	protected String makeBody(Html html) {
		String htmlStr = FileUtils.readFileToString(html.getHtmlPath());
		int start = htmlStr.indexOf(this.startStr);
		if (start == -1)
			start = htmlStr.indexOf("<body>") + "<body>".length();

		int end = htmlStr.indexOf(this.endStr);
		if (end != -1)
			end += this.endStr.length();
		else
			end = htmlStr.indexOf("<!-- Optional JavaScript -->");

		try {
			String body = htmlStr.substring(start, end);
			if (html.getData() != null) {
				body = TemplateUtils.evaluate(body, html.getData());
			}
			return body;
		} catch (Throwable ex) {
			Logger.error(html.getName());
			throw new RuntimeException(ex);
		}

	}

	/**
	 * make css <br>
	 * merge common css to index.css
	 * 
	 * @param html
	 */
	protected void makeCss(Html html) {
		String commonCss = getCommonCss();
		String indexCss = getHtmlCss(html);

		FileUtils.writeStringToFile(html.getOutputCssPath(), commonCss + "\n" + indexCss);
	}

	private void makeJs(Html html) {
		String commonJs = getCommonJs();
		String indexJs = getHtmlJs(html);
		FileUtils.writeStringToFile(html.getOutputJsPath(), commonJs + "\n" + indexJs);
	}

	private void copyImages(Html html) {
		if (new File(html.getImagePath()).exists()) {
			FileUtils.copyDirectory(html.getImagePath(), html.getOutputImagePath());
		}
	}

	protected String getSimpleHtml() throws IOException {
		if (cache.containsKey("simple.html"))
			return cache.get("simple.html");

		String simpleHtmlPath = templatePath + "/simple.html";
		String str = FileUtils.readFileToString(simpleHtmlPath);
		cache.put("simple.html", str);
		return str;
	}

	protected String evaluateSimpleHtml(Map model) {
		try {
			return TemplateUtils.evaluate(getSimpleHtml(), model);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * read all common css and merge them together
	 * 
	 * @return
	 */
	protected String getCommonCss() {
		if (cache.containsKey("common.css"))
			return cache.get("common.css");

		String commonCssFolder = basePath + "/common/css/";
		Collection<File> files = FileUtils.listFiles(new File(commonCssFolder), "css", true);
		return files.stream().map(file -> {
			return FileUtils.readFileToString(file) + "\n";
		}).reduce("", (p1, p2) -> {
			return p1 + p2;
		});
	}

	protected String getCommonJs() {
		if (cache.containsKey("common.css"))
			return cache.get("common.css");

		String commonJsFolder = basePath + "/common/js/";
		Collection<File> files = FileUtils.listFiles(new File(commonJsFolder), "js", true);
		return files.stream().map(file -> {
			return FileUtils.readFileToString(file) + "\n";
		}).reduce("", (p1, p2) -> {
			return p1 + p2;
		});
	}

	protected String getHtmlCss(Html html) {
		Collection<File> files = FileUtils.listFiles(html.getHtmlFolder(), "css", false);
		return files.stream().map(file -> {
			return FileUtils.readFileToString(file) + "\n";
		}).reduce("", (p1, p2) -> {
			return p1 + p2;
		});
	}

	protected String getHtmlJs(Html html) {
		Collection<File> files = FileUtils.listFiles(html.getHtmlFolder(), "js", false);
		return files.stream().map(file -> {
			if (file.getName().equalsIgnoreCase("other.js"))
				return "";

			return FileUtils.readFileToString(file) + "\n";
		}).reduce("", (p1, p2) -> {
			return p1 + p2;
		});
	}
}
