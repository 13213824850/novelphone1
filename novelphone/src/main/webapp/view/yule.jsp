<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("baseUrl", request.getContextPath()+"/");	%>
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

<body>

	<div class="container">
		

		<div class="row">
			<div class="col-sm-offset-1 col-xs-offset-1">
				<h3>娱乐功能:</h3>
				<br>

				<div id="voice">
				<div class="row">
					<!-- <h4 id="vedio" style="float: left">语音合成</h4> -->
					<div class="col-xs-12" ">
							 <textarea cols="40" rows="8" placeholder="字数不能超过512" name="body" id="inputzi"></textarea>
						<span id="zi"></span>
					</div>
					<div class="col-xs-2">
					<audio id="yulevedio" src="" autoplay="autoplay" controls="controls"></audio><br>
						<a href="#" id="dowloadyi">下载</a>
						
					</div>
              </div>

					<div>
						发音人选择: <input type="radio" value="0" name="people"
							checked="checked" /> 女声 <input type="radio" value="1"
							name="people" /> 男声 <input type="radio" value="3" name="people" />
						情感-度逍遥 <input type="radio" value="4" name="people" /> 情感-度丫丫
					</div>
					<br>
					<br>

					<div style="float: left">
						语速：<span class="glyphicon glyphicon-plus jia"></span>
					</div>
					<div class="progress  progress-striped active"
						style="width: 20%; float: left">
						<div class="progress-bar progress-bar-warning" role="progressbar"
							aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
							style="width: 50%;">
							<span id="spd">5</span>
						</div>
					</div>
					<span class="glyphicon glyphicon-minus jian" ></span>


					<hr>
					<div style="float: left">
						音调：<span class="glyphicon glyphicon-plus jia"></span>
					</div>
					<div class="progress  progress-striped active"
						style="width: 20%; float: left">
						<div class="progress-bar progress-bar-warning" role="progressbar"
							aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
							style="width: 50%;">
							<span id="pit">5</span>
						</div>
					</div>
					<span class="glyphicon glyphicon-minus jian" ></span>

					<hr>
					<div style="float: left">
						音量：<span class="glyphicon glyphicon-plus jia"></span>
					</div>
					<div class="progress  progress-striped active"
						style="width: 20%; float: left">
						<div class="progress-bar progress-bar-warning" role="progressbar"
							aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
							style="width: 50%;">
							<span id="vol">5</span>
						</div>
					</div>
					<span  class="glyphicon glyphicon-minus jian" ></span>
                      


				</div>
				<div class="col-xs-offset-1 col-xs-5">
				<button class="button btn-success" id="send">语音合成</button>
				
				</div>

			</div>

		</div>

	</div>

<input type="hidden" id="yuyingurl" />

	<script type="text/javascript">
		var APP_PATH = "${baseUrl}";
		var APP_PATHVEDIO = APP_PATH +"upload/yuleVedio/"
		
		//开始合成语音
		$("#send").click(function(){
			var message = $("#inputzi").val();
			if(message.length>=512){
				alert("字数超过512");
				return false;
			}
			$("#send").prop('disabled',true);
			var per =  $("input[name='people']:checked").val();
			$.ajax({
				url:APP_PATH+"vedio/yuying.action",
				data:"message="+message+"&&vol="+$("#vol").text()+"&&spd="+$("#spd").text()+"&&pit="+$("#pit").text()
				+"&&per="+per,
				type:"POST",
				success:function(e){
					$("#send").removeAttr('disabled');
					if(e.code==200){
						$("#yulevedio").attr('src',APP_PATHVEDIO+e.extend.url);
						$("#yuyingurl").attr("value",e.extend.url);
					}else if(e.code==400){
						alert(e.msg);
					}
				}
			});
		});
		
		//下载
		$("#dowloadyi").click(function(){
			var filename = $("#yuyingurl").val();
			if(filename==null||filename==''){
				return false;
			}
			window.open(APP_PATH+'vedio/dowloadyu.action?url='+filename);
		});
		
		
		//统计字数
		$("#inputzi").bind('input propertychange',function(){
			var length = $(this).val().length;
			if(length>=512){
				var message = $("#inputzi").val().substr(0,512);
				$("#inputzi").text(message);
				length =$(this).val().length; 
			}
			$("#zi").text('');
			$("#zi").append('当前字数为'+length);
		});
		
		
		

		//调节进度条值
			
		$(".jia").click(function(e){
			//当前的进度
			var pre = $(this).parent().next().children().width();
			//总宽度
			var width = $(this).parent().next().width();
			var i = width/10;
			var widthlv =decimal((pre+i)/width);
			if(widthlv*10>10){
				return false;
			}
			$(this).parent().next().children().css('width',widthlv*100+"%");
			$(this).parent().next().children().children().text(widthlv*10); 
		});
		
		//减
		$(".jian").click(function(){
			var div1 = $(this).prev();
			var width = div1.width();
			var next = div1.children().width();
			var i = width/10;
			var widthlv =decimal((next-i)/width);
			if(widthlv<0){
				return false;
			}
			div1.children().children().text(widthlv*10);
			div1.children().css('width',widthlv*100+'%') 
		});
		
		
		//四色五入
		function decimal(num){
			var vv = Math.pow(10,1);
			return Math.round(num*vv)/vv;
			}
		//cookie写入
		// var username=document.cookie.split(";")[0].split("=")[1];
		//JS操作cookies方法!
		//写cookies
	/* 	function setCookie(name, value) {
			var Days = 365;
			var exp = new Date();
			exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
			document.cookie = name + "=" + escape(value) + ";expires="
					+ exp.toGMTString();
		}

		//读取cookie
		function getCookie(name) {
			var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
			if (arr = document.cookie.match(reg))
				return unescape(arr[2]);
			else
				return null;
		}
		function clearCookie(name) {
			var exp = new Date();
			exp.setTime(exp.getTime() - 1);
			var cval = getCookie(name);
			if (cval != null)
				document.cookie = name + "=" + cval + ";expires="
						+ exp.toGMTString();
		}
		//正则获取传递的参数
		function GetQueryString(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		} */
	</script>

</body>
</html>