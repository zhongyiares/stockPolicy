package com.thinkgem.jeesite.modules.stock.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.modules.stock.entity.StockInfoVO;
import com.thinkgem.jeesite.modules.stock.util.HttpClientUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/01/08.
 */
public class GetRemoteUrlDataService {

    public final static String SZ = "SZ";
    public final static String SH = "SH";
    public static final String yymmdd = "yyyyMMdd";
    private static Logger logger = LogManager.getLogger(GetRemoteUrlDataService.class);
    /**
     * 远程爬取数据
     * @param windCode
     * @return
     */
    public StockInfoVO getStockInfoWithWindCode(String windCode){
        StockInfoVO stockInfoVO = null;
        try {
            if (windCode != null && !"".equals(windCode)) {
                String[] arr = windCode.split("\\.");
                String url = "http://nuff.eastmoney.com/EM_Finance2015TradeInterface/JS.ashx?id=";
                if (arr[1].equals(SH)) {
                    url += arr[0] + "1";
                } else if (arr[1].equals(SZ)) {
                    url += arr[0] + "2";
                }
                String result = HttpClientUtils.doGet(url);
                String substring = result.substring(9, result.length() - 1);
                JSONObject jsonObject = JSONObject.parseObject(substring);
                JSONArray jsonArray = jsonObject.getJSONArray("Value");

                stockInfoVO = new StockInfoVO();
                stockInfoVO.setStockCode(jsonArray.getString(1));                // 股票代码
                stockInfoVO.setStockName(jsonArray.getString(2));                // 股票名称
                stockInfoVO.setCurrentPrice(jsonArray.getDouble(25));            // 当前价格
                stockInfoVO.setCurrentPriceUpDown(jsonArray.getDouble(27));      // 当前涨跌多少钱(当前价格减去昨日收盘价)
                stockInfoVO.setCurrentPriceUpDownRate(jsonArray.getDouble(29));  // 当前涨跌幅(当前价格减去昨日收盘价再除以昨日收盘价)
                stockInfoVO.setTodayOpenPrice(jsonArray.getDouble(28));          // 今开11.39
                stockInfoVO.setTransactionNum(jsonArray.getDouble(31));          // 成交量26.56万手
                stockInfoVO.setTotalMarketValue(jsonArray.getDouble(46));        // 总市值196.84亿
                stockInfoVO.setPb(jsonArray.getDouble(43));                      // 市净率4.26
                stockInfoVO.setHigh(jsonArray.getDouble(30));                    // 最高11.56
                stockInfoVO.setTransactionTotalPrice(jsonArray.getString(35));   // 成交额3.01亿元
                stockInfoVO.setCirculationMarketValue(jsonArray.getDouble(45));  // 流通市值132.46亿
                stockInfoVO.setTotalValue(jsonArray.getDouble(46));              //总市值
                stockInfoVO.setPriceEarningRatio(jsonArray.getString(38));       // 市盈率102.15
                stockInfoVO.setLow(jsonArray.getDouble(32));                     // 最低11.10
                stockInfoVO.setTurnoverRate(jsonArray.getDouble(37));            // 换手率2.23%
                stockInfoVO.setPreClose(jsonArray.getDouble(34));                // 昨收
                stockInfoVO.setLimitUpPrice(jsonArray.getDouble(23));            //涨停价
            }
        }catch (Exception e){
            logger.info("------------------stockCode is :" + windCode);
            e.printStackTrace();
        }

        return stockInfoVO;
    }

    public StockInfoVO getBigDeal(String windCode){
        StockInfoVO stockInfoVO = null;
        // token2 = 28758b27a75f62dc3065b81f7facb365
        String token = "70f12f2f4f091e459a279469fe49eca5";
        long timeData = System.currentTimeMillis();
        try {
            if (windCode != null && !"".equals(windCode)) {
                String[] arr = windCode.split("\\.");
                String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=";
                if (arr[1].equals(SH)) {
                    url += arr[0] + "1";
                } else if (arr[1].equals(SZ)) {
                    url += arr[0] + "2";
                    token = "28758b27a75f62dc3065b81f7facb365";
                }
                url += "&sty=CTBFTA&token=" + token + "&_=" + timeData;
                String result = HttpClientUtils.doGet(url);
                String substring = result.substring(3, result.length() - 3);
                String[] strings = substring.split(",");
                stockInfoVO = new StockInfoVO();
                stockInfoVO.setBigDeal(Double.parseDouble(strings[13]));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stockInfoVO;
    }

    public Double getSpeed(String stockCode){
        try {
            if (stockCode != null && !"".equals(stockCode)) {
                String url = "http://www.iwencai.com/stockpick/search?w=%E6%B6%A8%E9%80%9F" + stockCode;
                String result = HttpClientUtils.doGet(url);
                String sub = result.substring(result.indexOf("\"result\":"), result.indexOf(",\"isEmpty\""));
                String[] strings = sub.split("\"");
                String speed = strings[11];
                return Double.valueOf(speed);
            }
            return 0.0;
        }catch (NumberFormatException e) {
            return 0.0;
        } catch (Exception e){
            e.printStackTrace();
            return 0.0;
        }

    }

    /**
     * 远程爬取数据
     * @param stockCode
     * @return
     */
    public StockInfoVO getStockInfoWithStockCode(String stockCode){
        StockInfoVO stockInfoVO = null;
        if (stockCode != null && !"".equals(stockCode)) {
            String url = "";
            if (stockCode.startsWith("60")){
                url = "http://nuff.eastmoney.com/EM_Finance2015TradeInterface/JS.ashx?id="+stockCode+"1";
            }else {
                url = "http://nuff.eastmoney.com/EM_Finance2015TradeInterface/JS.ashx?id="+stockCode+"2";
            }
            String result = HttpClientUtils.doGet(url);
            String substring = result.substring(9, result.length() - 1);
            JSONObject jsonObject = JSONObject.parseObject(substring);
            JSONArray jsonArray = jsonObject.getJSONArray("Value");

            stockInfoVO = new StockInfoVO();
            stockInfoVO.setStockCode(jsonArray.getString(1));                // 股票代码
            stockInfoVO.setStockName(jsonArray.getString(2));                // 股票名称
            stockInfoVO.setCurrentPrice(jsonArray.getDouble(25));            // 当前价格
            stockInfoVO.setCurrentPriceUpDown(jsonArray.getDouble(27));      // 当前涨跌多少钱(当前价格减去昨日收盘价)
            stockInfoVO.setCurrentPriceUpDownRate(jsonArray.getDouble(29));  // 当前涨跌幅(当前价格减去昨日收盘价再除以昨日收盘价)
            stockInfoVO.setTodayOpenPrice(jsonArray.getDouble(28));          // 今开11.39
            stockInfoVO.setTransactionNum(jsonArray.getDouble(31));          // 成交量26.56万手
            stockInfoVO.setTotalMarketValue(jsonArray.getDouble(46));        // 总市值196.84亿
            stockInfoVO.setPb(jsonArray.getDouble(43));                      // 市净率4.26
            stockInfoVO.setHigh(jsonArray.getDouble(30));                    // 最高11.56
            stockInfoVO.setTransactionTotalPrice(jsonArray.getString(35));   // 成交额3.01亿元
            stockInfoVO.setCirculationMarketValue(jsonArray.getDouble(45));  // 流通市值132.46亿
            stockInfoVO.setPriceEarningRatio(jsonArray.getString(38));       // 市盈率102.15
            stockInfoVO.setLow(jsonArray.getDouble(32));                     // 最低11.10
            stockInfoVO.setTurnoverRate(jsonArray.getDouble(37));            // 换手率2.23%
            stockInfoVO.setPreClose(jsonArray.getDouble(34));                // 昨收
        }
        return stockInfoVO;
    }




    public StockInfoVO getBigDealStockCode(String stockCode){
        StockInfoVO stockInfoVO = null;
        String token = "70f12f2f4f091e459a279469fe49eca5";
        if (stockCode != null && !"".equals(stockCode)) {
            String url = "";
            if (stockCode.startsWith("60")){
                url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd="+stockCode+"1";
            }else {
                url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd="+stockCode+"2";
            }
            url += "&sty=CTBFTA&token="+token;
            String result = HttpClientUtils.doGet(url);
            String substring = result.substring(3, result.length() - 3);
            String[] strings = substring.split(",");
            stockInfoVO = new StockInfoVO();
            stockInfoVO.setBigDeal(Double.parseDouble(strings[13]));
        }
        return stockInfoVO;
    }


    public static void main(String[] args) {
        GetRemoteUrlDataService g = new GetRemoteUrlDataService();
        StockInfoVO s = g.getBigDealStockCode("000001");
        System.out.println(s);
    }


}
