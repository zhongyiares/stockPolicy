package com.thinkgem.jeesite.modules.stock.service.multiThread.callable;

import com.thinkgem.jeesite.modules.stock.entity.StockInfoVO;
import com.thinkgem.jeesite.modules.stock.service.GetRemoteUrlDataService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by zhongyi on 2017/8/31 0031.
 */
public class StockDataDealProcessCallable implements Callable<StockInfoVO> {

    public static final Integer BILLION = 100000000;

    private Map map;

    private GetRemoteUrlDataService getRemoteUrlDataService = new GetRemoteUrlDataService();

    public StockDataDealProcessCallable(Map map) {
        this.map = map;
    }

    @Override
    public StockInfoVO call() throws Exception {
        String windCode = (String)map.get("s_info_windcode");
        Double diffVal = ((BigDecimal) map.get("val")).doubleValue();
        StockInfoVO stockInfoVO = getRemoteUrlDataService.getStockInfoWithWindCode(windCode);
        StockInfoVO stockInfoVOBig = getRemoteUrlDataService.getBigDeal(windCode);
        stockInfoVO.setDiffVal(diffVal);
        stockInfoVO.setBigDeal(stockInfoVOBig.getBigDeal());
        stockInfoVO.setStockCode(windCode);
        int conditionNum = getConditionNum(stockInfoVO);
        stockInfoVO.setConditionNum(conditionNum);
        return stockInfoVO;
    }

    public int getConditionNum(StockInfoVO val ) {
        int num = 0;
        //市值小于500亿 num +1
        if( (val.getCirculationMarketValue() / BILLION ) <= 500 ){
            num++;
        }
        //净流入金额大于0 num+1
        if(val.getBigDeal() > 0 ){
            num++;
        }
        //换手率大于等于9% num +1
        if(val.getTurnoverRate() > 9 ){
            num++;
        }
        // 市盈率小于300 num + 1
        try{
            double er = Double.parseDouble(val.getPriceEarningRatio());
            if( er  < 300 ){
                num++;
            }
        }catch (Exception e){
            //logger.info("----conditionNum:---股票:"+ bizCorrelation.getStockCode() +",市盈率 :"+val.getPriceEarningRatio());
        }
        //差值小与-1 num+1
        if(val.getDiffVal() < -1){
            num++;
        }
        return num;
    }
}
