package com.toolbox.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toolbox.service.HtmlService;

import io.swagger.annotations.ApiOperation;

@RestController
public class HelloController {

	@Value("${com.trade.riskcontrol.name}")
	private String name;

	@Autowired
	protected HtmlService htmlService;
	
	@RequestMapping("/hello")
	@ApiOperation(value = "test the server", notes = "abc")
	public String index() {
		htmlService.getEntitys(new HashMap());
		
		return "Hello World";
	}

}