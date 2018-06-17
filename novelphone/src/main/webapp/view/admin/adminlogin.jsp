<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
		<%
            request.setAttribute("baseUrl", request.getContextPath()+"/");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台登录</title>

<script type="text/javascript"
	src="${baseUrl }static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="${baseUrl }static/js/jquery.cookie.js"></script>
<link
	href="${baseUrl }static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">

<script src="${baseUrl }static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">

		<div class="row"></div>
		<div class="row">
		<form class="form-horizontal" action="${baseUrl }user/loginadmin.html">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">账号</label>
    <div class="col-sm-3">
      <input type="text" class="form-control" name="name" placeholder="账号">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-3">
      <input type="password" class="form-control" name="password" placeholder="密码">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default"> 登录</button>
    </div>
  </div>
</form>



		</div>

	</div>
</body>
</html>