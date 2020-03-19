<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="../css/index.css" rel="stylesheet" type="text/css">
<html>
<style type="text/css">
    <!--
    .STYLE1 {	font-size: 24px;
        font-weight: bold;
    }
    -->
</style>
<head>
    <title>Untitled Document</title>
    <script type="text/javascript" src="../tdp/js/validator.js"></script>
    <script type="text/javascript" src="../tdp/js/public.js"></script>
    <script type="text/javascript" src="../javascript/handleArchive.js"></script>
    <script language="JavaScript">
        function doDBClick(url,operator,type) {
            if (operator == false) {
                document.forms[0].action = url + "&op=view";
                document.forms[0].submit()
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
        <td>&nbsp;&nbsp;※&nbsp;您的位置：我的桌面--&gt;业务管理--&gt;内部短消息</td>
        <td align="right">&nbsp;</td>
    </tr>
</table>
<br/>
<br>
<form action="${pageContext.request.contextPath}/YwglSvl">
    <input type="hidden" name="reqType" value="msgreply">
    <table width="96%" height="49" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#bdc7d3" class="text_lb">

        <tr>
            <td height="23" align="right" bgcolor="#FFFFFF">标题：</td>
            <td colspan="3" bgcolor="#FFFFFF"><input name="msgtitle" type="text" id="textarea" value="" size="30" class="pi"></td>
        </tr>
        <tr>
            <td height="23" align="right" bgcolor="#FFFFFF">内容：</td>
            <td colspan="3" bgcolor="#FFFFFF"><textarea name="msgcontext" id="textarea7" cols="45" rows="10"></textarea></td>
        </tr>
        <tr>
            <td width="73" height="23" align="right" bgcolor="#FFFFFF">接收人：</td>
            <td width="158" bgcolor="#FFFFFF">
                <% String jsr = request.getParameter("jsr");%>
                <select name="jsr">
                    <option><%=jsr%></option>
                </select>
            </td>
            <td width="60" align="right" bgcolor="#FFFFFF">&nbsp;</td>
            <td width="450" bgcolor="#FFFFFF">&nbsp;</td>
        </tr>
    </table>
    <table width="96%" height="30" border="0" align="center" cellpadding="0" cellspacing="0" >
    <tr>
        <td width="67%" align="center" class="text" nowrap>
            <input type="submit"  value="发  送" class="button_new" >
            <input type="button"  value="返  回" class="button_new"
                   onClick="javascript:window.history.back()" >
            &nbsp;&nbsp; </td>
    </tr>
</table>
</form>
</body>
</html>
