package com.toolbox.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toolbox.DataObject;

@RestController
public class HtmlController extends BaseController {

	@RequestMapping("/html.make")
	public String make(@RequestBody String input) {

		DataObject dobj = this.getRequestDataObject(input);
		manager.make(dobj.getBody());
		
		return getOutputString(dobj);
	}

	@PostMapping("/html.index")
	public String add(@RequestParam("request") String input) {
		DataObject dobj = this.getRequestDataObject(input);

		// Date currentDate = new Date();
		// dobj.put("gmt_created", currentDate);
		// dobj.put("gmt_modified", currentDate);
		// dobj.put("group_id", AppContext.getGroupId());
		//
		// Map data = riskRuleService.addEntity(dobj.getBody());
		//
		// if (data == null) {
		// dobj.setStatusCode("301");
		// dobj.setStatusText("添加规则失败。");
		// }

		return getOutputString(dobj);
	}

	@PostMapping("/html.homepage")
	public String update(@RequestParam("request") String input) {
		DataObject dobj = this.getRequestDataObject(input);

		Date currentDate = new Date();
		// dobj.put("gmt_modified", currentDate);
		// dobj.put("group_id", AppContext.getGroupId());
		//
		// Map wheres = new HashMap();
		// wheres.put("id", dobj.getBodyValue("id"));
		// dobj.putBodyValue("where", wheres);
		//
		// riskRuleService.updateEntity(dobj.getBody());
		//
		// dobj.getBody().clear();
		return getOutputString(dobj);
	}

}
