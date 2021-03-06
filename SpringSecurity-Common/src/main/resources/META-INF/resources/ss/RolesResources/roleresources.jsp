<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@page import="com.byron.ss.model.*" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<link href="<c:url value="/styles/public.css"/>" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=basePath%>scripts/public.js"></script>
<script type="text/javascript" >
		function doroles(roleid) {
			//$$("roleid").value=roleid;
			$("#roleid").val(roleid);
			
			var url = "${ctx}/ss/RolesResources/initPage.do?roleid=" + roleid + "";
			window.location.href=url;
		}
		
		function relax(resourceid) {
			var roleid = $("#roleid").val();
			var url = "${ctx}/ss/RolesResources/doDelete.do?roleid=" + roleid + "&resourceid=" + resourceid + "";
			window.location.href=url;
		}
	</script>
  </head>
  
  <body>
  		<div class="tags_bg">
	  		<c:forEach items="${roles}" var="item" varStatus="status">
	  			<div onclick="doroles('${item.id}')" class="tags_but"  <c:if test="${item.id==role.id }"> style="background:#eee;color:#fff;" </c:if>>${item.name }</div>
	  		</c:forEach>
  		</div>
  		
  		<div style="padding:10px;"></div>
  		
  		<div>
  			<div style="display:inline;" onclick=""><a href="${ctx }/ss/RolesResources/doSavePage.do?roleid=${role.id}">分配资源</a></div>
  		</div>
  		
  		<input type="hidden" value="${role.id }" name="roleid" id="roleid"/>
  		
     	<div>
	     	<table border="1">
	     		<tr><td width="200">资源名</td><td width="400">url</td><td width="200">操作</td></tr>
		     	<c:forEach items="${resources}" var="item" varStatus="status">
		     		<tr><td>${item.name }</td><td>${item.url }</td><td><a href="javascript:void(0)" onclick="relax('${item.id}')">解除关系</a></td></tr>
		     	</c:forEach>
	     	</table>
     	</div>
  </body>
</html>
