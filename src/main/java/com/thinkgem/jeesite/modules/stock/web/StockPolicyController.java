/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.stock.web;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.stock.entity.StockInfoVO;
import com.thinkgem.jeesite.modules.stock.service.FilterStockPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 通知通告Controller
 * @author zhongyi
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "policy")
public class StockPolicyController extends BaseController {

	@Autowired
	private FilterStockPolicyService filterStockPolicyService;

	//@RequiresPermissions("oa:oaNotify:view")
	@ResponseBody
	@RequestMapping(value = "view")
	public JSONObject list( HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject jsonObjectResult = new JSONObject();
		List<StockInfoVO> stockInfoVOList =  filterStockPolicyService.conditionFilterStock("20170801","20170825",15,-3);
		if (stockInfoVOList != null) {
			jsonObjectResult.put("state", 0);
			jsonObjectResult.put("stockInfoVOList",stockInfoVOList);
		} else {
			jsonObjectResult.put("state", 1);
		}
		return jsonObjectResult;
	}


}