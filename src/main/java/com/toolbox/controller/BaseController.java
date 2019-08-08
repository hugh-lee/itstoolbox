package com.toolbox.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.toolbox.AppContext;
import com.toolbox.DataObject;
import com.toolbox.service.HtmlService;
import com.toolbox.service.SettingService;
import com.toolbox.utils.DateUtils;
import com.toolbox.utils.Logger;
import com.toolbox.website.Manager;

import net.sf.json.JSONObject;

public class BaseController {

	@Autowired
	protected Manager manager;
	
	@Autowired
	protected HtmlService htmlService;

	@Autowired
	protected SettingService settingService;

	protected DataObject getRequestDataObject() {
		return getRequestDataObject(null);
	}

	protected DataObject getRequestDataObject(String input) {
		HttpServletRequest request = AppContext.getHttpServletRequest();
		HttpServletResponse response = AppContext.getHttpServletResponse();
		HttpSession session = AppContext.getHttpSession();

		DataObject dobj = null;
		if (StringUtils.isNotEmpty(input)) {
			input = "{ 'body' : " + input + "}";
			dobj = new DataObject(input);
		} else {
			dobj = new DataObject();
		}
		dobj.setStatusCode("000");

		String action = request.getRequestURI();
		if (action != null && action.startsWith("/"))
			action = action.substring(1);
		dobj.setAction(action);

		AppContext.setAction(action);
		AppContext.setDataObject(dobj);

		Logger.info("[input]" + dobj);

		return dobj;
	}

	protected String getOutputString(DataObject dobj) {
		String output = JSONObject.fromObject(dobj).toString();
		Logger.info("[output]" + output);

		return output;
	}

	protected File saveUploadFile(MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			String fileName = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss") + "_" + file.getOriginalFilename();
			File outputFile = new File(getUploadFolder() + fileName);

			// 这里只是简单例子，文件直接输出到项目路径下。
			// 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
			// 还有关于文件格式限制、文件大小限制，详见：中配置。
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));
			out.write(file.getBytes());
			out.flush();
			out.close();

			return outputFile;
		}

		return null;
	}

	protected File saveUploadFile(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					String fileName = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss") + "_"
							+ file.getOriginalFilename();
					File outputFile = new File(getUploadFolder() + fileName);

					byte[] bytes = file.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(outputFile));
					stream.write(bytes);
					stream.close();

					return outputFile;
				} catch (Exception e) {
					Logger.error(("You failed to upload " + i + " => " + e.getMessage()), e);
				}
			} else {
				Logger.error("You failed to upload " + i + " because the file was empty.");
			}
		}
		return null;
	}

	protected void downloadFile(String filePath) {
		File file = new File(filePath);
		HttpServletResponse response = AppContext.getHttpServletResponse();
		if (file.exists()) {
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());// 设置文件名
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				Logger.error(e.getMessage(), e);
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						Logger.error(e.getMessage(), e);
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						Logger.error(e.getMessage(), e);
					}
				}
			}
		}
	}

	protected String getRootPath() {
		// 获取跟目录
		File path = null;
		try {
			path = new File(ResourceUtils.getURL("classpath:").getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (!path.exists())
			path = new File("");

		return path.getAbsolutePath();
	}

	protected String getUploadFolder() {
		String folder = getRootPath() + "/upload/";
		File file = new File(folder);
		if (!file.exists()) {
			file.mkdirs();
		}

		return folder;
	}

	protected String getDownloadFolder() {
		String folder = getRootPath() + "/download/";
		File file = new File(folder);
		if (!file.exists()) {
			file.mkdirs();
		}

		return folder;
	}
}
