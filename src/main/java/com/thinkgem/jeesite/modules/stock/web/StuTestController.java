/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.stock.web;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.stock.service.FilterStockPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通知通告Controller
 * @author zhongyi
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "test")
public class StuTestController extends BaseController {

	@Autowired
	private FilterStockPolicyService filterStockPolicyService;

	//@RequiresPermissions("oa:oaNotify:view")
	@ResponseBody
	@RequestMapping(value = "check")
	public JSONObject list( HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObjectResult = new JSONObject();
		String name =  request.getParameter("name");
		Integer status = 0;
		if(name.equals("zyares")){
			status = 1;
		}
		if (status != null) {
			jsonObjectResult.put("state", status);
		} else {
			jsonObjectResult.put("state", status);
		}
		return jsonObjectResult;
	}

	@ResponseBody
	@RequestMapping(value = "getArr")
	public JSONObject getArr( HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObjectResult = new JSONObject();

		jsonObjectResult.put("arr", "[1,2,3]");
		jsonObjectResult.put("xml","<?xml version=\"1.0\" encoding=\"utf-8\"?><hello>nihao</hello>");
		return jsonObjectResult;
	}




}