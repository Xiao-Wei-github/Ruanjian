<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %> 

<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
        <link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
        <script type="text/javascript" src="<%=path %>/js/popup.js"></script>
        <script type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>
        <script language="javascript">
			function check(){
				if(document.formAdd.mingcheng.value == "")
				{
					alert("名称不能为空!");
					return false;
				}
				return true;
			}
			
            function openXiangmu()
            {
				var url="<%=path %>/xiangmu?type=xiangmuSele";
       	   		var ret = window.showModalDialog(url,"项目信息选择","dialogWidth=500px;dialogHeight=300px;status=yes;help=no;scroll=yes");
            	if(ret != null)
            	{
            		document.getElementById("xiangmu_id").value = ret[0];
            		document.getElementById("xmmc").value = ret[1];
            	}
            }			
			
		    function up()
		    {
		        var url="<%=path %>/upload/upload.jsp";
		        var pop=new Popup({ contentType:1,isReloadOnClose:false,width:400,height:200});
	            pop.setContent("contentUrl",url);
	            pop.setContent("title","文件上传");
	            pop.build();
	            pop.show();
		    }			
        </script>
	</head>

	<body leftmargin="2" topmargin="9" background='<%=path %>/img/1.jpg'>
			<form action="<%=path %>/chengguo?type=chengguoAdd" name="formAdd" method="post">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#EEF4EA">
					        <td colspan="3" background="<%=path %>/img/wbg.gif" class='title'><span>项目成果添加</span></td>
					    </tr>
						<tr align='center' bgcolor="#FFFFFF" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         项目信息：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
								<input type="text" id="xmmc" readonly="readonly" onclick="openXiangmu()"/>点击选择项目
								<input type="hidden" id="xiangmu_id" name="xiangmu_id"/>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         成果名称：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input type="text" name="mingcheng" size="20"/>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         成果描述：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
								 <textarea rows="5" cols="16" name="miaoshu"></textarea>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         产生成果时间：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input name="shijian" readonly="readonly" class="Wdate"  
						        	   type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
							</td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         附件：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input type="text" name="fujian" id="fujian" size="20" readonly="readonly"/>
						        <input type="button" value="上传" onclick="up()"/>
						        <input type="hidden" name="fujianYuanshiming" id="fujianYuanshiming"/>						    
						    </td>
						</tr>						
						<tr align='center' bgcolor="#FFFFFF" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        &nbsp;
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						       <input type="submit" value="提交" onclick="return check();"/> 
						       <input type="reset" value="返回" onclick="javascript:history.back()"/>&nbsp;
						    </td>
						</tr>
					 </table>
			</form>
   </body>
</html>
