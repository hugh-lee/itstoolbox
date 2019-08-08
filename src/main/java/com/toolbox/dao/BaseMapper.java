package com.toolbox.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.github.pagehelper.Page;
import com.toolbox.dao.provider.SqlParams;
import com.toolbox.dao.provider.SqlProvider;

@Mapper
public interface BaseMapper {

	@SelectProvider(type = SqlProvider.class, method = "desc")
	List<Map> desc(String table);
	
	@SelectProvider(type = SqlProvider.class, method = "select")
	Map select(SqlParams params);
	
	@SelectProvider(type = SqlProvider.class, method = "select")
	List<Map> selects(SqlParams params);
	
	@SelectProvider(type = SqlProvider.class, method = "select")
	Page<Map> selectsByPage(SqlParams params);
	
	@InsertProvider(type = SqlProvider.class, method = "insert")
	@Options(useGeneratedKeys = true)
	int insert(SqlParams params);
	
	@UpdateProvider(type = SqlProvider.class, method = "update")
	int update(SqlParams params);
	
	@DeleteProvider(type = SqlProvider.class, method = "delete")
	int delete(SqlParams params);
}
