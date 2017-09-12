package com.thinkgem.jeesite.modules.stock.service.multiThread;

import com.thinkgem.jeesite.modules.stock.entity.StockInfoVO;
import com.thinkgem.jeesite.modules.stock.service.multiThread.callable.StockDataCallable;
import com.thinkgem.jeesite.modules.stock.service.multiThread.callable.StockDataDealProcessCallable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by zhongyi on 2017/03/16.
 */
@Service
public class MutilDealStockDataListService {

    private static Logger log = LogManager.getLogger(MutilDealStockDataListService.class);

    public List<StockInfoVO>  dealDataList(List<Map> dataList) throws Exception{
        long startTime = System.currentTimeMillis();
        List<StockInfoVO> stockInfoVOList = new ArrayList<StockInfoVO>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 4000, 500, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(4000));
        List<Future<StockInfoVO>> results = new ArrayList<Future<StockInfoVO>>();
        int count = 0;
        for(int i = 0; i < dataList.size(); i++) {
            StockDataDealProcessCallable stockDataDealProcessCallable = new StockDataDealProcessCallable(dataList.get(i));
            Future<StockInfoVO> result = executor.submit(stockDataDealProcessCallable);
            results.add(result);
        }
        //等任务执行完毕
        do{
            //当前已经完成的任务数
            long currentThreadNum = executor.getCompletedTaskCount();
            log.info("Main: Number of Completed Tasks:" + currentThreadNum );
            Thread.sleep(3000);
            if(count >= 5){
                //关闭现场 抛出异常
                log.info("=============shutdown MutilDealStockDataListService Task   :" + currentThreadNum );
                executor.shutdownNow();
                //延迟3秒
                Thread.sleep(3000);
                throw new Exception("********** The task exception shutdown the list size is 0 ******** : " + count );
                //return newBizCorrelList ;
            }
            if(executor.getCompletedTaskCount() == currentThreadNum ){
                count++;
                Thread.sleep(3000);
                log.info("********** The count num is : " + count );
            }

        }while(executor.getCompletedTaskCount() < results.size());


        for(Future<StockInfoVO> fus : results) {
            try {
                //System.out.printf(fus.get().getStockCode());
                stockInfoVOList.add(fus.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        long endTime = System.currentTimeMillis();
        log.info("==============================MutilDealStockDataListService  update finished============================== use :"+(endTime - startTime)/1000 + " second ");
        return stockInfoVOList;
    }

    public List<StockInfoVO>  dealStockCodeList(List<String> stockList) throws Exception{
        long startTime = System.currentTimeMillis();
        List<StockInfoVO> stockInfoVOList = new ArrayList<StockInfoVO>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 4000, 500, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(4000));
        List<Future<StockInfoVO>> results = new ArrayList<Future<StockInfoVO>>();
        int count = 0;
        if(stockList.size() > 50){
            for(int i = 0; i < 50 ; i++) {
                StockDataCallable stockDataCallable = new StockDataCallable(stockList.get(i));
                Future<StockInfoVO> result = executor.submit(stockDataCallable);
                results.add(result);
            }
        }else{
            for(int i = 0; i < stockList.size(); i++) {
                StockDataCallable stockDataCallable = new StockDataCallable(stockList.get(i));
                Future<StockInfoVO> result = executor.submit(stockDataCallable);
                results.add(result);
            }
        }

        //等任务执行完毕
        do{
            //当前已经完成的任务数
            long currentThreadNum = executor.getCompletedTaskCount();
            log.info("Main: Number of Completed Tasks:" + currentThreadNum );
            Thread.sleep(3000);
            if(count >= 5){
                //关闭现场 抛出异常
                log.info("=============shutdown dealStockCodeList Task   :" + currentThreadNum );
                executor.shutdownNow();
                //延迟3秒
                Thread.sleep(3000);
                throw new Exception("********** The task exception shutdown the list size is 0 ******** : " + count );
                //return newBizCorrelList ;
            }
            if(executor.getCompletedTaskCount() == currentThreadNum ){
                count++;
                Thread.sleep(3000);
                log.info("********** The count num is : " + count );
            }

        }while(executor.getCompletedTaskCount() < results.size());


        for(Future<StockInfoVO> fus : results) {
            try {
                //System.out.printf(fus.get().getStockCode());
                stockInfoVOList.add(fus.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        long endTime = System.currentTimeMillis();
        log.info("==============================MutilDealStockDataListService dealStockCodeList  update finished============================== use :"+(endTime - startTime)/1000 + " second ");
        return stockInfoVOList;
    }
}
