package com.toolbox.dao.provider;

import java.util.HashMap;

public class SqlParams<K, V> extends HashMap<K, V> {
	private String tableName;

	public SqlParams() {
	}

	public SqlParams(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
