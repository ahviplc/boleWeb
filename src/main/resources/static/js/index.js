SmallJobType = new Array();
SmallJobType[0] = new Array("技术", "不限|技术综合|服务端开发|前端及客户端开发|技术安全|人工智能|商业智能|技术运维|测试|硬件|基础运维|项目管理|技术支持");
SmallJobType[1] = new Array("产品", "不限|产品综合|用户研究|产品策划|产品运营");
SmallJobType[2] = new Array("内容", "不限|内容综合|视频|翻译");
SmallJobType[3] = new Array("市场", "不限|市场综合|品牌|公关|商务");
SmallJobType[4] = new Array("游戏", "不限|游戏综合|游戏程序|游戏测试|游戏策划|游戏音频|游戏美术|游戏运营|游戏运维|游戏用研");
SmallJobType[5] = new Array("电商", "不限|电商综合|电商商务|电商产品|电商运营|物流");
SmallJobType[6] = new Array("设计", "不限|设计综合|视觉设计|交互设计|工业设计");
SmallJobType[7] = new Array("金融", "不限|金融综合|风险管理|金融产品|金融运营|金融市场");
SmallJobType[8] = new Array("销售", "不限|销售|销售策划|销售支持|广告投放");
SmallJobType[9] = new Array("客服", "不限|客服综合|客服支持|客服质检|客户回访|审核");
SmallJobType[10] = new Array("职能", "公司事务|人力资源|行政|IT|财务|法务|采购|战略规划与研究");
SmallJobType[11] = new Array("高管", "高管综合");
SmallJobType[12] = new Array("其他", "农业|陶瓷|教育|生产|质量");

function getSmallJobType(bigType) {
    var bigType = bigType;
    var i, j;
    document.all.smallJob.length = 0;
    for (i = 0; i < SmallJobType.length; i++) {
        if (SmallJobType[i][0] == bigType) {
            var tempArray = SmallJobType[i][1].split("|");
            for (j = 0; j < tempArray.length; j++) {
                document.all.smallJob.options[document.all.smallJob.length] = new Option(tempArray[j], tempArray[j]);
            }
        }
    }
};

function doSerch() {
    var workplace = $("#workplace option:selected").val();
    var jobbigtype = $("#bigJobType option:selected").val();
    var joblittletype = $("#smallJob option:selected").val();
    var worktype = $("#worktype option:selected").val();
    if (jobbigtype == "") {
        alert("请选择工作类型！")
        return;
    }
    if (worktype == "") {
        alert("请选择工作性质！")
        return;
    }
    document.getElementById("result").innerHTML="";
    ajaxSerch(workplace, jobbigtype, joblittletype, worktype);
}

function ajaxSerch(place, bigtype, littletype, type) {
    $.ajax({
            url: "/job/search",
            type: 'post',
            data: JSON.stringify({
                workplace: place,
                joblittletype: littletype,
                worktype: type,
                jobbigtype: bigtype
            }),
            contentType: "application/json;charset=utf-8",
            origin: '',
            crossDomain: true,
            success: function (data) {
                var result = JSON.stringify(data);
                // alert(result);
                // document.getElementById("result").innerText = result;
                $.each(data, function (n, value) {

                    var jobtitle = value.jobtitle;
                    var jobdate = value.jobdate;
                    var hireCount = value.hireCount;
                    var url = "http://bole.netease.com/position/h5/detail.do?rcode=88W5t2D4nY&id=" + value.jobid;
                    var html;
                    html ="<tr class=\"event-bgColor\">"
                        + "<td><a target='_blank' href="+url+">"+jobtitle+"</td>"
                        + "<td>"+jobdate+"</td>"
                        + "<td>"+hireCount+"</td>"
                        + "<td><a target='_blank' href="+url+">去内推</td>"
                        + "</tr>\n";
                    // alert(html);
                    document.getElementById("result").innerHTML+=html;
                })
            }
        }
    )

};

window.onload=function () {
    $.ajax(
        {
            url:"/job/newestUpdateTime",
            // url:"http:127.0.0.1:8080/job/newestUpdateTime",
            type:"get",
            success: function (data) {
                var time=data;
                document.getElementById("update-time").innerText+=time;
            }
        }
    )
};
/*

    var b = document.getElementById("outputCtt").innerText;
    alert(b);
    $("#send").attr("href", "mailto:baorenai@u51@163.com?body=" +encodeURI(b));
    document.getElementById("send").click();
    $(document).load(
        $(".btn").click(sendEmail))
}
;*/
