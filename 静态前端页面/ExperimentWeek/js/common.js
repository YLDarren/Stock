$.extend({
    //详情操作页面字段对应
    pageData: function( option ){
        switch ( option ){
            case "零件维护" : option = "manager";
                break;
            case "订货" : option = "buyer";
                break;
            case "取货" : option = "employ";
                break;
            default : option = "";
        }
        return {
            "option": option,
            "manager": ".manager-content",
            "buyer": ".buyer-content",
            "employ": ".employ-content"
        }
    },
    //具体操作模态框对应
    methodBox: function( people ){
        return{
            "option": people,
            "page": ".operation",
            "editor": ".operation-manager",
            "buy": ".operation-buyer",
            "extract": ".operation-employ"
        }
    },
    //详情页具体操作对应
    methodData: function( method ){
        if(method == null || method == "" ){
            return;
        }
        method = $.trim(method);
        switch ( method ){
            //管理员-编辑
            case "editor":
                return $.editor();
            //管理员-停购
            case "stop":
                return $.stop();
            //采购员-购买
            case "buy":
                return $.buy();
            //车间员-提取
            case "extract":
                return $.extract();
        }
    },
    //search事件对应
    search: function( search ){
        if(search == null || search == "" ){
            return;
        }
        search = $.trim( search );
        switch ( search ){
            //管理员search
            case "managerSearch":
                return $.managerSearch();
            //采购员search
            case "buyerSearch":
                return $.buyerSearch();
            //车间员search
            case "employSearch":
                return $.employSearch();
        }
    },
    //弹出详情操作界面(还没加渲染数据)
    showContent: function( option ){
        if(option == null || option == "" ){
           return;
        }
        option = $.trim(option);
        var obj =  $.pageData( option );
        option = obj.option;
        var target = obj[option];

        // 把所有非点击的详情页面隐藏，
        for(var key in obj){
            var value = obj[key];
            if(key === "option"){
                continue;
            }else if(value === target){
                //显示
                $(value).fadeIn("slow");
            }else{
                //隐藏
                $(value).fadeOut("fast");
            }
        }

    },
    //管理员-编辑
    editor: function(){
        console.log("editor");
        //需要获取数据，渲染数据

        //显示页面
        var obj = $.methodBox("editor");
        var option = obj.option;
        var target = obj[option];
        var page = obj.page;

        $(page).fadeIn("fast");
        for(var key in obj){
            var value = obj[key];
            if(key === "option" || key === "page" ){
                continue;
            }else if(value === target){
                //显示
                $(value).fadeIn("slow");
            }else{
                //隐藏
                $(value).fadeOut("fast");
            }

        }
    },
    //管理员-停购
    stop: function(){
        console.log("stop");
    },
    //采购员-购买
    buy: function(){
        console.log("buy");

        //显示页面
        var obj = $.methodBox("buy");
        var option = obj.option;
        var target = obj[option];
        var page = obj.page;

        $(page).fadeIn("fast");
        for(var key in obj){
            var value = obj[key];
            if(key === "option"){
                continue;
            }else if(value === target || key === "page"){
                //显示
                $(value).fadeIn("slow")
            }else{
                //隐藏
                $(value).fadeOut("fast");
            }

        }

    },
    //车间员-提取
    extract: function(){
        console.log("extract");

        //显示页面
        var obj = $.methodBox("extract");
        var option = obj.option;
        var target = obj[option];
        var page = obj.page;

        $(page).fadeIn("fast");
        for(var key in obj){
            var value = obj[key];
            if(key === "option" || key === "page"){
                continue;
            }else if(value === target){
                //显示
                $(value).fadeIn("slow")
            }else{
                //隐藏
                $(value).fadeOut("fast");
            }

        }
    },
    //search
    searchData: function( people ){
        var id = $("#" + people + "SearchById").val();
        var name = $("#" + people + "SearchByName").val();
        var remain = $("#" + people + "SearchByRemain").val();
        var limit = $("#" + people + "SearchByLimit").val();
        var append = $("#" + people + "SearchByAppend").val();
        console.log(people);
        console.log(id);
        console.log(name);
        console.log(remain);
        console.log(limit);
        console.log(append);

    },
    //管理员search
    managerSearch: function(){
        var data = $.searchData("Manager");
    },
    //采购员search
    buyerSearch: function(){
        var data = $.searchData("Buyer");
    },
    //车间员search
    employSearch: function(){
        var data = $.searchData("Employ");
    },
    //operation弹出框的操作事件
    operation: function(parentId , text){
        if(parentId == null || parentId == ""){
            return;
        }
        if(text == null || text == ""){
            return;
        }
        parentId = $.trim(parentId);
        text = $.trim(text);
        switch( parentId ){
            case "ManagerOperation":
                if(text === "返回"){
                    return $.operationBack("editor");
                }else{
                    return $.operationConfirm("editor");
                }
                break;
            case "BuyerOperation":
                if(text === "返回"){
                    return $.operationBack("buy");
                }else{
                    return $.operationConfirm("buy");
                }
                break;
            case "EmployOperation":
                if(text === "返回"){
                    return $.operationBack("extract");
                }else{
                    return $.operationConfirm("extract");
                }
                break;
        }

    },
    //操作返回事件
    operationBack: function( people ){
        //隐藏模态框
        var obj = $.methodBox("extract");
        var page = obj.page;

        $(page).fadeOut("fast");
        for(var key in obj){
            var value = obj[key];
            //隐藏
            $(value).fadeOut("fast");
        }
    },
    //操作确认事件
    operationConfirm: function( people ){
        console.log(people);
    }

});