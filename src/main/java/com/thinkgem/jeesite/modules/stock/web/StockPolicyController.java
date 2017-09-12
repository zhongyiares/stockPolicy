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
	public JSONObject list( HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObjectResult = new JSONObject();
		String beginDate =  request.getParameter("beginDate");
		String endDate =  request.getParameter("endDate");
		String stockPrice =  request.getParameter("stockPrice");
		String diffVal =  request.getParameter("diffVal");
		List<StockInfoVO> stockInfoVOList =  filterStockPolicyService.conditionFilterStock(beginDate,endDate,Double.parseDouble(stockPrice),Double.parseDouble(diffVal),0,50);
		if (stockInfoVOList != null) {
			jsonObjectResult.put("state", 0);
			jsonObjectResult.put("stockInfoVOList",stockInfoVOList);
		} else {
			jsonObjectResult.put("state", 1);
		}
		return jsonObjectResult;
	}

	@ResponseBody
	@RequestMapping(value = "continuDownPolicy")
	public JSONObject continuDownPolicy( HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObjectResult = new JSONObject();
		String tradeDate =  request.getParameter("tradeDate");
		Integer nums =  Integer.parseInt(request.getParameter("nums"));
		List<String> tradeDateList = filterStockPolicyService.getTradeDatesByNum(tradeDate,nums);
		List<StockInfoVO> stockInfoVOList =  filterStockPolicyService.getStockListByDateList(tradeDateList);
		if (stockInfoVOList != null) {
			jsonObjectResult.put("state", 0);
			jsonObjectResult.put("stockInfoVOList",stockInfoVOList);
		} else {
			jsonObjectResult.put("state", 1);
		}
		return jsonObjectResult;
	}


}