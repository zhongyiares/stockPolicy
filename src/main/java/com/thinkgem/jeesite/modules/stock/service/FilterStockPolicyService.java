package com.thinkgem.jeesite.modules.stock.service;

import com.thinkgem.jeesite.modules.stock.entity.StockInfoVO;

import java.util.List;

/**
 * Created by zhongyi on 2017/8/25 0025.
 */
public interface FilterStockPolicyService {

    public List filterStock(String startDate, String endDate, double stockPrice, double less);

    public List<StockInfoVO> conditionFilterStock(String startDate, String endDate, double stockPrice, double less);
}
