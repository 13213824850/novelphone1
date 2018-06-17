<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
	 request.setAttribute("baseUrl", request.getContextPath()+"/");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="${baseUrl }static/js/jquery-3.3.1.min.js"></script>
<script src="${baseUrl }static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${baseUrl }static/js/jquery.cookie.js"></script>
<link href="${baseUrl }static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<title>小说</title>
</head>
<body>
	<div class="container">
		<div class="row">
		<div class="col-xs-2"><span class="glyphicon glyphicon-chevron-left btn-lg"  data-toggle="tooltip" data-placement="right" title="返回" id="gohistory"></span></div>
		<div class="col-xs-10">
		<ul class="nav nav-pills">
				<li role="presentation btn-xs"  ><a id="index" href="#">我的书架</a></li>
					<li role="presentation" ><a href="${baseUrl }view/recommendNovel.jsp">推荐</a></li>
					<li role="presentation" ><a href="${baseUrl }view/sortnovels.jsp">分类</a></li>
					<li role="presentation" ><a href="${baseUrl }view/novels.jsp">搜索</a></li>
					</ul>
		
		</div>
		</div>
		<div class="row">
			<div class="col-xs-4 col-xs-offset-1 ">
				<img src="${novel.image }" class="img-responsive" alt="Responsive image"></img>
			</div>

			<div class=" col-xs-6">
				<br> 书名: ${novel.title }<br> 
				作者: ${novel.auth }<br>
				提供者:${novel.product }<br>
				来源:${novel.source }<br>

			</div>
		</div>
		<div class="row" style="margin: 5%;">
			<!-- 简介 -->
			小说简介：${novel.body }
		</div>
		<div class="row">
			<div class="col-xs-offset-1">
				<button class="btn btn-success" id="readStart" type="button">开始阅读</button> 
				<button class="btn btn-info" id="joinReadRecord" type="button">加入书架</button>
				<button class="btn btn-danger btn-sm" id="report" type="button">侵权举报</button>
			</div>
		</div>
	</div>

	<input type="hidden" id="msg" value="${msg==null?0:msg }">
	<input type="hidden" id="contentid"
		value="${novel.contentid==null?0:novel.contentid }" />
	<input type="hidden" id="novelid" value="${novel.id }" />
	<input type="hidden" id="title" value="${novel.title }" />
	<script type="text/javascript">
		var baseUrl = "${baseUrl }";
		//获取传力啊的novelid

		//获取用户
		var user = $.cookie("user");
		var userId =0;
		var nickName = null;
		if(user!=null||user!=''){
			user = JSON.parse(user);
			userId = user.id;
			nickName = user.nickname;
		}
		
		$("#index").click(function(){
			var userid = user.id;
			var url = baseUrl + "novel/getReadRecord.html?userid="+userid;
			window.open(url,'_self');
		});
		
		
		//开始阅读
		$("#readStart").click(function() {
			readStart();
		});
		var msg = $("#msg").val();
		if (msg !=0) {
			alert(msg);
		}
		//加入书架
		$("#joinReadRecord").click(function() {
			joinReadRecord();
		});

		function readStart() {
			var contentid = $("#contentid").val();
			var novelid = $("#novelid").val();
			//判断是否已经加载就绪
			$.ajax({
				url:"${baseUrl}novel/readNovel.action",
			    data:"novelid="+novelid,
			    type:"POST",
			    success:function(e){
			    	if(e.code==200){
			    		window.open('${baseUrl}view/read.jsp?novelid='+novelid, "_self");
			    	}else if(e.code==400){
			    		alert(e.msg);
			    	}
			    }
			});
		}
		function joinReadRecord() {
			//判断是否登录
			if(userId==0){
				if(confirm("未登录无法举报请登录")){
					window.open(baseUrl+'login.jsp');
				}else{
					$("#reportModal").modal('hide');
				}
			}
			
			var novelid = $("#novelid").val();
			$.ajax({
				url : baseUrl + "read/addRead.action",
				data : "userid=" + userId + "&&novelid=" + novelid,
				type : "POST",
				success : function(e) {
					if (e.code == 200) {
						$("#joinReadRecord").empty();
						$("#joinReadRecord").text("已加入");
						$("#joinReadRecord").prop("disabled",'true');
					} else if (e.code == 400) {
						$("#joinReadRecord").text(e.msg);
						$("#joinReadRecord").prop("disabled",'true');
					}
				}
			});
		}
		
		//点击返回上一级
		$("#gohistory").click(function(){
			window.history.back(); 
		});
		
		//显示提示信息的
		$(function () {
			  $('[data-toggle="tooltip"]').tooltip()
			})
		
		//侵权举报
		$("#report").click(function(){
			var novelid = $("#novelid").val();
			var title = $("#title").val();
			report(novelid,title);
		});
		function report(novelid,title){
			var msg = "您确定举报书籍"+title+"?";
			/* if(!confirm(msg)){
				return false;
			} */
			
			//判断是否登录
			if(userId==0){
				if(confirm("未登录无法举报请登录")){
					window.open(baseUrl+'login.jsp');
				}else{
					$("#reportModal").modal('hide');
				}
			}
			
			
			//删除数据
			$("#reportForm")[0].reset();
			
			//开始举报填写举报信息
			$("#reportModal").modal('show');
			$("#NovelName").attr("value",title);
			$("#NovelName").attr("placeholder",title);
			
			//确定举报
			$("#reportSure").click(function(){
				//检查数据
				var jsondata = $("#reportForm").serializeArray();
				var reporttype = jsondata[0].value;
				var reason = jsondata[1].value;
				var contactType = jsondata[2].value;
				var contact = jsondata[3].value;
				if((title==null||novelid=='')){
					alert("出错了");
				}else{
					$.ajax({
						url:baseUrl+"report/addReport.action",
						data:$("#reportForm").serialize()+"&&novelid="+novelid+"&&novelname="+title+"&&userid="+userId+"&&nickName="+nickName,
						type:"POST",
						success:function(e){
							if(e.code==200){
								alert('举报成功');
							}else if(e.code==400){
								alert(e.msg);
							}
							$("#reportModal").modal('hide');
						}
					});
				}
				
			});

			
		}
		function getUser(){
			var user = $.cookie('user');
			if(user==null||user==''||user=='undefine'){
				if(confirm("还未登录你要登录吗？")){
					window.open(baseUrl+'view/login.jsp','_self');
				}
			}
			return JSON.parse(user);
		}
        //正则获取传递的参数
        function GetQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null)
                return unescape(r[2]);
            return null;
        }
		
		
		
		
	</script>
	
	<!-- 书籍举报 -->
	<div class="modal fade" tabindex="-1" role="dialog" id="reportModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">书籍举报</h4>
      </div>
      <div class="modal-body">
        
        
        
        <form class="form-horizontal" id="reportForm">
  <div class="form-group">
    <div class="col-sm-3">
      <input type="text" class="form-control" id="NovelName" name="NovelName" disabled="disabled" >
    </div>
  </div>
  
  <div class="form-group">
  <label class="radio-inline">
    类型：
</label>
  <label class="radio-inline">
  <input type="radio" name="reporttype" class="type" value="侵权"> 侵权
</label>
<label class="radio-inline">
  <input type="radio" name="reporttype" class="type" value="涉黄、暴力"> 涉黄、暴力
</label>
<label class="radio-inline">
  <input type="radio" name="reporttype" class="type" value="其他"> 其他
</label>
  </div>

<div class="form-group">
    <div class="col-sm-10">
      <textarea cols="40" rows="3" placeholder="请输入原因、留言或者其他" name="reason" id="reason"></textarea>
    </div>
  </div>
  
<div class="form-group">
  <label class="radio-inline">
    联系方式：
</label>
  <label class="radio-inline">
  <input type="radio" name="contacttype" id="inlineRadio1" class="contactType" value="QQ" > QQ
</label>
<label class="radio-inline">
  <input type="radio" name="contacttype" id="inlineRadio2" class="contactType" value="手机" > 手机
</label>
<label class="radio-inline">
  <input type="radio" name="contacttype" id="inlineRadio3" class="contactType" value="邮箱" > 邮箱
</label>
</div>

<div class="form-group">
    <div class="col-sm-10">
<input type="email" class="form-control col-sm-offset-3" name="contact" id="contact" placeholder="请输入联系方式" >
    </div>
  </div>

</form>

    
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" id=reportSure>确定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>
