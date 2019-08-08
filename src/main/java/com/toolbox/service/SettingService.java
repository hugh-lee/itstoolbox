package com.toolbox.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toolbox.dao.SettingMapper;

@Service
public class SettingService extends BaseService {

	public static final String TABLE = "setting";

	public static Map<String, String> settings = new HashMap<String, String>();
	@Autowired
	private SettingMapper settingMapper;

	protected String getTable() {
		return TABLE;
	}

	public String getValue(String key) {
		if (settings.isEmpty()) {
			List<Map> entitys = this.getEntitys(new HashMap());
			for (int i = 0, size = entitys.size(); i < size; i++) {
				Map<String, String> entity = entitys.get(i);
				settings.put(entity.get("name"), entity.get("value"));
			}
		}

		return settings.get(key);
	}

	public Map<String, String> getSettings() {
		if (settings.isEmpty()) {
			List<Map> entitys = this.getEntitys(new HashMap());
			for (int i = 0, size = entitys.size(); i < size; i++) {
				Map<String, String> entity = entitys.get(i);
				settings.put(entity.get("name"), entity.get("value"));
			}
		}

		return settings;
	}
}
