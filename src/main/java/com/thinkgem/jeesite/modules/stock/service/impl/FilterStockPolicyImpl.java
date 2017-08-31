package com.thinkgem.jeesite.modules.stock.service.impl;

import com.thinkgem.jeesite.modules.stock.entity.StockInfoVO;
import com.thinkgem.jeesite.modules.stock.service.FilterStockPolicyService;
import com.thinkgem.jeesite.modules.stock.service.multiThread.MutilDealStockDataListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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

    @Autowired
    private MutilDealStockDataListService mutilDealStockDataListService;

    @Override
    public List filterStock(String startDate, String endDate, double stockPrice ,double less ,int startNo , int endNo) {
        String sql = "select * from (select a.*, ROWNUM AS rowno " +
                "from ( select * from ( " +
                "select m.s_info_windcode, m.s_dq_close as bClose, n.s_dq_close aclose, (m.s_dq_close - n.s_dq_close) as val " +
                "from (select f.s_info_windcode, f.s_dq_close from Ashareeodprices f where f.trade_dt = ? and s_Dq_Tradestatus != '停牌' ) m " +
                "join (select f.s_info_windcode, f.s_dq_close from Ashareeodprices f where f.trade_dt = ? and s_Dq_Tradestatus != '停牌' ) n on m.s_info_windcode = n.s_info_windcode where m.s_dq_close < ? ) t" +
                " where t.val < ?  order by val,bClose ) a where ROWNUM <= ? ) table_alias where table_alias.rowno >= ? ";
        return  jdbcTemplateForRD.queryForList(sql,new Object[]{endDate,startDate,stockPrice,less,endNo,startNo});
    }

    @Override
    public List<StockInfoVO> conditionFilterStock(String startDate, String endDate, double stockPrice ,double less ,int startNo , int endNo){
        List<Map> dataList = filterStock(startDate,endDate,stockPrice,less ,startNo,endNo);
        List<StockInfoVO> stockInfoVOList = new ArrayList<StockInfoVO>();
        try {
            stockInfoVOList =   mutilDealStockDataListService.dealDataList(dataList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return stockInfoVOList;
    }
}
