<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("baseUrl", request.getContextPath()+"/");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript"
	src="${baseUrl }static/js/jquery.cookie.js"></script>
<script type="text/javascript"
	src="${baseUrl }static/js/jquery.validate.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>

<style type="text/css">
.error {
	color: red;
	
}
</style>
</head>
<body>


	<div data-role="body">
	
	<div style="text-align: center;"><h3>登录</h3></div>
	
	
	</div>

	<form action="${baseUrl }user/login.html" id="loginform">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="name" id="name"
					value="" />
					<label class="error" for="name" generated="true" ></label>
					</td>
					
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" id="password"
					value="" />
					<label class="error" for="password" generated="true" ></label>
					</td>
			</tr>
			<tr>
				<td><a id="register">一键注册</a></td>
				<td><input type="submit" value="登录" id="sub" /></td>
			</tr>
			<tr>
				<td id="msg">${msg }</td>
			</tr>

		</table>

	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			var user = $.cookie('user');
			if (user != null) {
				user = JSON.parse(user);
				$("#name").attr('value', user.name);
				$("#name").attr('placeholder', user.name);
				$("#password").attr('value', user.password);
				$("#password").attr('placeholder', user.password);

				//$("#loginform").submit();
				window.open("${baseUrl }user/login.html?name="+user.name+"&&password="+user.password,"_self");

			}

			//表单验证
			$("#loginform").validate({
				rules : {
					name : {
						required : true,
						rangelength : [ 6, 10 ],
					},
					password : {
						required : true,
						rangelength : [ 6, 10 ]
					}
				},
				messages : {
					name : {
						required : "不能为空",
						rangelength : "账号必须为6-10位字符"
					},
					password : {
						required : "不能为空",
						rangelength : "密码必须为6-10位字符"
					}
				},
				submitHandler : function(form) {
					form.submit();
				}
			});

			$("#register").click(function() {
				window.open("${baseUrl }user/getNamePassword.html", '_self');
			});
		});

		/* if(user!=null){
			login();
		}

		function login(){
			$("#name").attr("value",user.name);
			$("#password").attr("value",user.password);
			$("#loginform").submit();
			
		} */
	</script>
</body>
</html>