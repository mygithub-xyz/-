<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>附件管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:个人报销管理>>报销列表
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='mybaoxiao-add-show';" value='添加报销' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>


<!--  内容列表   -->
<form name="form2">

<table width="98%"  cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;附件列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="20%">编号</td>
	<td width="6%">总金额</td>
	<td width="10%">使用时间</td>
	<td width="40%">备注信息</td>
	<td width="10%">审批状态</td>
	<td width="10%">操作</td>
</tr>

<c:forEach items="${bes}" var="baoxiao" varStatus="sta">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${baoxiao.bxid}" class="np"></td>
	<td>${sta.count}</td>
	<td>${baoxiao.totalmoney}</td>
	<td align="center"><a href=''><u> <fmt:formatDate value="${baoxiao.bxtime}" pattern="yyyy-MM-dd"></fmt:formatDate> </u></a></td>
	<td>${baoxiao.bxremark}</td>
	<%--报销状态，报销单当前处于什么状态：
	未审批的状态0；
	(一级)
	审核通过状态1；
	审核驳回状态2；
	(二级
	通过3
	驳回4
	三级
	四级.....)
	未付款3
	已付款4
	--%>
		<c:choose>
			<c:when test="${baoxiao.bxstatus == 0}">
				<td>未审批</td>
				<td>
					<a href="${pageContext.request.contextPath}/mybaoxiao-edit.jsp?bxid=${baoxiao.bxid}">编辑</a>
					<a href="${pageContext.request.contextPath}/mybaoxiao-edit.jsp?bxid=${baoxiao.bxid}">查看详情</a>
					<a href="${pageContext.request.contextPath}/mybaoxiao-edit.jsp?bxid=${baoxiao.bxid}">撤销</a>
				</td>
			</c:when>
			<c:when test="${baoxiao.bxstatus == 1}">
				<td>审批通过</td>
				<td><a href="" style="pointer-events:none;color: grey" >查看详情</a>
					<a href="${pageContext.request.contextPath}/mybaoxiao-edit.jsp?bxid=${baoxiao.bxid}">撤销</a>
				</td>
			</c:when>
			<c:when test="${baoxiao.bxstatus == 2}">
				<td>驳回</td>
				<td><a href="${pageContext.request.contextPath}/mybaoxiao-edit-show?bxid=${baoxiao.bxid}">编辑</a>
					<a href="${pageContext.request.contextPath}/mybaoxiao-edit.jsp?bxid=${baoxiao.bxid}">查看详情</a>
					<a href="${pageContext.request.contextPath}/mybaoxiao-edit.jsp?bxid=${baoxiao.bxid}">撤销</a>
				</td>
			</c:when>
			<c:when test="${baoxiao.bxstatus == 3}">
				<td>审批未付款</td>
				<td><a href="javascript:void(0)" style="pointer-events:none;color: grey" >查看详情</a>
					<a href="javascript:void(0)" style="pointer-events:none;color: grey" >打印</a>
				</td>
			</c:when>
			<c:when test="${baoxiao.bxstatus == 4}">
				<td>已付款</td>
				<td><a href="javascript:void(0)" style="pointer-events:none;color: grey" >查看详情</a>
					<a href="javascript:void(0)" style="pointer-events:none;color: grey" >打印</a>
				</td>
			</c:when>
		</c:choose>
</tr>
</c:forEach>


<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>