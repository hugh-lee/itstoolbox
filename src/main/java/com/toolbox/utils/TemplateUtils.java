package com.toolbox.utils;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

public class TemplateUtils {

	private static VelocityEngine ve;

	static {
		// 初始化Velocity模板引擎
		ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "class,file,jar");
		ve.setProperty(RuntimeConstants.RUNTIME_LOG_REFERENCE_LOG_INVALID, true);
		ve.init();
	}

	public static String evaluate(String templateString, Map model) {
		try {
			StringWriter writer = new StringWriter();
			VelocityContext context = new VelocityContext(model);
			ve.evaluate(context, writer, "null", templateString);
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
