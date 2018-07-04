$.extend({
    // 详情操作页面字段对应
    pageData : function(option) {
        var parent = "";
        switch (option) {
            case "成绩维护":
                option = "resultInput";
                parent = ".manager-content";
                break;
            case "添加":
                option = "add";
                parent = ".manager-content";
                break;
            case "考试成绩":
                option = "examResults";
                parent = ".buyer-content";
                break;
            case "考试排名":
                option = "testRankings";
                parent = ".buyer-content";
                break;
            case "课程":
                option = "curriculum";
                parent = ".employ-content";
                break;
            case "学号":
                option = "number";
                parent = ".employ-content";
                break;
            default:
                option = "";
                parent ="";
        }

        return {
            "option": option,
            "parent": parent,
            "resultInput": ".manager-maintain",
            "add": ".manager-add",
            "examResults": ".buyer-score",
            "testRankings": ".buyer-sort",
            "curriculum": ".employ-lesson",
            "number": ".employ-number"
        }

    },
    //展示右边详情操作界面
    showContent: function(option){
        if (option == null || option == "") {
            return;
        }

        option = $.trim(option);
        var obj = $.pageData(option);
        option = obj.option;
        parent = obj.parent;
        var target = obj[option];

        if(parent === "" || option === ""){
            return false;
        }


        if(option !== "add"){
            //加载数据
            console.log(option)
        }

        //先把所有的右边区域的内容都隐藏
        $(".manager-content").hide();
        $(".buyer-content").hide();
        $(".employ-content").hide();

        //显示点击的页面
        $(parent).fadeIn("fast");
        for(var key in obj){
            var value = obj[key];
            if(key === "option" || key === "parent"){
                continue;
            }else if (value === target) {
                // 显示
                $(value).fadeIn("slow");
            } else {
                // 隐藏
                $(value).fadeOut("fast");
            }

        }

    },
    //显示模态框，并渲染数据
    methodData: function(method , studentNumber , lessonNumber ){
        if (method == null || method == "" || studentNumber == null || studentNumber == "" || lessonNumber == null || lessonNumber == "") {
            return;
        }
        method = $.trim( method );
        studentNumber = $.trim( studentNumber );
        lessonNumber = $.trim( lessonNumber );
        if(method === "delete"){
            return $.delete( studentNumber , lessonNumber );
        }

        //向后台获取一条数据根据学生编号和课程编号
        console.log("向后台获取一条数据根据学生编号和课程编号");
        console.log(studentNumber , lessonNumber);

        $.getDataByNumber(studentNumber , lessonNumber);
    },
    //向后台根据学生编号和课程编号获取一条数据
    getDataByNumber: function(studentNumber , lessonNumber){
        //获取数据

        //渲染数据

        //显示模态框
        $(".operation").fadeIn("slow");
    },
    //删除一行数据，只删除数据库中的成绩表中的字段
    delete: function( studentNumber , lessonNumber ){
        console.log("删除一行数据");
        console.log(studentNumber , lessonNumber );
        //向后台提交数据
    },

    //为弹出来的模态框绑定确定和返回事件
    operation: function( text ){
        if (text == null || text == "") {
            return;
        }
        text = $.trim(text);

        if(text === "返回"){
            //隐藏模态框
            $(".operation").fadeOut("fast");
        }else if(text === "确定"){
            //获得数据
            var studentNumber = $("#managerOperationStudentNumber").text();
            var studentClass = $("#managerOperationClass").val();
            var studentName = $("#managerOperationStudentName").text();
            var lesson = $("#managerOperationLesson").val();
            var lessonNumber = $("#managerOperationNumber").val();
            var credit = $("#managerOperationCredit").val();
            var score = $("#managerOperationScore").val();
            //提交数据

            console.log(studentNumber);
            console.log(studentClass);
            console.log(studentName);
            console.log(lesson);
            console.log(lessonNumber);
            console.log(credit);
            console.log(score);
            //隐藏模态框

        }
    },

    //search 混合搜索
    search: function( search ){
        if (search == null || search == "") {
            return;
        }
        search = $.trim(search);
        switch (search) {
            //成绩维护search
            case "maintainSearch":
                return $.maintainSearch();
            //考试成绩search
            case "scoreSearch":
                return $.scoreSearch();
            //考试排名search
            case "sortSearch":
                return $.sortSearch();
        }
    },
    //成绩维护search
    maintainSearch: function(){
        var ManagerSearchById = $("#ManagerSearchById").val();//学号
        var ManagerSearchByName = $("#ManagerSearchByName").val();//班级编号
        var ManagerSearchByStudentName = $("#ManagerSearchByStudentName").val();//学生姓名
        var ManagerSearchByLesson = $("#ManagerSearchByLesson").val();//课程名
        var ManagerSearchByScore = $("#ManagerSearchByScore").val();//学分

        //提交数据

        //渲染数据


    },

    //考试成绩search
    scoreSearch: function(){
        var querySearchById = $("#querySearchById").val();//学号
        var querySearchByName = $("#querySearchByName").val();//班级编号
        var querySearchByRemain = $("#querySearchByRemain").val();//学生姓名
        var querySearchByLimit = $("#querySearchByLimit").val();//课程名
        var querySearchByAppend = $("#querySearchByAppend").val();//学分

        //提交数据

        //渲染数据


    },
    //考试排名search
    sortSearch: function(){
        var sortSearchById = $("#sortSearchById").val();//学号
        var sortSearchByName = $("#sortSearchByName").val();//课程编号
        var sortSearchByRemain = $("#sortSearchByRemain").val();//学生姓名
        var sortSearchByLimit = $("#sortSearchByLimit").val();//平均分
        var sortSearchByAppend = $("#sortSearchByAppend").val();//课名

        //提交数据

        //渲染数据

    }

});