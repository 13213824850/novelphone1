<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	    		<%
					request.setAttribute("baseUrl", request.getContextPath()+"/");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>定时任务</title>
</head>
<body>
	<div class="container">

		<jsp:include page="head.jsp"></jsp:include>
		<hr>

		<div class="row">
		
		<div class="col-sm-offset-3 col-sm-5">
		
		<button class="btn btn-info" id="novelcount">小说统计</button>
		<button class="btn btn-success" id="start">小说开始定时更新</button>
		<button class="btn btn-danger" id="end" >小说暂停定时更新</button>
		</div>
		</div>
		<br>
		
		<div class="row">
		
		<div class="col-sm-offset-2 col-sm-8">
		<p id="show"></p>
		
		</div>
	</div>
	
	<script type="text/javascript">
	var baseUrl = "${baseUrl}";
	$(document).ready(function(){
		$("#xs").addClass("active");
		$("#novelcount").click(function(){
			$.get(baseUrl+'novel/novelcount.action',function(e){
				if(e.code==200){
					$("#show").empty();
					$("#show").append('本站共收录'+e.extend.novelcount+'本小说');
				}else if(e.code=400){
					alert(e.msg);
				}
				
			});
		});
		
		
		$("#start").click(function(){
			$.get(baseUrl+'novel/UpdateNovelStartAndStop.action?state=true',function(e){
				alert(e.msg);
			});
		});
		$("#end").click(function(){
			$.get(baseUrl+'novel/UpdateNovelStartAndStop.action?state=false',function(e){
				alert(e.msg);
			});
		});
		
	});
	
	</script>
	
	
</body>
</html>