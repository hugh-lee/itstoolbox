package com.toolbox;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

public class DataObject extends HashMap {
	
	public DataObject() {
		this.put("header", new DataObjectMap());
		this.put("body", new DataObjectMap());
		this.put("status", new DataObjectMap());
	}

	public DataObject(String dataString) {
		this();

		JSONObject jsonObject = JSONObject.fromObject(dataString);
		if (jsonObject.get("header") != null)
			this.setHeader(new DataObjectMap((Map) jsonObject.get("header")));
		if (jsonObject.get("body") != null)
			this.setBody(new DataObjectMap((Map) jsonObject.get("body")));
		if (jsonObject.get("status") != null)
			this.setStatus(new DataObjectMap((Map) jsonObject.get("status")));
	}

	public Map getHeader() {
		return (Map) this.get("header");
	}

	public void setHeader(Map header) {
		this.put("header", header);
	}

	public Map getStatus() {
		if (this.get("status") == null)
			this.setStatus(new DataObjectMap());
		return (Map) this.get("status");
	}

	public void setStatus(Map status) {
		this.put("status", status);
	}

	public Map getBody() {
		return (Map) this.get("body");
	}

	public void setBody(Map body) {
		this.put("body", body);
	}

	public Object getBodyValue(String key) {
		return this.getBody().get(key);
	}

	public void putBodyValue(String key, Object value) {
		this.getBody().put(key, value);
	}

	public Object removeBodyValue(String key) {
		return this.getBody().remove(key);
	}

	public String getAction() {
		return (String) this.getHeader().get("action");
	}

	public void setAction(String action) {
		this.getHeader().put("action", action);

	}

	public String getIp() {
		return (String) this.getHeader().get("ip");
	}

	public void setIp(String ip) {
		this.getHeader().put("ip", ip);
	}

	public void setStatusCode(String value) {
		this.getStatus().put("code", value);
	}

	public String getStatusCode() {
		return (String) this.getStatus().get("code");
	}

	public void setStatusText(String value) {
		this.getStatus().put("text", value);
	}

	public String getStatusText() {
		return (String) this.getStatus().get("text");
	}

	public void setStatusException(Throwable ex) {
		this.getStatus().put("exception", getStack(ex));
	}

	public String getStatusException() {
		return (String) this.getStatus().get("exception");
	}

	public boolean isSuccess() {
		if (this.getStatusCode() == null || "0000".equals(this.getStatusCode()))
			return true;

		return false;
	}

	private String getStack(Throwable ex) {
		StringWriter sw = new StringWriter();
		PrintWriter wt = new PrintWriter(sw);
		ex.printStackTrace(wt);
		String result = sw.toString();

		wt.close();
		try {
			sw.close();
		} catch (IOException e) {

		}

		if (result.length() > 500)
			result = result.substring(0, 500) + " ......";
		return result;
	}

}

class DataObjectMap<K, V> extends HashMap<K, V> {
	public DataObjectMap() {
		super();
	}

	public DataObjectMap(Map<? extends K, ? extends V> m) {
		super(m);
	}

	public V get(Object key) {
		V vlue = super.get(key);
		if (vlue instanceof JSONNull)
			return null;
		return vlue;
	}

	public V remove(Object key) {
		V value = super.remove(key);
		if (value instanceof JSONNull)
			return null;

		return value;
	}

}
