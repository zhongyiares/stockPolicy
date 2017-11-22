/**
 * Created by Administrator on 2017/11/16 0016.
 */

var H5_loading = function ( images  ,firstPage) {

    var id = this.id;
    if(this._images == undefined){
        this._images = ( images || []).length;
        this._loaded = 0;
        window[id] = this;
        for( var s in images) {
            var item = images[s];
            var img = new Image;
            img.src = item;
            img.onload = function () {
                window[id].loader();
            };
        }
        $('#rate').text('0%');
        return this;
    }else {
        this._loaded++ ;
        $("#rate").text( (this._loaded / this._images * 100 ) >> 0  +"%");
        if(this._loaded < this._images){
            return this;
        }
    }

    window[id] = null;

    this.el.fullpage({
        onLeave:function (index,nextIndex,direction) {
            $(this).find('.h5_component').trigger('onLeave');
        },
        afterLoad:function (anchorLink,index) {
            $(this).find('.h5_component').trigger('onLoad');
        }
    });
    this.page[0].find('.h5_component').trigger('onLoad');
    this.el.show();
    if(firstPage){
        $.fn.fullpage.moveTo( firstPage );
    }
}