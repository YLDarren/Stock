$(document).ready(function(){
    var username = $.cookie("username");
    var level = $.cookie("level");

    //绑定退出事件
    $("#back").on("click" , function(){
        $.ajax({
            url:"",
            type:"POST",
            dataType:"json",
            data:{},//不需要传参
            success: function(data){
                if(data.success){
                    window.location.href = "";
                }
            },
            error: function(error){
                console.log(error);
            }
        });
    });

    //绑定点击弹出功能列表事件
    $(".score").on("click" , function(){

        var parentLiArray = $(this).parent().siblings("li");
        for(var i = 0 ; i < parentLiArray.length ; i++){
            $(parentLiArray[i]).removeClass("active");
        }
        $(this).parent().addClass("active");
        var $ul = $(this).siblings("ul");
        $ul.slideToggle("slow");


    });

    //绑定功能点击事件,弹出详情操作界面
    $(".manager-ul-li").on("click" , function(){
        var option = $(this).text();

        $.showContent(option);
    });

    //绑定具体操作事件，弹出模态框
    $("body").on("click" , ".confirm" , function(){
        var $select = $(this).parent("td").siblings(".select").children("select");
        var studentNumber = $(this).parent("td").siblings(":first").text();
        var lessonNumber = $(this).parent("td").siblings(".lessonNumber").text();
        var method = $select.val();

        //加载和渲染数据
        $.methodData( method , studentNumber ,lessonNumber);

    });

    //为弹出的模态框点击确定或返回事件
    $(".operationButton").on("click" , function(){
        var text = $(this).text();
        $.operation(text);
    });

    //绑定search事件
    $(".searchBtn").on("click" , function(){
        var search =  this.getAttribute("id");
        $.search( search );
    });

    //绑定成绩录入 - 添加事件
    $("#addScore").on("click" , function(){
        var studentNumber = $("#studentNumber").val();//学生学号
        var studentClass = $("#studentClass").val();//班级
        var studentName = $("#studentName").val();//姓名
        var studentLesson = $("#studentLesson").val();//课程名
        var studentLessonNumber = $("#studentLessonNumber").val();//课程编号
        var studentCredit = $("#studentCredit").val();//学分
        var studentScore = $("#studentScore").val();//成绩

        //向后台提交数据
        $.ajax({
            url: "",
            type: "post",
            dataType: "json",
            data:{
                "studentNumber": studentNumber,
                "studentClass": studentClass,
                "studentName": studentName,
                "studentLesson": studentLesson,
                "studentLessonNumber": studentLessonNumber,
                "studentCredit": studentCredit,
                "studentScore": studentScore
            },
            success: function(data){
                console.log(data);
                //成功提交，清空表格
                $("#studentNumber").val("");
                $("#studentClass").val("");
                $("#studentName").val("");
                $("#studentLesson").val("");
                $("#studentLessonNumber").val("");
                $("#studentCredit").val("");
                $("#studentScore").val("");
            },
            error: function(error){
                console.log("error");
                console.log(error);
            }
        })


    });

});
