package com.toolbox.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toolbox.AppContext;
import com.toolbox.AppException;
import com.toolbox.DataObject;

import net.sf.json.JSONObject;

@ControllerAdvice
public class GlobalExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		DataObject dobj = AppContext.getDataObject();
		if (dobj == null)
			dobj = new DataObject();

		if (e instanceof AppException) {
			dobj.setStatusCode(((AppException) e).getCode());
			dobj.setStatusCode(((AppException) e).getMessage());
		} else {
			dobj.setStatusCode("999");
			dobj.setStatusText(e.getMessage());
			dobj.setStatusException(e);
		}

		return JSONObject.fromObject(dobj).toString();
	}

}