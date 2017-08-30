package com.thinkgem.jeesite.modules.stock.service.impl;

import com.thinkgem.jeesite.modules.stock.entity.StockInfoVO;
import com.thinkgem.jeesite.modules.stock.service.FilterStockPolicyService;
import com.thinkgem.jeesite.modules.stock.service.GetRemoteUrlDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by zhongyi on 2017/8/25 0025.
 */
@Service
public class FilterStockPolicyImpl implements FilterStockPolicyService {

    @Autowired
    private JdbcTemplate jdbcTemplateForRD;

    private GetRemoteUrlDataService getRemoteUrlDataService = new GetRemoteUrlDataService() ;

    @Override
    public List filterStock(String startDate, String endDate, double stockPrice ,double less) {
        String sql = "select * from ( select m.s_info_windcode, m.s_dq_close as bClose, n.s_dq_close aclose, (m.s_dq_close - n.s_dq_close) as val from (select f.s_info_windcode, f.s_dq_close from Ashareeodprices f where f.trade_dt = ? ) m join (select f.s_info_windcode, f.s_dq_close from Ashareeodprices f where f.trade_dt = ? ) n on m.s_info_windcode = n.s_info_windcode where m.s_dq_close < ? ) t where t.val < ?  order by val,bClose ";
        return  jdbcTemplateForRD.queryForList(sql,new Object[]{endDate,startDate,stockPrice,less});
    }

    @Override
    public List<StockInfoVO> conditionFilterStock(String startDate, String endDate, double stockPrice ,double less ){
        List<Map> dataList = filterStock(startDate,endDate,stockPrice,less);
        List<StockInfoVO> stockInfoList = new ArrayList<StockInfoVO>();
        StockInfoVO stockInfoVO = null;
        for(int i= 0 ; i< dataList.size() ;i++){
            Map map = dataList.get(i);
            String windCode = (String)map.get("s_info_windcode");
            Double diffVal = ((BigDecimal) map.get("val")).doubleValue();
            stockInfoVO = getRemoteUrlDataService.getStockInfoWithWindCode(windCode);
            StockInfoVO stockInfoVOBig = getRemoteUrlDataService.getBigDeal(windCode);
            stockInfoVO.setDiffVal(diffVal);
            stockInfoVO.setBigDeal(stockInfoVOBig.getBigDeal());
            stockInfoVO.setStockCode(windCode);
            stockInfoList.add(stockInfoVO);
        }
        return stockInfoList;
    }
}
