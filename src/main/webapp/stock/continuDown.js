/**
 * Created by Administrator on 2016/12/22.
 */

$(function () {
    // updateCorrelationDatas();

});


// $(".index-n-table ul li").click(function(){
//     $(".index-n-table ul li").removeClass("n-t-checked");
//     $(this).addClass("n-t-checked");
//     var tdn=$(this).attr("data-nt-li");
//     $(".nt").hide();
//     $("."+tdn).show();
//
// });

/**
 * 初始化数据
 */
var boardObject;

var time_range = function (beginTime, endTime, nowTime) {
    var strb = beginTime.split (":");
    if (strb.length != 2) {
        return false;
    }
    var stre = endTime.split (":");
    if (stre.length != 2) {
        return false;
    }
    var strn = nowTime.split (":");
    if (stre.length != 2) {
        return false;
    }
    var b = new Date ();
    var e = new Date ();
    var n = new Date ();

    b.setHours (strb[0]);
    b.setMinutes (strb[1]);
    e.setHours (stre[0]);
    e.setMinutes (stre[1]);
    n.setHours (strn[0]);
    n.setMinutes (strn[1]);

    if (n.getTime () - b.getTime () > 0 && n.getTime () - e.getTime () <= 0) {
        return true;
    } else {
        return false;
    }
}

function getAverage(list, name){
    var length = list.length;
    var currentPriceTotal=0,currentProfitTotal=0,currentPriceUpDownRateTotal=0,turnoverRateTotal=0
        ,circulationMarketValueTotal=0,totalValueTotal=0,priceEarningRatioTotal=0,bigDealTotal=0,conditionNumTotal=0 ,limitUpPrice = 0 , diffVal = 0;
    for(var i=0;i<list.length;i++){
        currentPriceTotal += parseFloat(list[i].currentPrice);
        currentProfitTotal += list[i].currentProfit;
        currentPriceUpDownRateTotal += list[i].currentPriceUpDownRate;
        turnoverRateTotal += list[i].turnoverRate;
        circulationMarketValueTotal += list[i].circulationMarketValue;
        totalValueTotal += list[i].totalValue;
        if(list[i].priceEarningRatio=="-"){
            priceEarningRatioTotal += 0;
        }else {
            priceEarningRatioTotal += parseFloat(list[i].priceEarningRatio);
        }
        bigDealTotal += parseFloat(list[i].bigDeal);
        conditionNumTotal += list[i].conditionNum;
        limitUpPrice += list[i].limitUpPrice;
        diffVal += list[i].diffVal;
    }
    var stockName;
    if(name != undefined || name != null){
        stockName = name;
    }else{
        stockName = "总平均值";
    }
    var node = {
        stockName:stockName,
        stockCode:'',
        currentPrice:parseFloat((currentPriceTotal/length).toFixed(2)),
        currentProfit:currentProfitTotal/length,
        currentPriceUpDownRate:parseFloat((currentPriceUpDownRateTotal/length).toFixed(2)),
        turnoverRate:turnoverRateTotal/length,
        circulationMarketValue:circulationMarketValueTotal/length,
        totalValue:totalValueTotal/length,
        priceEarningRatio:parseFloat((priceEarningRatioTotal/length).toFixed(2)),
        bigDeal:parseFloat((bigDealTotal/length).toFixed(2)),
        conditionNum:parseFloat((conditionNumTotal/length).toFixed(2)),
        limitUpPrice : parseFloat((limitUpPrice / length).toFixed(2)),
        diffVal : parseFloat((diffVal / length).toFixed(2))
    }
    //list.unshift(node);
    return node;
}

function getArrayAverage(list){
    var conditionNumArray = {};
    list.map(function (item) {
        if(item.conditionNum in conditionNumArray){
            conditionNumArray[item.conditionNum+''].push(item);
        }else{
            conditionNumArray[item.conditionNum+''] = new Array(item);
        }
    });
    for(var conditionNum in conditionNumArray){
        list.unshift(getAverage(conditionNumArray[conditionNum],"平均值（"+conditionNum+"）"));
    }
}

function dealAverage(list){
    var old = list;
    getArrayAverage(list);
    list.unshift(getAverage(old))
}

/**
 * 排序
 * @param name
 * @param minor
 * @param order
 * @returns {Function}
 */
var by = function(name,minor,order){
    return function (o,p){
        var a,b;
        if(typeof o === "object" && typeof p === "object" && o && p) {
            a = o[name];
            b = p[name];
            if(a===b){
                return typeof minor === 'function' ? minor(o,p,order):0;
            }
            if(typeof a === typeof b){
                if(order=='ASC') {
                    return a > b ? -1 : 1;
                }else{
                    return a < b ? -1 : 1;
                }
            }else {
                if(order=='ASC') {
                    return typeof a > typeof b ? -1 : 1;
                }else{
                    return typeof a < typeof b ? -1 : 1;
                }
            }
        }else{
            throw ("error");
        }
    }
};

/**
 * 开始遮罩层
 */
function showLoading(){
    $("body").append('<div class="loadingDiv2"><div class="item-loader-container"><div class="la-ball-clip-rotate-pulse la-2x"><div></div><div></div></div></div></div>')
    $(".loadingDiv2").fadeIn(100)
};

/**
 * 结束遮罩层
 */
function hideLoading(){
    $(".loadingDiv2").fadeOut(100)
    setTimeout(function(){
        $(".loadingDiv2").remove()
    },100)
};


/**
 *刷新趋同性
 */
$('#queryBtn').click(function () {
    showLoading();
    var tradeDate = $('#tradeDate').val();
    var nums = $('#nums').val();
    var param = {"tradeDate":tradeDate,"nums":nums};
    $.ajax({
        type: 'POST',
        dataType: "json",
        data: param,
        url: "/policy/continuDownPolicy",//请求的action路径
        error: function (data) {//请求失败处理函数
             alert("请求失败11");
        },
        success: function (data) {
            if(data.state == 0){
                var stockInfoVOList = data.stockInfoVOList;
                //stockInfoVOList.sort(by("currentPriceUpDownRate",null,"ASC"));
                dealAverage(stockInfoVOList);
                showTable(stockInfoVOList);
                hideLoading();
            }else{
                alert("SgrcrAB update failure");
            }
        }
    });
});


