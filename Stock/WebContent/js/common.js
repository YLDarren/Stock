$.extend({
	// 详情操作页面字段对应
	pageData : function(option) {
		switch (option) {
		case "零件维护":
			option = "manager";
			break;
		case "订货":
			option = "buyer";
			break;
		case "取货":
			option = "employ";
			break;
		default:
			option = "";
		}
		
		return {
			"option" : option,
			"manager" : ".manager-content",
			"buyer" : ".buyer-content",
			"employ" : ".employ-content"
		}
		
	},
	// 具体操作模态框对应
	methodBox : function(people) {
		return {
			"option" : people,
			"page" : ".operation",
			"editor" : ".operation-manager",
			"buy" : ".operation-buyer",
			"extract" : ".operation-employ"
		}
	},
	// 详情页具体操作对应
	methodData : function(method , $firstTd) {
		if (method == null || method == "" || $firstTd == null || $firstTd == "") {
			return;
		}
		method = $.trim( method );
		$firstTd = $.trim( $firstTd );
		
		if(method === "stop"){
            return $.stop( $firstTd );
        }
		
		$.getDataByNumber($firstTd , method);
	},
	// search事件对应
	search : function(search) {
		if (search == null || search == "") {
			return;
		}
		search = $.trim(search);
		switch (search) {
			//管理员search
	        case "managerSearch":
	            return $.searchData("Manager");
	        //采购员search
	        case "buyerSearch":
	            return $.searchData("Buyer");
	        //车间员search
	        case "employSearch":
	            return $.searchData("Employ");
		}
	},
	// 弹出详情操作界面
	showContent : function(option , level) {
		if (option == null || option == "") {
			return;
		}
		option = $.trim(option);
		var obj = $.pageData(option);
		option = obj.option;
		var target = obj[option];
		var levelArray = ["manager" , "buyer" , "employ"];
	    
		// 把所有非点击的详情页面隐藏，
		for ( var key in obj) {
			var value = obj[key];
			if (key === "option" || key === levelArray[level - 1]) {
				continue;
			} else if (value === target && key === levelArray[level - 1]) {
				// 显示
				$(value).fadeIn("slow");
			} else {
				// 隐藏
				$(value).fadeOut("fast");
			}
		}

	},
	//根据编号，获取一行数据
    getDataByNumber: function( $firstTd , type ){
    	$.ajax({
            url: "/Stock/number",
            type: "post",
            dataType: "json",
            data: {
                "number": $firstTd
            },
            success: function(data){
            	if(data.success === true){
                    data = data.data;
                    switch(type){
                        case "editor":
                            return $.editor( data );
                        case "buy":
                            return $.buy( data );
                        case "extract":
                            return $.extract( data );
                    }
                }else{
                    alert(data.reason);
                }
            },
            error: function(error){
                console.log("error");
                console.log(error)
            }
        })
    },
	// 管理员-编辑
	editor : function( data ) {
		
		//渲染数据
        var $body = $("#managerOperationEditor");
        var html = '<tr>' +
            '                        <td class="disabled" id="managerOperatePartsNumber">'+data.partsNumber+'</td>' +
            '                        <td class="disabled">'+data.partsMaterial+'</td>' +
            '                        <td class="disabled">'+data.partsRemain+'</td>' +
            '                        <td>' +
            '                            <input type="text" name="limit" id="managerOperationLimit" placeholder="'+data.partsLimit+'" />' +
            '                        </td>' +
            '                        <td>' +
            '                            <input type="text" name="append" id="managerOperationAppend" placeholder="'+data.partsAppend+'" />' +
            '                        </td>' +
            '                        <td>' +
            '                            <input type="text" name="defaultAppend" id="managerOperationDefaultAppend" placeholder="'+data.partsDefaultAppend+'" />' +
            '                        </td>' +
            '                    </tr>';
        $body.html(html);
		
		// 显示页面
		var obj = $.methodBox("editor");
		var option = obj.option;
		var target = obj[option];
		var page = obj.page;

		$(page).fadeIn("fast");
		for ( var key in obj) {
			var value = obj[key];
			if (key === "option" || key === "page") {
				continue;
			} else if (value === target) {
				// 显示
				$(value).fadeIn("slow");
			} else {
				// 隐藏
				$(value).fadeOut("fast");
			}

		}
	},
	// 管理员-停购
	stop : function( $firstTd ) {
		$.ajax({
	           url: "/Stock/stop",
	           type: "POST",
	           dataType: "json",
	           data: {
	               "number": $firstTd
	           },
	           success: function(data){
	               if(data.success === true){
	                   alert(data.data);
	                   window.location.reload();
	               }else{
	                   alert(data.reason);
	               }
	           },
	           error: function(error){
	               console.log("error");
	               console.log(error);
	           }
	        });
	},
	// 采购员-购买
	buy : function( data ) {
		var $body = $("#OperationBuy");
        var html = '<tr>' +
            '                    <td class="disabled" id="buyerOperationBuyNumber">'+data.partsNumber+'</td>' +
            '                    <td class="disabled">'+data.partsMaterial+'</td>' +
            '                    <td class="disabled">'+data.partsRemain+'</td>' +
            '                    <td class="disabled">'+data.partsLimit+'</td>' +
            '                    <td class="disabled">'+data.partsAppend+'</td>' +
            '                    <td>' +
            '                        <input type="text" name="buy" id="buyerOperationBuy" placeholder="-1" />' +
            '                    </td>' +
            '                </tr>';

        $body.html(html);
		// 显示页面
		var obj = $.methodBox("buy");
		var option = obj.option;
		var target = obj[option];
		var page = obj.page;

		$(page).fadeIn("fast");
		for ( var key in obj) {
			var value = obj[key];
			if (key === "option") {
				continue;
			} else if (value === target || key === "page") {
				// 显示
				$(value).fadeIn("slow")
			} else {
				// 隐藏
				$(value).fadeOut("fast");
			}

		}

	},
	// 车间员-提取
	extract : function( data ) {
		var $body = $("#OperationNeed");
        var html = '<tr>' +
            '                    <td class="disabled" id="employOperationBuyNumber" >'+data.partsNumber+'</td>' +
            '                    <td class="disabled">'+data.partsMaterial+'</td>' +
            '                    <td class="disabled">'+data.partsRemain+'</td>' +
            '                    <td class="disabled">'+data.partsUsed+'</td>' +
            '                    <td>' +
            '                        <input type="text" name="extract" id="employOperationExtract" placeholder="-1" />' +
            '                    </td>' +
            '                    <td>' +
            '                        <input type="text" name="need" id="employOperationNeed" placeholder="-1" />' +
            '                    </td>' +
            '                </tr>';

        $body.html(html);
		// 显示页面
		var obj = $.methodBox("extract");
		var option = obj.option;
		var target = obj[option];
		var page = obj.page;

		$(page).fadeIn("fast");
		for ( var key in obj) {
			var value = obj[key];
			if (key === "option" || key === "page") {
				continue;
			} else if (value === target) {
				// 显示
				$(value).fadeIn("slow")
			} else {
				// 隐藏
				$(value).fadeOut("fast");
			}

		}
	},
	// search
	searchData : function(people) {
		var id = $("#" + people + "SearchById").val();
		var name = $("#" + people + "SearchByName").val();
		var remain = $("#" + people + "SearchByRemain").val();
		var limit = $("#" + people + "SearchByLimit").val();
		var append = $("#" + people + "SearchByAppend").val();
		
		//向后台获取数据
        $.ajax({
            url:"/Stock/search",
            type:"post",
            dataType:"json",
            data:{
                "id": id,
                "name": name,
                "remain": remain,
                "limit": limit,
                "append": append
            },
            success: function(data){
                if(data.success === true){
                	data = data.data;
                    switch( people ){
                        case "Manager":
                            return $.managerSearch(data);
                        case "Buyer":
                            return $.buyerSearch(data);
                        case "Employ":
                            return $.employSearch(data);

                    }
                }else{
                	alert(data.reason)
                }
            },
            error: function(error){
                console.log("error");
                console.log(error)
            }
        });

	},
	// 管理员search
	managerSearch : function(data) {
		var html = "";
        for(var i = 0 ; i < data.length ; i++){
            html += '<tr>' +
                '                            <td>'+data[i].partsNumber+'</td>' +
                '                            <td>'+data[i].partsMaterial+'</td>' +
                '                            <td>'+data[i].partsTotal+'</td>' +
                '                            <td>'+data[i].partsRemain+'</td>' +
                '                            <td>'+data[i].partsUsed+'</td>' +
                '                            <td>'+data[i].partsLimit+'</td>' +
                '                            <td>'+data[i].partsAppend+'</td>' +
                '                            <td>'+data[i].partsDefaultAppend+'</td>' +
                '                            <td>'+data[i].partsActive+'</td>' +
                '                            <td class="select">' +
                '                                <select>' +
                '                                    <option value="editor">编辑</option>' +
                '                                    <option value="stop">停购</option>' +
                '                                </select>' +
                '                            </td>' +
                '                            <td>' +
                '                                <span class="confirm">确定</span>' +
                '                            </td>' +
                '                        </tr>';
        }
        $(".ManagerSearch").html(html);
	},
	// 采购员search
	buyerSearch : function(data) {
	
		var html = "";
        for(var i = 0 ; i < data.length ; i++){
            html += '<tr>' +
                '                            <td>'+data[i].partsNumber+'</td>' +
                '                            <td>'+data[i].partsMaterial+'</td>' +
                '                            <td>'+data[i].partsTotal+'</td>' +
                '                            <td>'+data[i].partsRemain+'</td>' +
                '                            <td>'+data[i].partsUsed+'</td>' +
                '                            <td>'+data[i].partsLimit+'</td>' +
                '                            <td>'+data[i].partsAppend+'</td>' +
                '                            <td>'+data[i].partsDefaultAppend+'</td>' +
                '                            <td>'+data[i].partsActive+'</td>' +
                '                            <td class="select">' +
                '                                <select>' +
                '                                    <option value="buy">采购</option>' +
                '                                </select>' +
                '                            </td>' +
                '                            <td>' +
                '                                <span class="confirm">确定</span>' +
                '                            </td>' +
                '                        </tr>';
        }
        $(".BuyerSearch").html(html);
	},
	// 车间员search
	employSearch : function(data) {
		var html = "";
        for(var i = 0 ; i < data.length ; i++){
            html += '<tr>' +
                '                            <td>'+data[i].partsNumber+'</td>' +
                '                            <td>'+data[i].partsMaterial+'</td>' +
                '                            <td>'+data[i].partsTotal+'</td>' +
                '                            <td>'+data[i].partsRemain+'</td>' +
                '                            <td>'+data[i].partsUsed+'</td>' +
                '                            <td>'+data[i].partsLimit+'</td>' +
                '                            <td>'+data[i].partsAppend+'</td>' +
                '                            <td>'+data[i].partsDefaultAppend+'</td>' +
                '                            <td>'+data[i].partsActive+'</td>' +
                '                            <td class="select">' +
                '                                <select>' +
                '                                   <option value="extract">提取</option>' +
                '                                </select>' +
                '                            </td>' +
                '                            <td>' +
                '                                <span class="confirm">确定</span>' +
                '                            </td>' +
                '                        </tr>';
        }
        $(".EmploySearch").html(html);
	},
	// operation弹出框的操作事件
	operation : function(parentId, text) {
		if (parentId == null || parentId == "") {
			return;
		}
		if (text == null || text == "") {
			return;
		}
		parentId = $.trim(parentId);
		text = $.trim(text);
		switch (parentId) {
		case "ManagerOperation":
			if (text === "返回") {
				return $.operationBack("editor");
			} else {
				return $.operationConfirm("editor");
			}
			break;
		case "BuyerOperation":
			if (text === "返回") {
				return $.operationBack("buy");
			} else {
				return $.operationConfirm("buy");
			}
			break;
		case "EmployOperation":
			if (text === "返回") {
				return $.operationBack("extract");
			} else {
				return $.operationConfirm("extract");
			}
			break;
		}

	},
	// 操作返回事件
	operationBack : function(people) {
		// 隐藏模态框
		var obj = $.methodBox("extract");
		var page = obj.page;

		$(page).fadeOut("fast");
		for ( var key in obj) {
			var value = obj[key];
			// 隐藏
			$(value).fadeOut("fast");
		}
	},
	// 操作确认事件
	operationConfirm : function(people) {
		switch( people ){
	        case "editor":
	            return $.managerEditor();
	        case "buy":
	            return $.buyerBuy();
	        case "extract":
	            return $.employExtract();
		}
	},
	//管理员编辑确认事件
    managerEditor: function(){
    	//获得数据，提交
        var number = $.trim($("#managerOperatePartsNumber").text());
        var limit = $.trim($("#managerOperationLimit").val());
        var append = $.trim($("#managerOperationAppend").val());
        var defaultAppend = $.trim($("#managerOperationDefaultAppend").val());

        //提交数据
        $.ajax({
           url: "/Stock/manager",
           type: "POST",
           dataType: "json",
           async: false,
           data: {
               "number": number,
               "limit": limit,
               "append": append,
               "defaultAppend": defaultAppend
           },
           success: function(data){
        	   if(data.success === true){
        		   if(append !== ""){
        			   DwrPush.send("管理员又追加编号为\""+number + "\"" + append +"件啦，赶紧更新吧！");
        		   }
                   alert(data.data);
               }else{
                   alert(data.reason);
               }
           },
           error: function(error){
                console.log("error");
                console.log(error);
           }
        });
        //关闭弹出框,
        var obj = $.methodBox("editor");
        var page = obj.page;
        $(page).fadeOut("fast");
        for(var key in obj){
            var value = obj[key];
            //隐藏
            $(value).fadeOut("fast");
        }

        //刷新
        window.location.reload();
    },

    //采购员购买确认事件
    buyerBuy: function(){
    	
    	//获得数据，提交
        var number = $.trim($("#buyerOperationBuyNumber").text());
        var buy = $.trim($("#buyerOperationBuy").val());

        //提交数据
        $.ajax({
            url: "/Stock/buyer",
            type: "POST",
            dataType: "json",
            async: false,
            data: {
                "number": number,
                "buy": buy
            },
            success: function(data){
                if(data.success === true){
                    alert(data.data);
                }else{
                    alert(data.reason);
                }
            },
            error: function(error){
                console.log("error");
                console.log(error);
            }
        });

        //关闭弹出框,
        var obj = $.methodBox("buy");
        var page = obj.page;
        $(page).fadeOut("fast");
        for(var key in obj){
            var value = obj[key];
            //隐藏
            $(value).fadeOut("fast");
        }

        //刷新
        window.location.reload();
    },
    //车间员工提取还需确认事件
    employExtract: function(){
    	//获得数据，提交
        var number = $.trim($("#employOperationBuyNumber").text());
        var extract = $.trim($("#employOperationExtract").val());
        var need = $.trim($("#employOperationNeed").val());

        //提交数据
        $.ajax({
            url: "/Stock/employ",
            type: "POST",
            dataType: "json",
            async: false,
            data: {
                "number": number,
                "extract": extract,
                "need": need
            },
            success: function(data){
                if(data.success === true){
                    alert(data.data);
                }else{
                    alert(data.reason);
                }
            },
            error: function(error){
                console.log("error");
                console.log(error);
            }
        });

        //关闭弹出框,
        var obj = $.methodBox("extract");
        var page = obj.page;
        $(page).fadeOut("fast");
        for(var key in obj){
            var value = obj[key];
            //隐藏
            $(value).fadeOut("fast");
        }

        //刷新
        window.location.reload();
    },
	// 根据cookie显示不同的内容
	initContentByCookie : function(username, level) {
		if(username == null || username == "" || level == null || level == ""){
            window.location.href="/Stock/page/login.html"
        }
		
		// 内容和level一一对应;主要用来显示不同的内容
		var content = {
			"1" : ".manager-content",
			"2" : ".buyer-content",
			"3" : ".employ-content"
		};
		// List和level一一对应;主要用来把该用户高亮显示
		var list = {
			"1" : "#managerListLi",
			"2" : "#buyerListLi",
			"3" : "#employListLi"
		};

		$("#user").text(username);// 显示用户名

		// 把该用户高亮
		for ( var key in list) {
			var value = list[key];
			if (key === level) {
				$(value).addClass("active")
			} else {
				$(value).removeClass("active");
			}
		}

		var $selector = content[level];
		// 初始化详情页面
		for (key in content) {
			value = content[key];
			if (value === $selector) {
				//加载数据
                $.ajax({
                   url:"/Stock/init",
                   type:"post",
                   dataType:"json",
                   data:{},
                   success:function(data){
                       if(data.success === true){
                           data = data.data;
                           var html = "";
                           for(var i = 0 ; i < data.length ; i++){
                                html += '<tr>' +
                                    '                            <td>'+data[i].partsNumber+'</td>' +
                                    '                            <td>'+data[i].partsMaterial+'</td>' +
                                    '                            <td>'+data[i].partsTotal+'</td>' +
                                    '                            <td>'+data[i].partsRemain+'</td>' +
                                    '                            <td>'+data[i].partsUsed+'</td>' +
                                    '                            <td>'+data[i].partsLimit+'</td>' +
                                    '                            <td>'+data[i].partsAppend+'</td>' +
                                    '                            <td>'+data[i].partsDefaultAppend+'</td>'+
                                    '                            <td>'+data[i].partsActive+'</td>';
                                switch(level){
                                    case "1":
                                        html += '<td class="select">' +
                                            '                                <select>' +
                                            '                                    <option value="editor">编辑</option>' +
                                            '                                    <option value="stop">停购</option>' +
                                            '                                </select>' +
                                            '                            </td>' +
                                            '                            <td>' +
                                            '                                <span class="confirm">确定</span>' +
                                            '                            </td>' +
                                            '                        </tr>';
                                        break;
                                    case "2":
                                        html += '<td class="select">' +
                                            '                                <select>' +
                                            '                                    <option value="buy">采购</option>' +
                                            '                                </select>' +
                                            '                            </td>' +
                                            '                            <td >' +
                                            '                                <span class="confirm">确定</span>' +
                                            '                            </td>' +
                                            '                        </tr>';
                                        break;
                                    case "3":
                                        html += '<td class="select">' +
                                            '                                <select>' +
                                            '                                    <option value="extract">提取</option>' +
                                            '                                </select>' +
                                            '                            </td>' +
                                            '                            <td >' +
                                            '                                <span class="confirm">确定</span>' +
                                            '                            </td>' +
                                            '                        </tr>';
                                        break;
                                }
                           }
                           $(".init").html(html);
                       }else{
                    	   alert(data.reason)

                       }
                   },
                   error:function(error){
                       console.log("error: ");
                       console.log(error);
                   }
                });
				
				// 显示
				$(value).fadeIn("slow");
			} else {
				// 隐藏
				$(value).fadeOut("fast");
			}
		}
	},
	//显示添加零件操作界面
    addStockParts: function(){
    	//显示操作界面
        var obj = $.methodBox("extract");
        var page = obj.page;
        $(page).fadeIn("fast");
        //显示
        $(".add-manager").fadeIn("slow")
        
    },
    //添加零件--返回事件
    addBack: function(){
        //隐藏操作界面
        var obj = $.methodBox("extract");
        var page = obj.page;
        $(".add-manager").fadeOut("fast");
        $(page).fadeOut("fast");
    },
    //addParts添加零件
    addParts: function(){

        var number = $.trim($("#managerAddNumber").val());
        var material = $.trim($("#managerAddMaterial").val());
        var total = $.trim($("#managerAddTotal").val());
        var limit = $.trim($("#managerAddLimit").val());
        var defaultAppend = $.trim($("#managerAddDefaultAppend").val());

        //提交数据
        $.ajax({
            url: "/Stock/add",
            type: "POST",
            dataType: "json",
            async: false,
            data: {
                "number": number,
                "material": material,
                "total": total,
                "limit": limit,
                "defaultAppend": defaultAppend
            },
            success: function(data){
                if(data.success === true){
                    alert(data.data);
                    //刷新页面
                    window.location.reload();
                }else{
                    alert(data.reason);
                }
            },
            error: function(error){
                console.log("error");
                console.log(error);
            }
        });
        
    }

});