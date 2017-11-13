/**
 * Created by Administrator on 2017/11/6 0006.
 */

var H5ComponentPie= function ( name,cfg) {
    var component = new H5ComponentBase( name ,cfg);

    //
    var w = cfg.width;
    var h = cfg.height;
    var cns = document.createElement('canvas');
    var ctx = cns.getContext('2d');
    cns.width = ctx.width = w;
    cns.height = ctx.height = h;
    $(cns).css('zIndex',1);
    component.append(cns);

    var r = w /2;

    //地图层
    ctx.beginPath();
    ctx.fillStyle = '#eee';
    ctx.strokeStyle = '#eee';
    ctx.lineWidth  = 1;
    ctx.arc(r,r,r,0,2*Math.PI);
    ctx.fill();
    ctx.stroke();

    //数据
    var cns = document.createElement('canvas');
    var ctx = cns.getContext('2d');
    cns.width = ctx.width = w;
    cns.height = ctx.height = h;
    $(cns).css('zIndex',2);
    component.append(cns);

    var colors = ['red','green','blue','orange','gray','purse'];
    var sAngel = 1.5 * Math.PI;
    var eAngel = 0;
    var aAngel = Math.PI * 2;

    var step = cfg.data.length;
    for(var i=0 ;i< step ;i++){
        var item = cfg.data[i];
        var color = item[2] || (item[2] = colors.pop());

        eAngel = sAngel + aAngel * item[1];

        ctx.beginPath();
        ctx.fillStyle = color;
        ctx.strokeStyle = color;
        ctx.lineWidth  = .1;
        ctx.moveTo(r,r)
        ctx.arc(r,r,r,sAngel,eAngel);
        ctx.fill();
        ctx.stroke();

        sAngel = eAngel;
    }

    var cns = document.createElement('canvas');
    var ctx = cns.getContext('2d');
    cns.width = ctx.width = w;
    cns.height = ctx.height = h;
    $(cns).css('zIndex',3);
    component.append(cns);

    //地图层
    ctx.fillStyle = '#eee';
    ctx.strokeStyle = '#eee';
    ctx.lineWidth  = 1;

    var draw = function ( per ) {
        ctx.clearRect(0,0,w,h);
        ctx.beginPath();
        ctx.moveTo(r,r);
        if(per <=0){
            ctx.arc(r,r,r,0,2*Math.PI);
        }else {
            ctx.arc(r,r,r,sAngel,sAngel+2*Math.PI*per,true);
        }

        ctx.fill();
        ctx.stroke();
    };

    // draw(0);


    component.on('onLoad',function () {
        var s=0;
        for(var i=0 ;i< 100 ;i++){
            setTimeout(function () {
                s+=.01;
                draw(s);
            },i*10+500)
        }
    });

    component.on('onLeave',function () {
        var s=1;
        for(var i=0 ;i< 100 ;i++){
            setTimeout(function () {
                s-=.01;
                draw(s);
            },i*10)
        }
    });

    return component;
}