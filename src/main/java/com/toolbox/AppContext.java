package com.toolbox;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AppContext {

	static ThreadLocal<Map> threadLocal = new ThreadLocal<Map>();

	public static void set(String key, Object value) {
		Map map = threadLocal.get();
		if (map == null) {
			map = new HashMap();
			threadLocal.set(map);
		}

		map.put(key, value);
	}

	public static Object get(String key) {
		Map map = threadLocal.get();
		if (map == null) {
			map = new HashMap();
			threadLocal.set(map);
		}
		return map.get(key);
	}

	public static void remove(String key) {
		Map map = threadLocal.get();
		if (map == null) {
			map = new HashMap();
			threadLocal.set(map);
		}
		map.remove(key);
	}

	public static String getAction() {
		return (String) get("action");
	}

	public static void setAction(String action) {
		set("action", action);
	}

	public static String getUserId() {
		HttpSession session = getHttpSession();
		if (session == null)
			return null;

		Map user = (Map) session.getAttribute(AppConstants.SESSION_USER);
		if (user == null)
			return null;
		return (String) user.get("id");
	}
	
	public static String getLoginName() {
		HttpSession session = getHttpSession();
		if (session == null)
			return null;

		Map user = (Map) session.getAttribute(AppConstants.SESSION_USER);
		if (user == null)
			return null;
		return (String) user.get("login_name");
	}

	public static String getGroupId() {
		HttpSession session = getHttpSession();
		if (session == null)
			return null;

		Map user = (Map) session.getAttribute(AppConstants.SESSION_USER);
		if (user == null)
			return null;
		return (String) user.get("group_id");
	}

	public static HttpSession getHttpSession() {
		return (HttpSession) get("httpSession");
	}

	public static void setHttpSession(HttpSession httpSession) {
		set("httpSession", httpSession);
	}

	public static void setHttpServletRequest(HttpServletRequest request) {
		set("request", request);
	}

	public static HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) get("request");
	}

	public static void setHttpServletResponse(HttpServletResponse response) {
		set("response", response);
	}

	public static HttpServletResponse getHttpServletResponse() {
		return (HttpServletResponse) get("response");
	}
	
	public static void setDataObject(DataObject dobj) {
		set("dataObject", dobj);
	}

	public static DataObject getDataObject() {
		return (DataObject) get("dataObject");
	}
}
