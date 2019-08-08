package com.toolbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toolbox.dao.HtmlMapper;

@Service
public class HtmlService extends BaseService {

	public static final String TABLE = "html";

	@Autowired
	private HtmlMapper htmlMapper;

	protected String getTable() {
		return TABLE;
	}
}
