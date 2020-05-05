//全选功能
function allCheck() {
    //获取所有的复选框
    //进行复选框遍历，遍历的同时，修改复选框的选中状态
    //标签+属性选择器
    var cs = $("input[type='checkbox']");
    for(var i = 0;i < cs.length;i++){
        var jsO = cs[i];//是js对象
        var jqO = $(jsO);//转换为jQuery对象
        jqO.prop("checked",true);
    }
}

//反选功能
function unCheck() {
    //获取复选框
    //遍历，遍历的同时改变状态
    var cs = $("input[type='checkbox']");
    for(var i = 0;i < cs.length;i++){
        var jsO = cs[i];//是js对象
        var jqO = $(jsO);//转换为jQuery对象
        var bo = jqO.prop("checked");//原状态
        jqO.prop("checked",!bo);
    }
}
//展示项目列表---展示所有的项目，但是应该受限，不论是否存在需求分析项目都要展示
function showProject() {
    $.ajax({
        url:"project/project-need-project-show",
        type:"post",
        data:"mark=1",
        dataType:"json",
        success:function (obj) {
            // console.log(obj);
            $("#pro").empty();
            $("#pro").append("<option value='-1'>----选择项目----</option>");
            $.each(obj,function (i, project) {
                $("<option></option>").val(project.pid).text(project.pname).appendTo($("#pro"));
            })
        }
    });
}
//毫秒值转日期
function dateto(da) {
    var bt = da;
    var bd = new Date(bt);
    var by = bd.getFullYear();
    var bm = bd.getMonth() + 1;
    var ba = bd.getDate();
    var bs = by + "-" + bm + "-" + ba;
    return bs;
}
//当选择项目之后，联动添加需求分析和模块信息
function addayalisys(pid) {
    $.ajax({
        url:"project/project-function-need-show",
        type:"post",
        data:"pid="+pid,
        dataType:"json",
        success:function (obj) {
            // console.log(obj);
            $("#anid").empty();
            $("<option></option>").val(obj.id).text(obj.title).appendTo($("#anid"));
            $("#mod").empty();
            $("#mod").append("<option value='-1'>---选择模块---</option>");
            if(obj.modules.length > 0){
                $.each(obj.modules,function (i, module) {
                    $("<option></option>").val(module.id).text(module.modname).appendTo($("#mod"));
                })
            }
        }
    })
}