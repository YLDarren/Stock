$(document).ready(function(){
    //绑定退出事件
    $("#back").on("click" , function(){
        console.log("退出登陆");
    });
    //用户绑定点击弹出功能列表事件(未加权限)
    $(".people").on("click" , function(){
        var $ul = $(this).siblings("ul");
        $ul.slideToggle("slow");
    });

    //绑定功能点击事件,弹出详情操作界面(未加权限)
    $(".manager-ul-li").on("click" , function(){
        var option = $(this).text();
        $.showContent( option );
    });

    //绑定具体操作事件，弹出模态框
    $("body").on("click" , ".confirm" , function(){
        var $select = $(this).parent("td").siblings(".select").children("select");
		var $firstTd = $(this).parent("td").siblings(":first");
		console.log($firstTd);
		console.log($firstTd.text());
        var method = $select.val();
        $.methodData( method );
    });

    //为弹出的模态框点击确定或返回事件
    $(".operationButton").on("click" , function(){
        var text = $(this).text();
        var parentId = $(this).parent().attr("id");
        $.operation(parentId , text);
    });

    //绑定search事件
    $(".searchBtn").on("click" , function(){
        var search =  this.getAttribute("id");
        $.search( search );
    })
});