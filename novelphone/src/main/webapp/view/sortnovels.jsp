<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
		request.setAttribute("baseUrl", request.getContextPath()+"/");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小说分类</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<link rel="stylesheet"
	href="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">

<script type="text/javascript"
	src="${baseUrl }static/js/jquery.cookie.js"></script>
	<style type="text/css">
	.s{
	height: 80px;
	width: 40%;
	background: white;
	margin: 20px;
	text-align: center;
	margin-top: 20px;
	}
	span{
	text-align: center;
	}
	
	</style>
</head>

<body>
	<div data-role="page" id="pageone">
		<div data-role="navbar" id="head">
			<ul>
				<li><a href="#" id="index" data-icon="home">我的书架</a></li>
				<li><a href="#" data-icon="arrow-r">推荐</a></li>
			</ul>
		</div>
		 <div data-role="main" class="ui-content">
		     <div class="ui-grid-b">
		      <div class="ui-block-a s" ><span>玄幻奇幻</span><input type="hidden"  value="xhqh" /></div>
		      <div class="ui-block-b s" ><span>武侠仙侠</span><input type="hidden"  value="wxxx" /></div>
		      <div class="ui-block-a s"><span>都市言情</span><input type="hidden"  value="dsyq" /></div>
		      <div class="ui-block-b s"><span>历史军事</span><input type="hidden"  value="lsjs" /></div>
		      <div class="ui-block-a s"><span>科幻灵异</span><input type="hidden"  value="khly" /></div>
		      <div class="ui-block-b s"><span>网游竞技</span><input type="hidden"  value="wyjj" /></div>
		      <div class="ui-block-a s"><span>女生频道</span><input type="hidden"  value="nspd" /></div>
		    </div>
            </div>
	</div>
	
</body>
<script type="text/javascript">
	$(document).ready(function() {
 
		var  baseUrl = "${baseUrl}";
		
		
		
		$(".s").click(function(){
			var type = $(this).children().next().val();
			window.open(baseUrl+'view/recommendNovel.jsp?type='+type,'_self');
		});
		
		$("#index").click(function() {
			var user = getUser();
			userid = JSON.parse(user).id;
			var url = baseUrl + "novel/getReadRecord.html?userid=" + userid;
			window.open(url, '_self');
		});

		function getUser() {
			var user = $.cookie('user');
			if (user == null) {
				window.open(baseUrl + 'login.jsp', '_self');
			} else {
				return user;
			}
		}

	});
</script>
</html>