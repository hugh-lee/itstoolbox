package com.toolbox.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.toolbox.dao.BaseMapper;
import com.toolbox.dao.provider.SqlParams;

public abstract class BaseService {

	@Autowired
	private BaseMapper baseMapper;

	protected abstract String getTable();

	public List<Map> descTable() {
		return baseMapper.desc(getTable());
	}

	public Map getEntity(Map data) {
		return baseMapper.select(createSqlParams(data));
	}

	public List<Map> getEntitys(Map data) {
		return baseMapper.selects(createSqlParams(data));
	}

	public List<Map> getEntitys(Map data, String orderby) {
		return baseMapper.selects(createSqlParams(data, orderby));
	}

	public Page<Map> getEntitys(Map data, int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		return baseMapper.selectsByPage(createSqlParams(data));
	}

	public SqlParams addEntity(Map data) {
		SqlParams params = createSqlParams(data);
		baseMapper.insert(params);

		return params;
	}

	public int updateEntity(Map data) {
		return baseMapper.update(createSqlParams(data));
	}

	public int deleteEntity(Map data) {
		return baseMapper.delete(createSqlParams(data));
	}

	public SqlParams createSqlParams(Map data) {
		SqlParams params = new SqlParams(getTable());
		params.putAll(data);

		return params;
	}

	private SqlParams createSqlParams(Map data, String orderby) {
		SqlParams sqlParams = createSqlParams(data);
		sqlParams.setSort(orderby);
		return sqlParams;
	}
}
