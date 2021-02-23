<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<script type="text/javascript" src="<%=path %>/jsxx/jsxxBus.js"></script>
        <script language="javascript">
           function xiangmuDele(id)
           {
               if(confirm('您确定删除吗?'))
               {
                   window.location.href="<%=path %>/xiangmu?type=xiangmuDel&id="+id;
               }
           }
           function xiangmuAdd()
           {
                 var url="<%=path %>/admin/xiangmu/xiangmuAdd.jsp";
				 window.location.href=url;
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/1.jpg'>			
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="9" background="<%=path %>/img/tbg.gif">&nbsp;项目信息管理&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td>名称</td>
					<td>立项时间</td>
					<td>负责人</td>
					<td>操作</td>
		        </tr>	
				<c:forEach items="${requestScope.xiangmuList}" var="xiangmu">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${xiangmu.mingcheng}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${xiangmu.shijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${xiangmu.fuzeren}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						    <input type="button" onclick="xiangmuDele(${xiangmu.id})" value="删除"/>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="添加" style="width: 80px;" onclick="xiangmuAdd()" />
			    </td>
			  </tr>
		    </table>
		    
	</body>
</html>
