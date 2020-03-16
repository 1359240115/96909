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
    <td>&nbsp;&nbsp;※&nbsp;您的位置：我的桌面--&gt;雇主信息</td>
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
    <input type="hidden" name="reqType" value="insert">
    <table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#bdc7d3" class="text_lb">
        <tr>
          <td width="10%" align="right" bgcolor="#FFFFFF">姓名：</td>
          <td width="15%" bgcolor="#FFFFFF">
              <input name="e_name" type="text" size="15" class="pi"><span style="color: red ; font-size: 10px">*必填</span>
          </td>
          <td width="10%" align="right" bgcolor="#FFFFFF">性别：</td>
          <td width="17%" bgcolor="#FFFFFF">
              <input type="radio" name="sex" id="radio12" value="男">男
              <input type="radio" name="sex" id="radio13" value="女">女
          </td>
          <td width="10%" align="right" bgcolor="#FFFFFF">年龄：</td>
          <td width="13%" bgcolor="#FFFFFF"><input name="age" type="text" id="textarea" size="15" class="pi"></td>
          <td width="10%" align="right" bgcolor="#FFFFFF">民族：</td>
          <td width="15%" bgcolor="#FFFFFF"><input name="nation" type="text" id="textarea2" size="15" class="pi"></td>
        </tr>
        <tr>
          <td align="right" bgcolor="#FFFFFF">籍贯：</td>
          <td bgcolor="#FFFFFF"><input name="native" type="text" id="textarea5" size="15" class="pi"></td>
          <td align="right" bgcolor="#FFFFFF">学历：</td>
          <td bgcolor="#FFFFFF"><input name="education" type="text" id="textarea4"  size="15" class="pi"></td>
          <td align="right" bgcolor="#FFFFFF">身份证号码：</td>
          <td colspan="3" bgcolor="#FFFFFF"><input name="idcard" type="text" id="textarea3" size="40" class="pi"></td>
        </tr>
        <tr>
          <td align="right" nowrap bgcolor="#FFFFFF">工作单位：</td>
          <td colspan="5" bgcolor="#FFFFFF"><input name="workspace" type="text" id="textarea6"  size="65" class="pi"></td>
          <td align="right" bgcolor="#FFFFFF">职业：</td>
          <td bgcolor="#FFFFFF"><input name="occupation" type="text" id="textarea7"  size="15" class="pi"></td>
        </tr>
        <tr>
          <td align="right" nowrap bgcolor="#FFFFFF">合同号：</td>
          <td colspan="5" bgcolor="#FFFFFF"><input name="contractid" type="text" id="textarea8"  size="65" class="pi"></td>
          <td align="right" bgcolor="#FFFFFF">合同期限：</td>
          <td bgcolor="#FFFFFF"><input name="contractdate" type="text" id="textarea9"  size="15" class="pi"></td>
        </tr>
        <tr>
          <td align="right" nowrap bgcolor="#FFFFFF">联系电话：</td>
          <td align="center" bgcolor="#FFFFFF">手机</td>
          <td colspan="4" bgcolor="#FFFFFF"><input name="telphone" type="text" id="textarea10" size="44" class="pi"></td>
          <td align="right" bgcolor="#FFFFFF">住宅：</td>
          <td bgcolor="#FFFFFF"><input name="address1" type="text" id="textarea11." size="15" class="pi"></td>
        </tr>
        <tr>
          <td align="right" nowrap bgcolor="#FFFFFF">户口所在地：</td>
          <td colspan="7" bgcolor="#FFFFFF"><input name="hkszd" type="text" id="textarea12"  size="65" class="pi"></td>
        </tr>
        <tr>
          <td colspan="2" align="right" bgcolor="#FFFFFF">服务处所（地址）：</td>
          <td colspan="6" bgcolor="#FFFFFF"><input name="address" type="text" id="textarea13"  size="44" class="pi"></td>
        </tr>
        <tr>
          <td rowspan="6" align="center" bgcolor="#FFFFFF">家<br>
          庭<br>
          资<br>
          料</td>
          <td align="right" bgcolor="#FFFFFF">居住地址：</td>
          <td colspan="6" align="left" bgcolor="#FFFFFF"><input name="home" type="text" id="textarea14" size="44" class="pi"></td>
        </tr>
        <tr>
          <td align="right" bgcolor="#FFFFFF">家庭人数：</td>
          <td colspan="6" bgcolor="#FFFFFF"><input name="jtrs" type="text" id="textarea15" size="44" class="pi"></td>
        </tr>
        <tr>
          <td align="right" bgcolor="#FFFFFF">服务内容：</td>
          <td colspan="6" bgcolor="#FFFFFF"><input name="fwnr" type="text" id="textarea16"  size="44" class="pi"></td>
        </tr>
        <tr>
          <td align="right" bgcolor="#FFFFFF">房屋面积：</td>
          <td colspan="6" bgcolor="#FFFFFF"><input name="fwmj" type="text" id="textarea17" size="44" class="pi"></td>
        </tr>
        <tr>
          <td align="right" bgcolor="#FFFFFF">饮食习惯：</td>
          <td colspan="6" bgcolor="#FFFFFF"><input name="ysxg" type="text" id="textarea18" size="44" class="pi"></td>
        </tr>
        <tr>
          <td align="right" bgcolor="#FFFFFF">其他：</td>
          <td colspan="6" bgcolor="#FFFFFF"><input name="qita" type="text" id="textarea19" size="44" class="pi"></td>
        </tr>
         <tr>
          <td align="right" nowrap bgcolor="#FFFFFF">接受工资：</td>
          <td colspan="2" align="left" bgcolor="#FFFFFF">从
            <input name="minprice" type="text" id="textarea11" size="8" class="pi">
          到
          <input name="maxprice" type="text" id="textarea23" size="8" class="pi"></td>
          <td align="left" bgcolor="#FFFFFF">&nbsp;</td>
          <td align="right" bgcolor="#FFFFFF">&nbsp;</td>
          <td colspan="3" bgcolor="#FFFFFF">&nbsp;</td>
         </tr>s
           <tr>
          <td align="right" nowrap bgcolor="#FFFFFF">雇用：</td>
          <td colspan="7" bgcolor="#FFFFFF"><input type="checkbox" name="workertype" />
            月嫂&nbsp;
            <input type="checkbox" name="workertype"  />
            保姆&nbsp;&nbsp;
            <input type="checkbox" name="workertype"  />
            钟点&nbsp;&nbsp;
            <input type="checkbox" name="workertype"  />
            厨嫂
            <input type="checkbox" name="workertype" />
            清洁工
            &nbsp;
            <input type="checkbox" name="workertype" />
             护工&nbsp;&nbsp;
             <input type="checkbox" name="workertype"/>
             早教
            &nbsp;&nbsp;
            <input type="checkbox" name="workertype"  />
            家教
            &nbsp;&nbsp;
            <input type="checkbox" name="workertype" />
            高级管家<br>
          </td>
        </tr>
        <tr>
          <td align="right" nowrap bgcolor="#FFFFFF">经办人：</td>
          <td colspan="7" align="left" bgcolor="#FFFFFF"><input name="chargeman" type="text" id="textarea21" value="" size="65" class="pi"></td>
        </tr>
        <tr>
          <td align="right" nowrap bgcolor="#FFFFFF">登记日期：</td>
          <td colspan="7" align="left" bgcolor="#FFFFFF"><input name="inputtime" type="text" id="textarea22" value="" size="65" class="pi"></td>
        </tr>
      </table>
    <table width="96%" height="30" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td width="67%" align="center" class="text" nowrap>
      <input type="submit" value="保  存" class="button_new">
      <input type="button" value="返  回" class="button_new"onClick="javascript:window.history.back()" >

    &nbsp;&nbsp; </td>
  </tr>
</table>
</form>
</body>
</html>
