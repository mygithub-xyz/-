<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>编辑项目信息</title>
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
                        当前位置:项目管理>>编辑项目基本信息
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<form name="form2" id="projectedit">
    <input type="hidden" id="pid" name="pid">
    <table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
        <tr bgcolor="#E7E7E7">
            <td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;编辑项目信息&nbsp;</td>
        </tr>
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">项目名称：</td>
            <td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input id="pname" name="pname"/></td>
            <td align="right" bgcolor="#FAFAF1" height="22">客户公司名称：</td>
            <td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input id="cusname"/><input type="hidden" id="cusid" name="comname"></td>
        </tr>
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">客户方负责人：</td>
            <td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input id="cusperson"/></td>
            <td align="right" bgcolor="#FAFAF1" height="22">项目经理：</td>
            <td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input id="ename"/><input type="hidden" id="empFk" name="empFk"></td>
        </tr>
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">开发人数：</td>
            <td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input id="empcount" name="empcount">人</td>
            <td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
            <td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input id="starttime" name="starttime"/></td>
        </tr>
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">立项时间：</td>
            <td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input id="buildtime" name="buildtime"/></td>
            <td align="right" bgcolor="#FAFAF1" height="22">预估成本：</td>
            <td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input id="cost" name="cost"/>万</td>
        </tr>
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">级别：</td>
            <td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
                <select id="level" name="level">
                    <option value=1 >紧急</option>
                    <option value=2>一般</option>
                    <option value=3>暂缓</option>
                </select>
            </td>
            <td align="right" bgcolor="#FAFAF1" height="22">计划完成时间：</td>
            <td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input id="endtime" name="endtime"/></td>
        </tr>

        <tr >
            <td align="right" bgcolor="#FAFAF1" >备注：</td>
            <td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
                <textarea rows=15 cols=130 id="remark" name="remark"></textarea>
            </td>
        </tr>


        <tr bgcolor="#FAFAF1">
            <td height="28" colspan=4 align=center>
                &nbsp;
                <a href="javascript:projectEdit()" class="coolbg">保存</a>
                <a href="project-base.jsp" class="coolbg">返回</a>
            </td>
        </tr>
    </table>

</form>

<script type="text/javascript">
    $(function () {
        showProject("${param.pid}");//等同于request.getParameter("pid")
    })

    //显示项目的功能
    function showProject(pid) {
        // alert(pid);
        $.ajax({
            url:"project/project-base-edit-show",
            data:"pid="+pid,
            dataType:"json",
            statusCode:{
                200: function (obj) {
                    var bb = dateto(obj.buildtime);
                    var sb = dateto(obj.starttime);
                    var eb = dateto(obj.endtime);
                    //页面中文本框显示数据
                    $("#pid").val(obj.pid);
                    $("#pname").val(obj.pname);
                    $("#cusname").val(obj.customer.comname);
                    $("#cusid").val(obj.customer.id);
                    $("#cusperson").val(obj.customer.companyperson);
                    $("#ename").val(obj.employee.ename);
                    $("#empFk").val(obj.empFk);
                    $("#empcount").val(obj.empcount);
                    $("#starttime").val(sb);
                    $("#buildtime").val(bb);
                    $("#cost").val(obj.cost);
                    //级别的设置
                    $("#level").val(obj.level).prop("selected", true);
                    //级别的设置
                    $("#endtime").val(eb);
                    $("#remark").val(obj.remark);
                },
                404:function () {
                    alert("请求有误");
                },
                500:function () {
                    alert("请求失败");
                }
            }
        });
    }

    //修改功能实现  -- 异步
    function projectEdit() {
        $.ajax({
            url:"project/project-edit",
            type:"post",
            data:$("#projectedit").serialize(),
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
</script>
</body>
</html>