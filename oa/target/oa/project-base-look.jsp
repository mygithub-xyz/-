<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>查看项目信息</title>
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
                        当前位置:项目管理>>查看项目基本信息
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<form name="form2">

    <table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
        <tr bgcolor="#E7E7E7">
            <td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;查看项目信息&nbsp;</td>
        </tr>
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">项目名称：</td>
            <td align='left' id="pname"bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"></td>
            <td align="right" bgcolor="#FAFAF1" height="22">客户公司名称：</td>
            <td align='left' id="cusname" bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"></td>
        </tr>
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">客户方负责人：</td>
            <td align='left' id="cusperson" bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"></td>
            <td align="right" bgcolor="#FAFAF1" height="22">项目经理：</td>
            <td align='left' id="ename" bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"></td>
        </tr>
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">开发人数：</td>
            <td align='left' id="empcount" bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"></td>
            <td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
            <td align='left' id="starttime" bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"></td>
        </tr>
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">立项时间：</td>
            <td align='left'id="buildtime" bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"></td>
            <td align="right" bgcolor="#FAFAF1" height="22">预估成本：</td>
            <td align='left' id="cost" bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"></td>
        </tr>
        <tr >
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">级别：</td>
            <td align='left' id="level" bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"></td>
            <td align="right" bgcolor="#FAFAF1" height="22">计划完成时间：</td>
            <td align='left' id="endtime" bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"></td>
        </tr>
        </tr>

        <tr >
            <td align="right" bgcolor="#FAFAF1" >备注：</td>
            <td colspan=3 id="remark" align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
            </td>
        </tr>


        <tr bgcolor="#FAFAF1">
            <td height="28" colspan=4 align=center>
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
                $.ajax({
                url:"project/project-base-edit-show",
                data:"pid="+pid,
                dataType:"json",
                statusCode:{
                200:function (obj) {
                //console.log(obj)
                var bb = dateto(obj.buildtime);
                var sb = dateto(obj.starttime);
                var eb = dateto(obj.endtime);
                //页面中文本框显示数据
                $("#pname").text(obj.pname);
                $("#cusname").text(obj.customer.comname);
                $("#cusperson").text(obj.customer.companyperson);
                $("#ename").text(obj.employee.ename);
                $("#empcount").text(obj.empcount+"人");
                $("#starttime").text(sb);
                $("#buildtime").text(bb);
                $("#cost").text(obj.cost+"万");
                if(obj.level==1){
                    $("#level").text("紧急");
                }
                if(obj.level==2){
                        $("#level").text("一般");
                    }
                if(obj.level==3){
                        $("#level").text("暂缓");
                    }

                $("#endtime").text(eb);
                $("#remark").text(obj.remark);
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