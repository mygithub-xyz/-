<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>项目信息管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
   <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>基本信息管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='project-base-add.jsp';" value='添加新项目' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' id="form3">
<input type='hidden' name='currentPage' id="currentPage" />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
              <select name='conname' style='width:150px'>
                  <option value=''>选择类型...</option>
                  <option value='pname'>项目名称</option>
                  <option value='ename'>项目经理</option>
                  <option value='c.comname'>客户公司名称</option>
              </select>
        </td>
        <td width='70' >
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='keyword' value='' style='width:120px' />
        </td>
        <td width='110'>
            <select name='orderby' style='width:120px'>
                <option value='pid'>排序...</option>
                <option value='buildtime'>立项时间</option>
                <option value='endtime'>计划完成时间</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<img name="imageField" onclick="showList(1)" type="image" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2" id="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;项目信息列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">项目名称</td>
	<td width="10%">客户公司名称</td>
	<td width="10%">客户方负责人</td>
	<td width="5%">项目经理</td>
	<td width="8%">开发人员数</td>
	<td width="6%">立项时间</td>
	<td width="8%">最近更新时间</td>
	<td width="8%">计划完成时间</td>
	<td width="8%">状态</td>
	<td width="10%">操作</td>
</tr>

  <%--列表数据--%>
    <tbody id="showproject">
    </tbody>


<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	<a href="javascript:chooseAll()" class="coolbg">全选</a>
	<a href="javascript:unchooseAll()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:batchDelete()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
<script type="text/javascript">
    $(function () {
        showList(1);
    })
    function showList(num) {
        //保存当前页号的隐藏框，填充上页码
        $("#currentPage").val(num);
        $.ajax({
            url:"project/project-show",
            type:"post",
            data:$("#form3").serialize(),
            dataType:"json",
            success:function(obj){
                //console.log(obj);
                $("#showproject").empty();
                $.each(obj.list,function (i, project) {
                    var j = i + 1;
                    var bb = dateto(project.buildtime);
                    var sb = dateto(project.starttime);
                    var eb = dateto(project.endtime);
                    $("#showproject").append(
                        '<tr align="center" bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor=\'#FCFDEE\';" onMouseOut="javascript:this.bgColor=\'#FFFFFF\';" height="22" >'+
                        '<td><input name="ids" type="checkbox" id="id" value="'+project.pid+'" class="np"></td>'+
                        '<td>'+j+'</td>'+
                        '<td align="center"><a href="javascrip:void(0)"><u>'+project.pname+'</u></a></td>'+
                        '<td>'+project.customer.comname+'</td>'+
                        '<td>'+project.customer.companyperson+'</td>'+
                        '<td>'+project.employee.ename+'</td>'+
                        '<td>'+project.empcount+'</td>'+
                        '<td>'+bb+'</td>'+
                        '<td>'+sb+'</td>'+
                        '<td>'+eb+'</td>'+
                        '<td>进行中</td>'+
                        '<td><a href="project-base-edit.jsp?pid='+project.pid+'">编辑</a> | <a href="project-base-look.jsp?pid='+project.pid+'">查看详情</a></td>'+
                        '</tr>'
                    );
                });

                var str = "";
                $.each(obj.navigatepageNums,function (i, num) {
                    if(num == obj.pageNum){
                        var s = "<a style='color: red;' href='javascript:showList("+num+")'>"+num+"</a>" + "&nbsp;&nbsp;";
                    }else {
                        var s = "<a href='javascript:showList("+num+")'>"+num+"</a>" + "&nbsp;&nbsp;";
                    }
                    str += s;
                });

                $("#showproject").append(
                    '<tr bgcolor="#FAFAF1" align="center">'+
                    '<td height="28" colspan="12">'+
                    '&nbsp;<a href="javascript:showList('+1+')">首页</a>'+
                    '&nbsp;<a href="javascript:showList('+obj.prePage+')">上一页</a>'+
                    '【&nbsp;&nbsp;'+str+'】'+
                    '&nbsp;<a href="javascript:showList('+obj.nextPage+')">下一页</a>'+
                    '&nbsp;<a href="javascript:showList('+obj.pages+')">尾页</a>'+
                    '</td>'+
                    '</tr>'
                );
            },
            error:function () {
                alert("发生异常");
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
    /*全选功能*/
    function chooseAll() {
        var cs = $("input[type='checkbox']");
        for (var i = 0; i < cs.length; i++) {
            var jsO = cs[i];
            var jqO = $(jsO);
            jqO.prop("checked", true);
        }
    }
    /*反选功能*/
    function unchooseAll() {
        var cs = $("input[type='checkbox']");
        for (var i = 0; i < cs.length; i++) {
            var jsO = cs[i];
            var jqO = $(jsO);
            var bo = jqO.prop("checked");
            jqO.prop("checked", !bo);
        }
    }
    //批量删除功能
    function batchDelete() {
        var cs = $("input[type='checkbox']:checked");
        if(cs.length != 0){
            var bo = confirm("是否要删除当前选中的数据呢？");
            if(bo){
                //异步实现删除
                $.ajax({
                    url:"project/project-delete",
                    type:"post",
                    data:$("#form2").serialize(),
                    dataType:"json",
                    statusCode:{
                        200:function () {
                            location.href = "project-base.jsp";
                        },
                        404:function () {
                            alert("请求有误")
                        },
                        500:function () {
                            alert("请求异常")
                        }
                    }
                });
            }else {
                alert("你撤销了删除操作");
            }
        }else {
            alert("要删除数据，需要先选中它，再实现删除");
        }
    }

</script>

</body>
</html>