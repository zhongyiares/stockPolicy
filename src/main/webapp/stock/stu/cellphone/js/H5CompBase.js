/**
 * Created by Administrator on 2017/11/6 0006.
 */

var H5ComponentBase = function ( name,cfg) {
    var cfg = cfg || {};

    var id = ("h5_c_"+Math.random()).replace(".","_");

    var cls = 'h5_component_'+cfg.type +' h5_component_name_'+name;

    var component = $('<div class="h5_component '+cls+'" id="'+id+'"></div>');

    cfg.text && component.text(cfg.text);
    cfg.width && component.width(cfg.width / 2);
    cfg.height && component.height(cfg.height / 2)

    cfg.css && component.css(cfg.css);
    cfg.bg && component.css("backgroundImage",'url('+cfg.bg+')');

    if( cfg.center ===  true){
        component.css({
            marginLeft : ( cfg.width / 4 * -1 ) + 'px',
            left : '50%'
        })
    }

    return component;
}