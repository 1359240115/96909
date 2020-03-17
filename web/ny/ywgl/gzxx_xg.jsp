<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="../css/index.css" rel="stylesheet" type="text/css">
<html>
<style type="text/css">
<!--
.STYLE2 {font-size: 16px}
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
    <td>&nbsp;&nbsp;※&nbsp;您的位置：我的桌面--&gt;业务管理--&gt;客户管理</td>
    <td align="right">&nbsp;</td>
  </tr>
</table>
<br>
<table width="96%" height="80" border="0" align="center" cellpadding="0" cellspacing="0" >
    <tr>
      <td width="67%" height="40" align="center" nowrap class="MENU_line1">家政服务消费者资料登记表</td>
    </tr>
    <tr>
      <td align="right" nowrap class="MENU_line1 STYLE2">档案编号：GZ20006300223</td>
    </tr>
  </table>
<br>
<form action="${pageContext.request.contextPath}/YwglSvl">
    <input type="hidden" name="reqType" value="update">
    <table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#bdc7d3" class="text_lb">
        <tr>
            <td width="10%" align="right" bgcolor="#FFFFFF">姓名：</td>
            <td width="15%" bgcolor="#FFFFFF">
                <input name="e_name" readonly="readonly" value="${employer.name}" type="text" size="15" class="pi">
            </td>
            <td width="10%" align="right" bgcolor="#FFFFFF">性别：</td>
            <td width="17%" bgcolor="#FFFFFF">
                <input type="radio" name="sex" checked="checked" id="radio12" value="男">男
                <input type="radio" name="sex" id="radio13" value="女">女
            </td>
            <td width="10%" align="right" bgcolor="#FFFFFF">年龄：</td>
            <td width="13%" bgcolor="#FFFFFF"><input name="age" value="${employer.age}" type="text" id="textarea" size="15" class="pi"></td>
            <td width="10%" align="right" bgcolor="#FFFFFF">民族：</td>
            <td width="15%" bgcolor="#FFFFFF"><input name="nation" value="${employer.mingzu}" type="text" id="textarea2" size="15" class="pi"></td>
        </tr>
        <tr>
            <td align="right" bgcolor="#FFFFFF">籍贯：</td>
            <td bgcolor="#FFFFFF"><input name="native" value="${employer.jiguan}" type="text" id="textarea5" size="15" class="pi"></td>
            <td align="right" bgcolor="#FFFFFF">学历：</td>
            <td bgcolor="#FFFFFF"><input name="education" value="${employer.xueli}" type="text" id="textarea4"  size="15" class="pi"></td>
            <td align="right" bgcolor="#FFFFFF">身份证号码：</td>
            <td colspan="3" bgcolor="#FFFFFF"><input name="idcard" value="${employer.idcard}" type="text" id="textarea3" size="40" class="pi"></td>
        </tr>
        <tr>
            <td align="right" nowrap bgcolor="#FFFFFF">工作单位：</td>
            <td colspan="5" bgcolor="#FFFFFF"><input name="workspace" value="${employer.workspace}" type="text" id="textarea6"  size="65" class="pi"></td>
            <td align="right" bgcolor="#FFFFFF">职业：</td>
            <td bgcolor="#FFFFFF"><input name="occupation" value="${employer.zhiye}" type="text" id="textarea7"  size="15" class="pi"></td>
        </tr>
        <tr>
            <td align="right" nowrap bgcolor="#FFFFFF">合同号：</td>
            <td colspan="5" bgcolor="#FFFFFF"><input readonly="readonly" value="${employer.hetonghao}" name="contractid" type="text" id="textarea8"  size="65" class="pi"></td>
            <td align="right" bgcolor="#FFFFFF">合同期限：</td>
            <td bgcolor="#FFFFFF"><input name="contractdate" value="${employer.hetongqixian}" type="text" id="textarea9"  size="15" class="pi"></td>
        </tr>
        <tr>
            <td align="right" nowrap bgcolor="#FFFFFF">联系电话：</td>
            <td align="center" bgcolor="#FFFFFF">手机</td>
            <td colspan="4" bgcolor="#FFFFFF"><input name="telphone" value="${employer.phone}" type="text" id="textarea10" size="44" class="pi"></td>
            <td align="right" bgcolor="#FFFFFF">住宅：</td>
            <td bgcolor="#FFFFFF"><input name="address1" value="${employer.address}" type="text" id="textarea11." size="15" class="pi"></td>
        </tr>
        <tr>
            <td align="right" nowrap bgcolor="#FFFFFF">户口所在地：</td>
            <td colspan="7" bgcolor="#FFFFFF"><input name="hkszd" value="${employer.home}" type="text" id="textarea12"  size="65" class="pi"></td>
        </tr>
        <tr>
            <td colspan="2" align="right" bgcolor="#FFFFFF">服务处所（地址）：</td>
            <td colspan="6" bgcolor="#FFFFFF"><input name="address" value="${employer.address}" type="text" id="textarea13"  size="44" class="pi"></td>
        </tr>
        <tr>
            <td rowspan="6" align="center" bgcolor="#FFFFFF">家<br>
                庭<br>
                资<br>
                料</td>
            <td align="right" bgcolor="#FFFFFF">居住地址：</td>
            <td colspan="6" align="left" bgcolor="#FFFFFF"><input name="home" value="${employer.address}" type="text" id="textarea14" size="44" class="pi"></td>
        </tr>
        <tr>
            <td align="right" bgcolor="#FFFFFF">家庭人数：</td>
            <td colspan="6" bgcolor="#FFFFFF"><input name="jtrs" value="${employer.jtrs}" type="text" id="textarea15" size="44" class="pi"></td>
        </tr>
        <tr>
            <td align="right" bgcolor="#FFFFFF">服务内容：</td>
            <td colspan="6" bgcolor="#FFFFFF"><input name="fwnr" value="${employer.fwnr}" type="text" id="textarea16"  size="44" class="pi"></td>
        </tr>
        <tr>
            <td align="right" bgcolor="#FFFFFF">房屋面积：</td>
            <td colspan="6" bgcolor="#FFFFFF"><input name="fwmj" value="${employer.fwmj}" type="text" id="textarea17" size="44" class="pi"></td>
        </tr>
        <tr>
            <td align="right" bgcolor="#FFFFFF">饮食习惯：</td>
            <td colspan="6" bgcolor="#FFFFFF"><input name="ysxg" value="${employer.ysxg}" type="text" id="textarea18" size="44" class="pi"></td>
        </tr>
        <tr>
            <td align="right" bgcolor="#FFFFFF">其他：</td>
            <td colspan="6" bgcolor="#FFFFFF"><input name="qita" value="${employer.qita}" type="text" id="textarea19" size="44" class="pi"></td>
        </tr>
        <%--<tr>
          <td align="center" nowrap bgcolor="#FFFFFF">要求服务等级：</td>
          <td colspan="7" align="left" bgcolor="#FFFFFF"><input name="textarea20" type="text" id="textarea20" value="" size="65" class="pi"></td>
        </tr>--%>
        <tr>
            <td align="right" nowrap bgcolor="#FFFFFF">接受工资：</td>
            <td colspan="2" align="left" bgcolor="#FFFFFF">从
                <input name="minprice" type="text" value="${employer.minprice}" id="textarea11" size="8" class="pi">
                到
                <input name="maxprice" type="text" value="${employer.maxprice}" id="textarea23" size="8" class="pi"></td>
            <td colspan="5" bgcolor="#FFFFFF">&nbsp;</td>
        </tr>
        <tr>
            <td align="right" nowrap bgcolor="#FFFFFF">经办人：</td>
            <td colspan="7" align="left" bgcolor="#FFFFFF"><input name="chargeman" value="${employer.jingbanren}" type="text" id="textarea21" size="65" class="pi"></td>
        </tr>
        <tr>
            <td align="right" nowrap bgcolor="#FFFFFF">登记日期：</td>
            <td colspan="7" align="left" bgcolor="#FFFFFF"><input name="inputtime" value="${employer.inputdate}" type="text" id="textarea22" value="" size="65" class="pi"></td>
        </tr>
      </table>
    <table width="96%" height="30" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td width="67%" align="center" class="text" nowrap>
      <input type="submit" value="保  存" class="button_new">
      <input type="button"  value="返  回" class="button_new"
             onClick="javascript:window.history.back()" >
    </td>
  </tr>
</table>
</form>
</body>
</html>
