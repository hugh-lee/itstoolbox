package com.toolbox.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.toolbox.AppException;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

public class JsonUtils {

	/** configure json output */
	public static JsonConfig jsonConfig;

	public static final String Default_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String Default_DATE_PATTERN = "yyyy-MM-dd";

	private static DateFormat dateTimeFormat = new SimpleDateFormat(Default_DATE_TIME_PATTERN);
	private static DateFormat dateFormat = new SimpleDateFormat(Default_DATE_PATTERN);

	static {

		jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name != null && name.toLowerCase().indexOf("password") != -1)
					return true;

				return false;
			}
		});

		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new JsonValueProcessor() {

			public Object processArrayValue(Object value, JsonConfig jsonConfig) {
				return process(value);
			}

			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
				return process(value);
			}

			private Object process(Object value) {
				if (value == null)
					return null;
				return dateTimeFormat.format(new Date(((java.sql.Timestamp) value).getTime()));
			}
		});

		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessor() {

			private DateFormat dateFormat = new SimpleDateFormat(Default_DATE_PATTERN);

			public Object processArrayValue(Object value, JsonConfig jsonConfig) {
				return process(value);
			}

			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
				return process(value);
			}

			private Object process(Object value) {
				if (value == null)
					return null;

				return dateFormat.format((Date) value);
			}
		});

		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new JsonValueProcessor() {

			public Object processArrayValue(Object value, JsonConfig jsonConfig) {
				return process(value);
			}

			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
				return process(value);
			}

			private Object process(Object value) {
				if (value == null)
					return null;
				return dateTimeFormat.format((Date) value);
			}
		});

	}

	public static Date GetDate(JSONObject json) {
		try {
			return (Date) JSONObject.toBean(json, Date.class);
		} catch (Exception ex) {

		}
		return null;
	}

	public static String toJsonString(Object obj) {
		try {
			ObjectMapper mapper = new ObjectMappingCustomer();
			return mapper.writeValueAsString(obj);
		} catch (IOException e) {
			throw new AppException("999", e);
		}
	}

}

class ObjectMappingCustomer extends ObjectMapper {

	public ObjectMappingCustomer() {
		super();

		// 空值处理为空串

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		this.setDateFormat(df);

		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

			@Override
			public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
					throws IOException, JsonProcessingException {
				jg.writeString("");
			}

		});

	}
}
