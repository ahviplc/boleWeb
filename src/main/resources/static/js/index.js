SmallJobType = new Array();
SmallJobType[0]=new Array("技术","技术综合|服务端开发|前端及客户端开发|技术安全|人工智能|商业智能|技术运维|测试|硬件|基础运维|项目管理|技术支持");
SmallJobType[1]=new Array("产品","产品综合|用户研究|产品策划|产品运营");
SmallJobType[2]=new Array("内容","内容综合|视频|翻译");
SmallJobType[3]=new Array("市场","市场综合|品牌|公关|商务");
SmallJobType[4]=new Array("游戏","游戏综合|游戏程序|游戏测试|游戏策划|游戏音频|游戏美术|游戏运营|游戏运维|游戏用研");
SmallJobType[5]=new Array("电商","电商综合|电商商务|电商产品|电商运营|物流");
SmallJobType[6]=new Array("设计","设计综合|视觉设计|交互设计|工业设计");
SmallJobType[7]=new Array("金融","金融综合|风险管理|金融产品|金融运营|金融市场");
function getSmallJobType(bigType) {
    alert("value change");
    var bigType = bigType;
    var i,j;
    document.all.smallJob.length=0;
    for(i=0;i<SmallJobType.length;i++)
    {
        if(SmallJobType[i][0]==bigType)
        {
            var tempArray = SmallJobType[i][1].split("|");
            for(j=0;j<tempArray.length;j++)
            {
                document.all.smallJob.options[document.all.smallJob.length]=new Option(tempArray[j],tempArray[j]);
            }
        }
    }
}