$(document).ready(function(){
	
	 var username = $.cookie("username");
	 var level = $.cookie("level");
	 //根据cookie显示不同的内容
	 $.initContentByCookie(username , level);
	
	 //加载追加消息
	 if(level === "2"){
		 //加载追加消息
	        $.ajax({
	            url: "/Stock/initMsg",
	            type: "POST",
	            dataType: "json",
	            data:{},
	            success: function(data){
	            	if(data.success === true){
	                    data = data.data;
	                    var html = "";
	                    var people = "";
	                    for(var i = 0 ; i < data.length ; i++){
	                        people = people === "3" ? "车间员工" : "管理员";
	                        html += '<div class="msg-line">'+people+data[i].userName+'追加'+data[i].partsMaterial+'编号为"'+data[i].partsNumber+'"的零件'+data[i].operateAdd+'件，'+
	                        	'请相同的零件一同操作</div>';
	                    }
	                    $(".buyer-content-msg").html(html).fadeIn("slow");
	                    
	                }
	            },
	            error: function(error){
	                console.log("error");
	                console.log(error);
	            }
	        })
	 }
	    
	 
    //绑定退出事件
    $("#back").on("click" , function(){
    	$.ajax({
            url:"/Stock/exit",
            type:"POST",
            dataType:"json",
            data:{

            },
            success: function(data){
                if(data.success){
                    window.location.href = "/Stock/page/login.html";
                }
            },
            error: function(error){
                console.log(error);
            }
         });
    });
    
    //用户绑定点击弹出功能列表事件
    $(".people").on("click" , function(){
        var $ul = $(this).siblings("ul");
        $ul.slideToggle("slow");
    });

    //绑定功能点击事件,弹出详情操作界面
    $(".manager-ul-li").on("click" , function(){
        var option = $(this).text();
        if(option === "添加零件"){
            $.addStockParts();
        }else{
            $.showContent( option , level );
        }
    });

    //绑定具体操作事件，弹出模态框
    $("body").on("click" , ".confirm" , function(){
        var $select = $(this).parent("td").siblings(".select").children("select");
        var $firstTd = $(this).parent("td").siblings(":first").text();
        var method = $select.val();
        $.methodData( method , $firstTd );
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
    });
    
    //为添加零件绑定返回事件
    $("#addBack").on("click" , function(){
        $.addBack();
    });

    //为添加零件绑定确认事件
    $("#addConfirm").on("click" , function(){
        $.addParts();
    });
    
});