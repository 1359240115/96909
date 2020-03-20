<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="../css/index.css" rel="stylesheet" type="text/css">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<style type="text/css">
<!--
.STYLE1 {	font-size: 24px;
	font-weight: bold;
}
-->
</style>
<head>
  <script  type="text/javascript" src="jquery-1.3.2.min.js"></script>
  <script type="text/javascript">
    /*$(document).ready(function () {
        alert("OK")
    })*/
    $(function () {
        document.getElementById("cname").onchange=function () {
            var cname = document.getElementById("cname").value;
            $.ajax({
                dataType:"json",    //数据类型为json格式
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                type:"get",
                url:"/96909/YwglSvl?reqType=findjsr&companyname="+cname,
                statusCode: {
                    404: function() {
                        alert('提交地址url未发现。 ');
                    }
                },
                success:function(data,textStatus){
                    //console.log(data);
                    var item=data.people;
                    //console.log(item);

                    var str = null;
                    for (var i = 0; i <item.length ; i++) {
                        //console.log(item[i]);
                        str += '<option>'+item[i].name+'</option>'
                    }
                   $("#username").html(str);
                }
            });
        }
    })
  </script>

<title>Untitled Document</title>
<script type="text/javascript" src="../tdp/js/validator.js"></script>
<script type="text/javascript" src="../tdp/js/public.js"></script>
<script type="text/javascript" src="../javascript/handleArchive.js"></script>

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
<form action="/96909/YwglSvl">
    <input type="hidden" name="reqType" value="addMessageto">
    <table width="96%" height="49" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#bdc7d3" class="text_lb">

        <tr>
          <td height="23" align="right" bgcolor="#FFFFFF">标题：</td>
          <td colspan="3" bgcolor="#FFFFFF"><input name="title" type="text" id="textarea" value="" size="30" class="pi"></td>
        </tr>
        <tr>
          <td height="23" align="right" bgcolor="#FFFFFF">内容：</td>
          <td colspan="3" bgcolor="#FFFFFF"><textarea name="context" id="textarea7" cols="45" rows="10"></textarea></td>
        </tr>
        <tr>
          <td width="73" height="23" align="right" bgcolor="#FFFFFF">接收人：</td>
          <td width="158" bgcolor="#FFFFFF">
            <select name="cname" id="cname">
              <option></option>
              <c:forEach items="${users}" var="user">
                <option>${user.companyname}</option>
              </c:forEach>
            </select>
          </td>
          <td width="60" align="right" bgcolor="#FFFFFF">&nbsp;</td>
          <td width="450" bgcolor="#FFFFFF">&nbsp;</td>
        </tr>
        <tr>
          <td height="23" align="right" bgcolor="#FFFFFF"></td>
          <td  bgcolor="#FFFFFF">
             <select name="username" id="username" size="10"  style="width:120px">

             </select>
          </td>
           <td  bgcolor="#FFFFFF">
           </td>
           <td  bgcolor="#FFFFFF">
          </td>
        </tr>
      </table>
    <table width="96%" height="30" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td width="67%" align="center" class="text" nowrap>
    <input type="submit"value="发  送" class="button_new">
    <input type="button" value="返  回" class="button_new" onClick="javascript:window.history.back()" >
    &nbsp;&nbsp; </td>
  </tr>
</table>
</form>
</body>
</html>
