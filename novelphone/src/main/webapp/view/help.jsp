<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("baseUrl", request.getContextPath()+"/");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript"
	src="${baseUrl }static/js/jquery-3.3.1.min.js"></script>
<script src="${baseUrl }static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${baseUrl }static/js/jquery.cookie.js"></script>
<link
	href="${baseUrl }static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">

<title>帮助中心</title>
</head>
<body>
	<div>

		<!-- Nav tabs -->
		<div class="container">
		<div class="row">
		
		<div class="col-xs-2"><span class="glyphicon glyphicon-chevron-left btn-lg"  data-toggle="tooltip" data-placement="right" title="返回" id="gohistory"></span></div>
		
		</div>
			<div class="row">
		
				<div class="col-xs-5">

					<ul class="nav nav-tabs" role="tablist" style="margin-top: 20px;">
						<li role="presentation" class="active"><a href="#home"
							aria-controls="home" role="tab" data-toggle="tab">页面排版</a></li>
						<br><br>
						<li role="presentation"><a href="#profile"
							aria-controls="profile" role="tab" data-toggle="tab"
							style="float: left;">小说侵权</a></li>
						<br><br>
						<li role="presentation"><a href="#messages"
							aria-controls="messages" role="tab" data-toggle="tab" style="float:left">联系客服</a></li>
						<br><br>
						<li role="presentation"><a href="#settings"
							aria-controls="settings" role="tab" data-toggle="tab"
							style="float: left">Settings</a></li>
						<br>
					</ul>
				</div>


				<div class="col-xs-7">

					<!-- Tab panes -->
					<div class="tab-content" style="margin-top: 40px;">
						<div role="tabpanel" class="tab-pane active" id="home">
						<p>
						为了更好地体验小说阅读，本网站采用的是翻页阅读 使用前关闭浏览器的左右翻页功能
						</p>
						
						</div>
						<div role="tabpanel" class="tab-pane" id="profile">
						
						<p>本网站小说全是由书名自主提供的搜索   可在小说介绍中看到提供者 如果侵权请点击举报  我们会在24小说内删除</p>
						</div>
						<div role="tabpanel" class="tab-pane" id="messages">联系qq：516449358</div>
						<div role="tabpanel" class="tab-pane" id="settings">...</div>
					</div>
				</div>

			</div>

		</div>



	</div>
	
	<script type="text/javascript">
	
	$('#myTabs a').click(function (e) {
		  e.preventDefault()
		  $(this).tab('show')
		})
		
			//点击返回上一级
		$("#gohistory").click(function(){
			window.history.back(); 
		});
		</script>
</body>
</html>