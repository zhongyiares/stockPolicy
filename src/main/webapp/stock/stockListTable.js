/**
 * Created by Administrator on 2017/02/15.
 */

function showTable(list){
    var str = "<tr><th style=\"width: 10%\">股票名称</th><th style=\"width: 10%\">股票代码</th><th style=\"width: 8%\">现价</th><th style=\"width: 8%\">涨停价</th>" +
        "<th style=\"width: 8%\">涨跌幅%</th><th style=\"width: 8%\">换手%</th><th style=\"width: 8%\">流通市值(亿)</th><th style=\"width: 8%\">总市值(亿)</th> " +
        "<th style=\"width: 8%\">市盈率</th><th style=\"width: 8%\">净流入额(万)</th><th style=\"width: 8%\">差值</th><th style=\"width: 8%\">满足条件数目</th></tr>";
    for(var i=0;i<list.length;i++){
        var cpuDownRateClass = "";
        var peradioClass = "";
        var greenClass = "sgrcrIndexColorGreen";
        var redClass = "sgrcrIndexColorRed";
        if(list[i].currentPriceUpDownRate > 0){
            cpuDownRateClass = "sgrcrIndexColorRed";
        }else{
            cpuDownRateClass = "sgrcrIndexColorGreen";
        }
        if(list[i].priceEarningRatio>=300){
            peradioClass = "sgrcrIndexColorYellow";
        }

        if(i<3){
            str += "<td><font style=\"background-color: #c84a31\" >"+parseInt(i+1)+"</font><b>"+list[i].stockName+"</b></td><td><i>"+list[i].stockCode+"</i></td>" +
                "<td class='"+redClass+"'>"+list[i].currentPrice+"</td><td>"+list[i].limitUpPrice+"</td><td class='"+cpuDownRateClass+"' >"+(list[i].currentPriceUpDownRate).toFixed(2)+"</td>";
        }else{
            str += "<td><font>"+parseInt(i+1)+"</font><b>"+list[i].stockName+"</b></td><td><i>"+list[i].stockCode+"</i></td>" +
                "<td class='"+redClass+"'>"+list[i].currentPrice+"</td><td>"+list[i].limitUpPrice+"</td><td class='"+cpuDownRateClass+"' >"+(list[i].currentPriceUpDownRate).toFixed(2)+"</td>";
        }

        if(list[i].turnoverRate>=10){
            str += "<td class='"+redClass+"'>"+(list[i].turnoverRate).toFixed(2)+"</td>";
        }else{
            str += "<td class='"+greenClass+"'>"+(list[i].turnoverRate).toFixed(2)+"</td>";
        }
        /*if(list[i].bigDeal>=0){
         str += "<td class='"+redClass+"'>"+list[i].bigDeal+"</td>";
         }else {
         str += "<td class='"+greenClass+"'>"+list[i].bigDeal+"</td>";
         }*/
        var circula = (list[i].circulationMarketValue/100000000).toFixed(2);
        if(list[i].circulationMarketValue>=10000000000){
            str += "<td class='"+greenClass+"'>"+circula+"</td>";
        }else{
            str += "<td class='"+redClass+"'>"+circula+"</td>";
        }
        //总市值
        if(list[i].totalValue > 100000000) {
            var totalValueShow = (list[i].totalValue / 100000000).toFixed(0);
            str += "<td>" + totalValueShow + "</td>";
        }else{
            var totalValueShow = (list[i].totalValue / 100000000).toFixed(2);
            str += "<td>" + totalValueShow + "</td>";
        }
        if(list[i].priceEarningRatio>=300){
            str += "<td class='"+peradioClass+"'>"+list[i].priceEarningRatio+"</td>";
        }else{
            str += "<td>"+list[i].priceEarningRatio+"</td>";
        }
        if(list[i].bigDeal>=0){
            str += "<td class='"+redClass+"'>"+list[i].bigDeal+"</td>";
        }else {
            str += "<td class='"+greenClass+"'>"+list[i].bigDeal+"</td>";
        }
        //差值
        str += "<td>"+list[i].diffVal+"</td>";

        //满足条件数
        str += "<td>"+list[i].conditionNum+"</td>";

        str += "</tr>";
    }
    $('.nt1Table').html(str);
}

