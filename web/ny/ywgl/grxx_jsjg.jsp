<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="../css/index.css" rel="stylesheet" type="text/css">
<link href="../css/button.css" rel="stylesheet" type="text/css">
<html>
<head>
<title>Untitled Document</title>
<script type="text/javascript" src="../js/public.js"></script>
<script language="JavaScript">
function doDBClick(url,operator,type) {
  if (operator == false) {
    document.forms[0].action = url + "&op=view";
    document.forms[0].submit();
  } else {
    document.forms[0].action = url;
    document.forms[0].submit();
  }
}
</script>
</head>

<body>

<table width="100%"  border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" class="where">
  <tr>
    <td>&nbsp;&nbsp;※&nbsp;您的位置：我的桌面--&gt;人力资源--&gt;检索结果</td>
    <td align="right">&nbsp;</td>
  </tr>
</table>
  <table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#bdc7d3">
    <tr align="center" class="tdtitle">
      <td nowrap align="center" width="3%">序号</td>
      <td width="12%" align="center" nowrap id=".name" ><strong>姓名</strong></td>
      <td width="11%" height="24" align="center" nowrap id=".name" ><div align="center" orderBy="true"><strong>性别</strong></div></td>
      <td width="12%" height="24" align="center" nowrap id=".phone" ><div align="center" orderBy="true"><strong>年龄</strong></div></td>
      <td width="16%" align="center" nowrap id=".title" ><div align="center" orderBy="true"><strong>员工类型</strong></div></td>
      <td width="13%" align="center" nowrap id=".register" ><div align="center" orderBy="true"><strong>个人要求</strong></div></td>
      <td width="9%" align="center" nowrap id=".register" ><strong>状态</strong></td>
      <td width="15%" align="center" nowrap id=".submit_date" ><strong>录入日期</strong></td>
	  <td width="15%" align="center" nowrap id=".submit_date" ><strong>所属公司</strong></td>
      <td width="9%" align="center" nowrap id=".submit_date" ><strong>操作</strong></td>
    </tr>
    <c:forEach items="${workerList}" var="worker">
      <tr align="center" class="td2"  onmouseover="javascript:changeBgColorOnMouseOver(this);" onMouseOut="javascript:changeBgColorOnMouseOut(this);" onDblClick="doDBClick('bl.htm',true,'2');">
        <td nowrap align="center" width="3%">${worker.w_id}</td>
        <td align="center" nowrap>${worker.name}</td>
        <td height="14" align="center" nowrap>${worker.sex}</td>
        <td align="center" nowrap>${worker.age}</td>
        <td align="center" nowrap>${worker.type}</td>
        <td align="center" nowrap>1800-2300</td>
        <td align="center" nowrap>${worker.status}</td>
        <td align="center" nowrap>${worker.inputtime}&nbsp; </td>
        <td align="center" nowrap>${worker.c_id}</td>
        <td align="center" nowrap><a href="${pageContext.request.contextPath}/ny/rlzy/grxx_ck.htm">查看</a> <a href="${pageContext.request.contextPath}/ny/rlzy/grxx_xg.htm">修改</a></td>
      </tr>
    </c:forEach>
  </table>
  </body>
</html>
