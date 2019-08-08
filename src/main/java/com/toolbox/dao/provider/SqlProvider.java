package com.toolbox.dao.provider;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.jdbc.SQL;

public class SqlProvider {

	public String desc(String table) {		
		return "desc " + table;
	}
	
	public String select(SqlParams params) {
		String table = params.getTableName();
		SQL sql = new SQL().SELECT("*").FROM(table);

		Iterator itera = params.entrySet().iterator();
		while (itera.hasNext()) {
			Entry entry = (Entry) itera.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			sql.WHERE(key + "=#{" + key + "}");

		}
		return sql.toString();
	}

	public String insert(SqlParams params) {

		String table = params.getTableName();

		SQL sql = new SQL();
		sql.INSERT_INTO(table);
		Iterator itera = params.entrySet().iterator();
		while (itera.hasNext()) {
			Entry entry = (Entry) itera.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			sql.VALUES((String) key, "#{" + key + "}");

		}

		return sql.toString();
	}

	public String update(SqlParams params) {

		String table = params.getTableName();
		Map wheres = (Map) params.remove("where");

		SQL sql = new SQL();
		sql.UPDATE(table);
		Iterator itera = params.entrySet().iterator();
		while (itera.hasNext()) {
			Entry entry = (Entry) itera.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			sql.SET(key + "=#{" + key + "}");
		}

		itera = wheres.entrySet().iterator();
		while (itera.hasNext()) {
			Entry entry = (Entry) itera.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();

			if (params.containsKey(key)) {
				key = key + "_1";
			}
			params.put(key, value);

			sql.WHERE(key + "=#{" + key + "}");

		}
		return sql.toString();
	}

	public String delete(SqlParams params) {
		String table = params.getTableName();
		SQL sql = new SQL().DELETE_FROM(table);

		Iterator itera = params.entrySet().iterator();
		while (itera.hasNext()) {
			Entry entry = (Entry) itera.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			sql.WHERE(key + "=#{" + key + "}");

		}
		return sql.toString();
	}
}
