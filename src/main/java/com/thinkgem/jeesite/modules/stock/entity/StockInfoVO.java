package com.thinkgem.jeesite.modules.stock.entity;

/**
 * 个股基本信息（用于个股搜索）
 * Author: qingcj
 * Date: 2016/11/10.
 */
public class StockInfoVO {
    // 股票代码
    private String stockCode;
    // 股票名称
    private String stockName;
    // 是否被关注 0 未关注 1 已关注
    private int isConcerned;
    //是否为样本股
    private String isBelongBio;

    // 当前价格
    private double currentPrice;
    // 当前涨跌多少钱(当前价格减去昨日收盘价)
    private double currentPriceUpDown;
    // 当前涨跌幅(当前价格减去昨日收盘价再除以昨日收盘价)
    private double currentPriceUpDownRate;


    // 今开11.39
    private double todayOpenPrice;
    // 成交量26.56万手
    private double transactionNum;
    // 总市值196.84亿
    private double totalMarketValue;
    // 市净率4.26
    private double pb;
    // 最高11.56
    private double high;
    // 成交额3.01亿元
    private String transactionTotalPrice;
    // 流通市值132.46亿
    private double circulationMarketValue;
    //总市值
    private double totalValue;
    // 市盈率102.15
    private String  priceEarningRatio ; //double类型
    // 最低11.10
    private double low;
    //换手率2.23%
    private double turnoverRate;
    //昨收价
    private double preClose;
    //大单净流入额
    private Double bigDeal;
    //涨停价
    private Double limitUpPrice;
    //差值
    private Double diffVal;
    //日Alpha
    private double dayAlpha;
    //日beta
    private double dayBeta;
    //周Alpha
    private double weekAlpha;
    //周beta
    private double weekBeta;
    //月Alpha
    private double monthAlpha;
    //月beta
    private double monthBeta;
    //年Alpha
    private double yearAlpha;
    //年beta
    private double yearBeta;
    //条件个数
    private Integer conditionNum;

    public Double getBigDeal() {
        return bigDeal;
    }

    public void setBigDeal(Double bigDeal) {
        this.bigDeal = bigDeal;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public Double getLimitUpPrice() {
        return limitUpPrice;
    }

    public void setLimitUpPrice(Double limitUpPrice) {
        this.limitUpPrice = limitUpPrice;
    }

    public String getIsBelongBio() {
        return isBelongBio;
    }

    public void setIsBelongBio(String isBelongBio) {
        this.isBelongBio = isBelongBio;
    }

    public int getIsConcerned() {
        return isConcerned;
    }

    public void setIsConcerned(int isConcerned) {
        this.isConcerned = isConcerned;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getCurrentPriceUpDown() {
        return currentPriceUpDown;
    }

    public void setCurrentPriceUpDown(double currentPriceUpDown) {
        this.currentPriceUpDown = currentPriceUpDown;
    }

    public double getCurrentPriceUpDownRate() {
        return currentPriceUpDownRate;
    }

    public void setCurrentPriceUpDownRate(double currentPriceUpDownRate) {
        this.currentPriceUpDownRate = currentPriceUpDownRate;
    }

    public double getTodayOpenPrice() {
        return todayOpenPrice;
    }

    public void setTodayOpenPrice(double todayOpenPrice) {
        this.todayOpenPrice = todayOpenPrice;
    }

    public double getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(double transactionNum) {
        this.transactionNum = transactionNum;
    }

    public double getTotalMarketValue() {
        return totalMarketValue;
    }

    public void setTotalMarketValue(double totalMarketValue) {
        this.totalMarketValue = totalMarketValue;
    }

    public double getPb() {
        return pb;
    }

    public void setPb(double pb) {
        this.pb = pb;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public String getTransactionTotalPrice() {
        return transactionTotalPrice;
    }

    public void setTransactionTotalPrice(String transactionTotalPrice) {
        this.transactionTotalPrice = transactionTotalPrice;
    }

    public double getCirculationMarketValue() {
        return circulationMarketValue;
    }

    public void setCirculationMarketValue(double circulationMarketValue) {
        this.circulationMarketValue = circulationMarketValue;
    }

    public String getPriceEarningRatio() {
        return priceEarningRatio;
    }

    public void setPriceEarningRatio(String priceEarningRatio) {
        this.priceEarningRatio = priceEarningRatio;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public double getPreClose() {
        return preClose;
    }

    public void setPreClose(double preClose) {
        this.preClose = preClose;
    }

    public Double getDiffVal() {
        return diffVal;
    }

    public void setDiffVal(Double diffVal) {
        this.diffVal = diffVal;
    }

    public double getDayAlpha() {
        return dayAlpha;
    }

    public void setDayAlpha(double dayAlpha) {
        this.dayAlpha = dayAlpha;
    }

    public double getDayBeta() {
        return dayBeta;
    }

    public void setDayBeta(double dayBeta) {
        this.dayBeta = dayBeta;
    }

    public double getWeekAlpha() {
        return weekAlpha;
    }

    public void setWeekAlpha(double weekAlpha) {
        this.weekAlpha = weekAlpha;
    }

    public double getWeekBeta() {
        return weekBeta;
    }

    public void setWeekBeta(double weekBeta) {
        this.weekBeta = weekBeta;
    }

    public double getMonthAlpha() {
        return monthAlpha;
    }

    public void setMonthAlpha(double monthAlpha) {
        this.monthAlpha = monthAlpha;
    }

    public double getMonthBeta() {
        return monthBeta;
    }

    public void setMonthBeta(double monthBeta) {
        this.monthBeta = monthBeta;
    }

    public double getYearAlpha() {
        return yearAlpha;
    }

    public void setYearAlpha(double yearAlpha) {
        this.yearAlpha = yearAlpha;
    }

    public double getYearBeta() {
        return yearBeta;
    }

    public void setYearBeta(double yearBeta) {
        this.yearBeta = yearBeta;
    }

    public Integer getConditionNum() {
        return conditionNum;
    }

    public void setConditionNum(Integer conditionNum) {
        this.conditionNum = conditionNum;
    }
}
