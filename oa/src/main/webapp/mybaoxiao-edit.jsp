<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    当前位置:报销管理>>添加新的报销
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="form11" action="mybaoxiao-edit" method="post">
	<input type="hidden" name="bxstatus" id="status">
	<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
		<tr >
			<td colspan="2"  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
				<input  type="hidden" name="bxid" id="bxid" value="${baoxiao.bxid}"/>
			</td>
		</tr>
		<tr >
			<td align="right" bgcolor="#FAFAF1" height="22">报销类型：</td>
			<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
				<select name="paymode">
					<c:forEach items="${types}" var="type">
						<c:choose>
							<c:when test="${baoxiao.paymode == type.etid}">
								<option value="${type.etid}" selected>${type.etname}</option>
							</c:when>
							<c:otherwise>
								<option value="${type.etid}">${type.etname}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr >
			<td align="right" bgcolor="#FAFAF1" height="22">金额：</td>
			<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
				<input value="${baoxiao.totalmoney}" name="totalmoney"/></td>
		</tr>
		<tr >
			<td align="right" bgcolor="#FAFAF1" height="22">使用时间</td>
			<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">

				<input name="bxtime" value="<fmt:formatDate value='${baoxiao.bxtime}' pattern='yyyy-MM-dd' ></fmt:formatDate>"/></td>
		</tr>
		<tr >
			<td align="right" bgcolor="#FAFAF1" >备注：</td>
			<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
				<textarea name="bxremark" rows=10 cols=130>${baoxiao.bxremark}</textarea>
			</td>
		</tr>
		<tr >
			<td align="right" bgcolor="#FAFAF1" >历次审批意见：</td>
			<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
				<c:forEach items="${baoxiao.baoxiaoreplyList}" var="reply">
					<p>
							${reply.replytime}<br>
							${reply.content}
					</p>
				</c:forEach>
			</td>
		</tr>
		<tr bgcolor="#FAFAF1">
			<td height="28" colspan=4 align=center>
				<a href="javascript:commit(0)" class="coolbg">再提交</a>
				<a href="" class="coolbg">返回</a>
			</td>
		</tr>
	</table>

</form>

<script type="text/javascript">
	function commit(status) {
		$("#status").val(status);
		$("#form11").submit();
	}
</script>
</body>
</html>