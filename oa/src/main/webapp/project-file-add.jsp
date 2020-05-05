<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>添加附件</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
    <tr>
        <td height="26" background="skin/images/newlinebg3.gif">
            <table width="58%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td >
                        当前位置:项目管理>>添加附件
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<form name="form2" id="form6" action="project/project-attachment-saveInfo" method="post" enctype="multipart/form-data" >

    <table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
        <tr bgcolor="#E7E7E7">
            <td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;添加附件&nbsp;</td>
        </tr>
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">选择项目：</td>
            <td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
                <select id="pro" name="proFk"><option value=1>选择项目</option></select>
            </td>
        </tr>
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">附件名称：</td>
            <td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input size="26" name="attname"/></td>
        </tr>
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">附件信息描述：</td>
            <td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input size="52" name="attdis"/></td>
        </tr>
        <tr >
            <td align="right" bgcolor="#FAFAF1" height="22">附件：</td>
            <td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
                <input type="file" name="files"/>
                <input type="file" name="files"/>
                <input type="file" name="files"/>
            </td>
        </tr>

        <tr >
            <td align="right" bgcolor="#FAFAF1" >备注：</td>
            <td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
                <textarea rows=10 cols=130 name="remark"></textarea>
            </td>
        </tr>


        <tr bgcolor="#FAFAF1">
            <td height="28" colspan=4 align=center>
                &nbsp;
                <a href="javascript:commit()" class="coolbg">保存</a>
                <a href="project-file.jsp" class="coolbg">返回</a>
            </td>
        </tr>
    </table>

</form>

<script type="text/javascript">
    $(function () {
        //预加载项目列表数据
        showProject();
    })

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

    function commit() {
        $("#form6").submit();
    }
</script>
</body>
</html>