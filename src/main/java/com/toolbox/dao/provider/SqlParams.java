package com.toolbox.dao.provider;

import java.util.HashMap;

public class SqlParams<K, V> extends HashMap<K, V> {
	private String tableName;

	private String sort;

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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
