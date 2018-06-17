<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("baseUrl", request.getContextPath()+"/");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>

<script type="text/javascript"
	src="${baseUrl }static/js/jquery-1.12.4.min.js"></script>

<script type="text/javascript"
	src="${baseUrl }static/js/jquery.cookie.js"></script>

<script src="${baseUrl }static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<link
	href="${baseUrl }static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${baseUrl }static/js/jquery.validate.min.js"></script>

<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body>
	<div class="container">


		<div class="row">

			<div style="text-align: center;" class="col-xs-12">
				<h3>用户注册</h3>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-offset-2 col-xs-8">

				<form id="registerForm">
					<div class="form-group">
						<label for="exampleInputEmail1">用户名</label> <input type="text"
							class="form-control" id="name" value="${user.name }" name="name"
							placeholder="用户名">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">昵称</label> <input type="text"
							class="form-control" id="nickname" value="${user.nickname }"
							name="nickname" placeholder="昵称">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">密码</label> <input type="text"
							class="form-control" id="password" value="${user.password }"
							name="password" placeholder="密码">
					</div>

				</form>
				<button class="btn btn-default" id="login">已有账号登录</button>
				<button class="btn btn-default" id="registersure">注册</button>

			</div>

		</div>

	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#login").click(function() {
				window.open("${baseUrl}view/login.jsp", '_self');
			});

			//表单验证
			
				$("#registerForm").validate({
					  debug:true,
					rules : {
						name : {
							required : true,
							rangelength : [ 6, 10 ]
						},
						nickname : {
							required : true,
							rangelength : [ 6, 8 ]
						},
						password : {
							required : true,
							rangelength : [ 6, 10 ]
						},

					},
					messages : {
						name : {
							required : "账号不能为空",
							rangelength : "账号为6-10位"
						},
						nickname : {
							required : "昵称不能为空",
							rangelength : "昵称必须为6-8位字符组成"
						},
						password : {
							required : "密码不能为空",
							rangelength : "密码必须为6-10位字符 "
						},
					},
				});

			$("#registersure").click(function() {
				if($("#registerForm").valid()){
				 	$.post("${baseUrl}user/registersure.action",$("#registerForm").serialize(),function(e){
					if(e.code==200){
						window.open("${baseUrl}view/login.jsp",'_self');
					}else if(e.code==400){
						alert(e.msg);
					}
				});
				}
			});

		});
	</script>
</body>
</html>