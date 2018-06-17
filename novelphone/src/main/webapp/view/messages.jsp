<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%
	 request.setAttribute("baseUrl", request.getContextPath()+"/");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="${baseUrl }static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="${baseUrl }static/js/jquery.cookie.js"></script>

<script src="${baseUrl }static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link
	href="${baseUrl }static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">

<title>消息中心</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-2">
				<span class="glyphicon glyphicon-chevron-left btn-lg"
					data-toggle="tooltip" data-placement="right" title="返回"
					id="gohistory"></span>
			</div>
			<div class="col-xs-10">
				<ul class="nav nav-pills">
					<li role="presentation btn-xs"><a href="#">我的书架</a></li>
					<li role="presentation"><a
						href="${baseUrl }view/recommendNovel.jsp">推荐</a></li>
					<li role="presentation"><a
						href="${baseUrl }view/sortnovels.jsp">分类</a></li>
				</ul>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12">

				<table class="table table-hover">

					<thead>
						<th>标题</th>
						<th>内容</th>

					</thead>

					<tbody id="tbody" ></tbody>

				</table>

			</div>
		</div>

	</div>

	<script type="text/javascript">
		$(document).ready(
				function() {
					var user = getUser();
					var baseUrl = "${baseUrl }";
					//查询消息记录数
					$.ajax({
						url : baseUrl + 'message/getMessages.action',
						data : 'userid=' + user.id + "&&state=" + 2,
						type : "POST",
						success : function(e) {
							$("#usermessage").children().empty();
							if (e.code == 200) {
								showMessages(e.extend.messages);
							} else if (e.code == 400) {
								$("#usermessage").children().append(0);
							}
						}
					});

					//显示消息
					function showMessages(e) {
						var span1 = $("<span><span>").addClass("glyphicon glyphicon-envelope");
						$.each(e, function(index, message) {
							var color = 'red';
							if(message.state==1){
								color = "#4D4D4D";
							}
						
							var td1 = $("<td></td>").append(span1).append(" "+message.title.substring(0,message.title.length>=4?4:message.title.length)+'...');
							var td2 = $("<td></td>").append(message.body.substring(0,message.body.length>=11?11:message.body.length)+'...');
							var tr = $("<tr></tr>").append(td1).append(td2).css('color',color);
								$("#tbody").append(tr);
								tr.click(function(){
									//显示详细message
									showdetail(message);
								});
						});
					}
                    
					
					function showdetail(e){
						var message = JSON.stringify(e);
						message = encodeURI(message);
						window.open(baseUrl+'view/message.jsp?message='+message,'_self');
					}
					
					//点击返回上一级
					$("#gohistory").click(function() {
						window.history.back();
					});

					function getUser() {
						var user = $.cookie('user');
						if (user == null || user == '' || user == 'undefine') {
							if (confirm("还未登录你要登录吗？")) {
								window.open(baseUrl + 'login.jsp', '_self');
							}
						}
						return JSON.parse(user);
					}
				});
	</script>
</body>
</html>