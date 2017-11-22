/**
 * Created by Administrator on 2017/11/16 0016.
 */
var res = ['Base','Pie','Point','Polyline','Radar','Bar'];
var html = [];
for( s in res){
    html.push('<script type="" src="../js/H5Comp'+res[s]+'.js"><\/script>');
    html.push('<link rel="stylesheet" href="../css/H5Comp'+res[s]+'.css">');
}
document.write( html.join(''));

$(function () {
    var h5 = new H5();
    h5.whenAddPage = function () {
        this.addComponent('slide_up',{
            bg:'../images/footer.png',
            css:{
                opacity:0,
                left:0,bottom:-20,
                width:'100%',height:'20px',zIndex:999
            },
            animateIn:{
                opacity:1,bottom:'-1px'
            },
            animateOut:{
                opacity:0,bottom:'-20px'
            },
            delay:500
        });
    }
    h5.addPage('face')
        .addComponent('topic',{
//                    text :'logo',
            width : 395,
            height : 130,
            bg:'../images/face_logo.png',
            css :{
                opacity: 0
            },
            animateIn :{
                top :100,
                opacity: 1
            },
            animateOut :{
                top :0,
                opacity: 0
            },
            center : true
        })
        .addComponent('slogan',{
            center : true,
            width : 365,
            height : 99,
            bg:'../images/face_slogan.png',
            css :{
                top :180,
                opacity: 0
            },
            animateIn :{
                left :'50%',
                opacity: 1
            },
            animateOut :{
                left :'0',
                opacity: 0
            },
            delay :500
        })
        .addComponent('face_img_left',{
            width : 370,
            height : 493,
            bg:'../images/face_img_left.png',
            css :{
                left : -50,
                bottom : - 50,
                opacity: 0
            },
            animateIn :{
                left : 0,
                bottom : 0,
                opacity: 1
            },
            animateOut :{
                left : -50,
                bottom : - 50,
                opacity: 0
            },
            delay :500
        })
        .addComponent('face_img_left',{
            width : 276,
            height : 449,
            bg:'../images/face_img_right.png',
            css :{
                right : -50,
                bottom : - 50,
                opacity: 0
            },
            animateIn :{
                right : 0,
                bottom : 0,
                opacity: 1
            },
            animateOut :{
                right : -50,
                bottom : - 50,
                opacity: 0
            },
            delay :500
        })
    h5.addPage()
        .addComponent('caption',{
            text :'核心理念',
        })
        .addComponent('text',{
            text :'IT教育网=只学有用的',
            width:500,
            height:30,
            center:true,
            css :{
                opacity: 0,
                textAlign :'center',
                color:'red',
                fontSize:'26px'
            },
            animateIn :{
                opacity: 1,
                top:160
            },
            animateOut :{
                top:240,
                opacity: 0
            },

        })
        .addComponent('description',{
            text :'IT教育网=只学有用的',
            width:481,
            height:295,
            center:true,
            bg:'../images/description_bg.gif',
            css:{
                opacity:0,
                padding:'15px 10px 10px 10px',
                color:'#fff',
                fontSize:'15px',
                lineHeight:'18px',
                textAlign:'justify',
                top:240
            },
            text:'2013年，IT教育网的诞生引领中国IT职业从教育进入新时代；高质量实战课程、全新教学模式、实时互动学习，以领先优势打造行业品牌；迄今为止，IT教育网已成为中国规模最大、互动性最高的IT技能学习平台。',
            animateIn:{opacity:1,top:190},
            animateOut:{opacity:0,top:240},
            delay:500,
        })
        .addComponent('people',{
            center:true,
            width:515,
            height:305,
            bg:'../images/p1_people.png',
            css:{
                opacity:0,
                bottom:0
            },
            animateIn:{opacity:1,bottom:40},
            animateOut:{opacity:0,bottom:0},
            delay:500
        })

        //
        .addPage()
        .addComponent('caption',{text:'课程分布方向'})//polyline
        .addComponent('polyline',{
            type:'polyline',
            data:[['前端开发',.4,'#ff7676'],['移动开发',.2],['后端开发',.3,'blue'],['图像处理',.1]],
            width:530,
            height:300,
            center:true,
            css:{opacity:0,top:200},
            animateIn:{opacity:1,top:250},
            animateOut:{opacity:0,top:100},
        })
        .addComponent('msg',{
            text:'前端开发课程占到40%',
            css:{
                opacity:0,top:160,
                textAlign:'center',width:'100%',color:'#ff7676'
            },
            animateIn:{ opacity:1},
            animateOut:{ opacity:0}
        })
        .addPage()
        .addComponent('caption',{text:'移动开发课程资源'})//pie
        .addComponent('pie',{
            type:'pie',
            data:[
                ['Android' , .4  ,'#ff7676'],
                ['IOS' , .3 ],
                ['Cocos2d-x' , .2  ],
                ['Unity-3D' , .1  ],
            ],
            css:{top:200},
            width:300,
            height:300,
            center:true
        })
        .addComponent('msg',{
            text:'移动课程 Android 占到40%',
            css:{
                opacity:0,bottom:120,
                textAlign:'center',width:'100%',color:'#ff7676'
            },
            animateIn:{ opacity:1},
            animateOut:{ opacity:0}
        })
        .addPage() //bar（bar_v）
        .addComponent('caption',{text:'前端开发课程'})
        .addComponent('bar',
            {
                type : 'bar',

                width : 530,
                height : 600,
                data:[
                    ['Javascript' , .4  ,'#ff7676'],
                    ['HTML/CSS' , .2  ],
                    ['CSS3' , .1 ],
                    ['HTML5' , .2  ],
                    ['jQuery' , .35 ],
                    ['Bootstrap' , .05 ],
                    ['AngularJs' , .09  ]
                ],
                css : {
                    top:100,
                    opacity:0
                },
                animateIn:{
                    opacity:1,
                    top:200,
                },
                animateOut:{
                    opacity:0,
                    top:100,
                },
                center : true,
            }
        )
        .addComponent('msg',{
            text:'前端课程 Javascript 占到40%',
            css:{
                opacity:0,bottom:120,
                textAlign:'center',width:'100%',color:'#ff7676'
            },
            animateIn:{ opacity:1},
            animateOut:{ opacity:0}
        })
        .addPage()
        .addComponent('caption',{text:'后端开发课程'})//radar

        .addComponent('radar',{
            type : 'radar',
            width : 400,
            height : 400,
            data:[
                ['Js' , .9  ,'#ff7676'],
                ['CSS3' , .8 ],
                ['HTML5' , .7  ],
                ['PHP' , .6  ],
                ['jQuery' , .5 ]
            ],
            css : {
                top:100,
                opacity:0
            },
            animateIn:{
                opacity:1,
                top:200,
            },
            animateOut:{
                opacity:0,
                top:100,
            },
            center : true,
        })
        .addPage()
        .addComponent('caption',{text:'课程难度分布'})//point
        .addComponent('point',{
            type : 'point',
            width : 300,
            height : 300,
            data:[
                ['中级' , .4  ,'#ff7676'],
                ['初级' , .2  ,'#ffa3a4', 0 ,'-60%'],
                ['高级' , .3  ,'#99c1ff', '50%' ,'-120%']
            ],
            css : {
                bottom:'20%'
            },
            center : true,
        })

        //


        .addPage('tail')
        .addComponent('logo',{
            center:true,
            width:359,
            height:129,
            bg:'../images/tail_logo.png',
            css:{top:240,opacity:0},
            animateIn:{opacity:1,top:210},
            animateOut:{opacity:0,top:240},
        })
        .addComponent('slogan',{
            center:true,
            width:314,
            height:46,
            bg:'../images/tail_slogan.png',
            css:{top:280,opacity:0},
            animateIn:{opacity:1,left:'50%'},
            animateOut:{opacity:0,left:'0%'},
            delay:500
        })
        .addComponent('share',{
            width:128,
            height:120,
            bg:'../images/tail_share.png',
            css:{opacity:0,top:110,right:110},
            animateIn:{opacity:1,top:10,right:10},
            animateOut:{opacity:0,top:110,right:110},
            delay:1000
        })
        .addComponent('back',{
            width:52,
            height:50,
            bg:'../images/tail_back.png',
            center:true,
            onclick : function(){
                $.fn.fullpage.moveTo( 1 )
            }
        })
        .loader( ['../images/tail_slogan.png','../images/tail_share.png','../images/tail_back.png']);
})