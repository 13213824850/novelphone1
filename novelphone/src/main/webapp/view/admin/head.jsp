<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("baseUrl", request.getContextPath()+"/");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${baseUrl }static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="${baseUrl }static/js/jquery.cookie.js"></script>

<script src="${baseUrl }static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link
	href="${baseUrl }static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
</head>
<body>
	<div class="row">

		<div class="col-sm-offset-3 col-sm-6"></div>
		<div class="col-sm-3">
			用户名：<a id="nickname"></a>&nbsp;&nbsp; 权限：<a id="power"></a>&nbsp;&nbsp;<a
				href="#">注销</a>
		</div>
	</div>

	<div>
		<h3 style="text-align: center; color: blue">小说网站后台管理</h3>

	</div>


	<div class="row">

		<div class="col-sm-12">

			<ul class="nav nav-pills" style="font-size: 20px;">
				<li role="presentation" id="yh"><a
					href="${baseUrl }/view/admin/title.jsp" class="li">用户管理</a></li>
				<li role="presentation" id="xs"><a
					href="${baseUrl }/view/admin/noveladmin.jsp" class="li">小说管理</a></li>
				<li role="presentation" id="ts"><a
					href="${baseUrl }/view/admin/reportadmin.jsp" class="li">投诉管理</a></li>
				<li role="presentation" id="yj"><a
					href="${baseUrl }/view/admin/feedbackadmin.jsp" class="li">意见反馈</a></li>
			</ul>
		</div>
	</div>

	<script type="text/javascript">
		var admin = $.cookie('admin');
		admin = JSON.parse(admin);
		$("#power").append(admin.power == 1 ? '管理员' : '普通用户');
		$("#nickname").append(admin.nickname);
	
	</script>
</body>
</html>