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

    @Override
    public List<String> getTradeDatesByNum(String tradeDate, Integer num) {
        String sql = "select f.* from (select trade_days from AShareCalendar t where trade_days <=  ? and t.s_info_exchmarket = 'SZSE' order by trade_days desc) f where rownum <=  ? ";
        return  jdbcTemplateForRD.queryForList(sql,new Object[]{tradeDate,num},String.class);
    }

    @Override
    public List<StockInfoVO> getStockListByDateList(List<String> tradeDateList) {
        List<StockInfoVO> stockInfoVOList = new ArrayList<StockInfoVO>();
        try {
            //List<String> firstFilterStockList = new ArrayList<String>();
            List<String> filterStockList = new ArrayList<String>();
            List<String> subFilterStockList = new ArrayList<String>();
            List<String> newSubFilterStockList = new ArrayList<String>();
            if(tradeDateList != null && tradeDateList.size() >0){
                for(String tradeDate : tradeDateList){
                    subFilterStockList = new ArrayList<String>();
                    newSubFilterStockList = new ArrayList<String>();
                    int size = filterStockList.size();
                    if(size > 0){
                        for(int i=0;i< size ;i++){
                            subFilterStockList.add(filterStockList.get(i));
                            if( subFilterStockList.size() % 900 == 0){
                                subFilterStockList = getStockList(subFilterStockList,tradeDate);
                                newSubFilterStockList.addAll(subFilterStockList);
                                subFilterStockList = new ArrayList<String>();
                            }
                        }
                        subFilterStockList = getStockList(subFilterStockList,tradeDate);
                        newSubFilterStockList.addAll(subFilterStockList);
                        filterStockList = newSubFilterStockList;
                    }else {
                        filterStockList = getStockList(filterStockList,tradeDate);
                    }
                }
            }
            stockInfoVOList =  mutilDealStockDataListService.dealStockCodeList(filterStockList);
        }catch (Exception e){
            e.printStackTrace();
        }

        return stockInfoVOList;
    }

    private  List<String> getStockList(List stockList,String tradeDate){
        StringBuilder sb = new StringBuilder();
        sb.append("select s_info_windcode from Ashareeodprices t where trade_dt = ? and t.s_dq_change < 0 ");
        if(stockList != null && stockList.size() >0){
            sb.append("and t.s_info_windcode in ( ");
            for(int i=0 ;i< stockList.size() ;i++){
                if(i != stockList.size() -1 ){
                    sb.append("'"+stockList.get(i)+"',");
                }else {
                    sb.append("'"+stockList.get(i)+"'");
                }
            }
            sb.append(" )");
        }
        return jdbcTemplateForRD.queryForList(sb.toString(),new Object[]{tradeDate},String.class);
    }
}
