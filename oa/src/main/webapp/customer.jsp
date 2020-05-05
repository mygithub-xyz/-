<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>客户信息管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

    <script type="application/javascript">
    </script>
</head>
<body leftmargin="8" topmargin="8" background='${pageContext.request.contextPath}/skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="${pageContext.request.contextPath}/skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:客户信息管理>>客户信息
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='customer-add-show?currentPage=${pi.pageNum}&size=${pi.size}&pages=${pi.pages}';" value='添加客户信息' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form id="formsearch" action="customer-search" method="post">
    <input type="hidden" id="currentPage" name="currentPage">
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='${pageContext.request.contextPath}/skin/images/wbg.gif' align='center'>
        <table border='0' cellpadding='0' cellspacing='0'>
            <tr>
                <td width='90' align='center'>搜索条件：</td>
                <td width='160'>
                    <select id="mt" name='conname' style='width:150px'>
                        <c:forEach items="${mapSelect}" var="mapS">
                            <c:choose>
                                <%--判断的是上一次的查询条件是什么--%>
                                <c:when test="${mapS.key == condition.conname}">
                                    <option value='${mapS.key}' selected>${mapS.value}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value='${mapS.key}'>${mapS.value}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td width='70'>
                    关键字：
                </td>
                <td width='160'>
                    <input type='text' id="info"  name='keyword' value="${condition.keyword}" style='width:120px' />
                </td>
                <td width='110'>
                    <select id="mtime" name='orderby' style='width:120px'>
                        <c:forEach items="${mapOrder}" var="mapO">
                            <c:choose>
                                <c:when test="${mapO.key == condition.orderby}">
                                    <option value='${mapO.key}' selected>${mapO.value}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value='${mapO.key}'>${mapO.value}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <%--input是一个图片按钮，默认功能就是提交，设置使用onClick没有效果
                    所以如果保留search函数，那么修改input标签--%>
                    &nbsp;&nbsp;&nbsp;<img name="imageField" onclick="search(1)" src="${pageContext.request.contextPath}/skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
                </td>
            </tr>
        </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">

<table id="infotb" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7" >
	<td height="24" colspan="12" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;需求列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" id="tr2">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">联系人</td>
	<td width="10%">公司名称</td>
	<td width="8%">添加时间</td>
	<td width="8%">联系电话</td>
	<td width="10%">操作</td>
</tr>





    <c:forEach items="${pi.list}" var="customer" varStatus="i">
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
        <td><input name="ids" type="checkbox" id="id" value="${customer.id}" class="np"></td>
        <td>${i.count}</td>
        <td>${customer.companyperson}</td>
        <td align="center">${customer.comname}</td>
        <td><fmt:formatDate value="${customer.addtime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        <td>${customer.comphone}</td>
        <td><a href="customer-edit-show?id=${customer.id}&currentPage=${pi.pageNum}&flag=edit">编辑</a> | <a href="customer-edit-show?id=${customer.id}&currentPage=${pi.pageNum}&flag=look">查看详情</a></td>
        </tr>
    </c:forEach>

    <tr bgcolor="#FAFAF1" align="center">
        <td height="28" colspan="12">&nbsp;
            &nbsp;&nbsp;<a href="javascript:search(${pi.firstPage})" class="coolbg">首页</a>
            &nbsp;&nbsp;<a href="javascript:search(${pi.prePage})" class="coolbg">上一页</a>&nbsp;&nbsp;
            【
               <c:forEach items="${pi.navigatepageNums}" var="num">

                <c:choose>
                    <c:when test="${pi.pageNum==num}">
                        &nbsp;&nbsp;<a style="color: red;" href="javascript:search(${num})" class="coolbg">${num}</a>
                    </c:when>
                    <c:otherwise>
                        &nbsp;&nbsp;<a href="javascript:search(${num}) " class="coolbg">${num}</a>
                    </c:otherwise>
                </c:choose>
               </c:forEach>
            &nbsp;&nbsp;】
            &nbsp;&nbsp;<a href="javascript:search(${pi.nextPage})" class="coolbg">&nbsp;下一页&nbsp;</a>
            &nbsp;&nbsp;<a href="javascript:search(${pi.pages})" class="coolbg">&nbsp;尾页&nbsp;</a>
        </td>
    </tr>

<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:chooseAll()" class="coolbg">全选</a>
	<a href="javascript:unchooseAll()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:deleteMany()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="javascript:exportToExcel()" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  <script type="text/javascript">
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
      //删除操作
      function deleteMany() {
        var cs=$("input[type='checkbox']:checked");
        if (cs.length!=0){
            var bo=confirm("是否确定要删除?");

            if (bo){
              var str="";
              for (var i = 0; i < cs.length; i++) {
              var jsO = cs[i];
              var jqO = $(jsO);
              var s = jqO.val() + ",";
              str += s;
          }
                str = str.substring(0,str.length-1);
                location.href = "customer-delete?lastPage=${pi.lastPage}&ids="+str;
      }else {
                alert("你撤销了删除操作！！！");
            }
        }else {
            alert("请先选择,再进行删除吧！！！");
        }
      }
      //导出操作到Excel表格的功能
      function exportToExcel() {
          //先判断是否有选中复选框，如果没有选中则导出当前页全部，如果有选中则导出选中的
          var cs = $("input[type='checkbox']:checked");
          var str = "";
          if(cs.length != 0){
              //表示有选中，就把选中的导出

              for(var i = 0;i < cs.length;i++){
                  var jsO = cs[i];
                  var jqO = $(jsO);
                  var s = jqO.val() + ",";
                  str += s;
              }
              str = str.substring(0,str.length-1);

              location.href = "customer-export?ids="+str;
          }else{
              //表示没有选中，就把当前页全部导出
              var cs1 = $("input[type='checkbox']");
              for(var i = 0;i < cs1.length;i++){
                  var jsO = cs1[i];
                  var jqO = $(jsO);
                  var s = jqO.val() + ",";
                  str += s;
              }
              str = str.substring(0,str.length-1);

              location.href = "customer-export?ids="+str;
          }
      }

      //搜索操作
      function search(num) {
          $("#currentPage").val(num)
          $("#formsearch").submit();
      }
  </script>

</body>
</html>