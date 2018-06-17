<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
        request.setAttribute("baseUrl", " http://gixf6t.natappfree.cc/novel1/");
%>
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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息</title>
</head>
<body>
<div data-role="page">

  <div data-role="header">
    <h1 id="title"></h1>
  </div>

  <div data-role="main" class="ui-content">
    内容：<p id="body"></p>
    <p >
  <div style="float: right">
   发送人：&nbsp;<a id="sendUser" style="color:#4D4D4D"></a>&nbsp;&nbsp;&nbsp;<br>
   发送时间：&nbsp;<a id="sendTime" style="color:#4D4D4D"></a><br>
   <button style="float: right" class="ui-btn ui-btn-inline ui-shadow ui-corner-all" id="delete" >删除</button>
  </div>
    
    </p>
  </div>

  <div data-role="footer">
    <h1>底部文本</h1>
  </div>

</div>
</body>

<script type="text/javascript">


$(document).ready(function(){
	
	var baseUrl = "${baseUrl}";
	
	//获取得到的参数
	var message = GetQueryString("message");
	message = JSON.parse(message);
	//开始赋值
	$("#title").empty();
	$("#title").append(message.title);
	$("#body").empty();
	$("#body").append(message.body);
	$("#sendUser").empty();
	$("#sendUser").append(message.sendname);
	$("#sendTime").empty();
	$("#sendTime").append(message.updatetime);
	
	//发送ajax请求使得消息状态为已读
	
	if(message.state==0){
		var url = baseUrl+'message/updateStateMessage.action';
		$.post(url,{'id':message.id,'saveid':message.saveid});
	}
	
	$("#delete").click(function(e){
		$.post(baseUrl+'message/deleteMessage.action',{'ids':message.id,'saveid':message.saveid},function(e){
			if(e.code==200){
				window.open(baseUrl+'view/messages','_self');
			}else if(e.code==400){
				alert(e.msg);
			}
		});
	});
	
	//正则获取传递的参数
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return decodeURI(r[2]);
		return null;
	}
});
</script>
</html>