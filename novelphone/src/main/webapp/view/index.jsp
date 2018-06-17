<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("baseUrl", request.getContextPath()+"/");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script> -->

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>

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

#fontgen{
position:absolute;
left:5%;
top:0px;
color: red;
font-size: 30px;
font-family:serif;
font-style:italic;
font-weight: bold;
}
</style>

</head>
<body>

	<div class="container " id="body">
		<div style="text-align: center;color: black;"><a href="#">公告：本软件仅供交流使用</a></div>
		<!-- 搜索框 -->
		<div style="float: right" id="searchNovel" hidden="hidden"
			class="col-xs-12">
			<input type="text" name="novelName" id="novelName"
				placeholder="作者、小说名" /> <span id="getNovels"
				class="glyphicon glyphicon-search btn-lg"></span>
		</div>

		<div id="infor">
			<div class="row">
				<div class="col-xs-11">
					<span class="glyphicon glyphicon-user btn-lg" id="userspan"></span>
					<!-- 搜索按钮 -->
					<div style="float: right">
						<span id="search" class="glyphicon glyphicon-search btn-lg"></span>
					</div>

					<br>
				</div>
			</div>

			<ul class="nav nav-pills" style="background-color:gainsboro;font-size: 15px">
				<li role="presentation btn-xs" class="active"><a href="#">我的书架</a></li>
					<li role="presentation" ><a href="${baseUrl }view/recommendNovel.jsp">推荐</a></li>
					<li role="presentation" ><a href="${baseUrl }view/sortnovels.jsp">分类</a></li>
					</ul>
			<hr>
			<!-- 书架不为空 -->
			<c:if test="${requestScope.userNovels!=null }">
				<c:forEach items="${requestScope.userNovels }" var="userNovel">
					<div class="itemBook">
						<div class="row">
							<input type="hidden" id="bookid" value="${userNovel.id} " />
							<input type="hidden" id="isupdate" value="${userNovel.isupdate} " />
							<div class="col-xs-4 ">
								<img src="${userNovel.image }" class="img-responsive" alt="Responsive image">
								<!-- 判断是否更新 -->
								<c:if test="${userNovel.isupdate==1 }"><div  id="fontgen" class="col-xs-offset-1">更</div></c:if>
								
								</img>
							</div>

							<div class=" col-xs-7">
								<br> 书名: ${userNovel.title }<br> 作者: ${userNovel.auth }<br>
								最新章节:<a href="${userNovel.newchapterurl }" class="newChapter">${userNovel.newchapter }</a>
								<input type="hidden" value="${userNovel.newchapterurl }">
							</div>
						</div>
					</div>
					<br>
				</c:forEach>
			</c:if>
			<c:if test="${requestScope.userNovels==null }">
				<h4>书架空空如也</h4>
			</c:if>
		</div>
	</div>

<div id="userinflist" style="width: 40%;background-color:#CCCCCC; " hidden>

<div id="userhide"><span class="glyphicon glyphicon-user btn-lg" id="nickname" style="margin-top: 30px;overflow: hidden;"></span></div>
<ul class="nav nav-pills nav-stacked ">
<%--  <li role="presentation" ><a href="${baseUrl }/view/yule.jsp" >语音合成</a></li>--%>
  <li role="presentation" ><a href="${baseUrl }/view/messages.jsp" id="usermessage">消息<span class="badge"></span></a></li>
  <%-- <li role="presentation" ><a href="${baseUrl }/view/recommendNovel.jsp?foot=foot" id="footprint">我的足迹</a></li> --%>
  <li role="presentation" ><a href="#" id="feedback">意见反馈</a></li>
  <li role="presentation" ><a href="${baseUrl }/view/help.jsp">帮助</a></li>
</ul>

</div>

	<input type="hidden" id="bookid" value="" />
	<script type="text/javascript">
		$(document).ready(function() {
					var baseUrl = "${baseUrl }";
					
					var isupdate = 0;//判断是否更新
					$("#search").click(function() {
						window.open(baseUrl + "novel/searchjump.html",'_self');
					});

					//点击阅读
					$(".itemBook").click(function() {
						var bookid = $(this).children().children().val();
					   isupdate = $(this).children().children().next().val();
						
						readBook(bookid, 0);
					});
					//最新章节阅读
					$(".newChapter").click(function() {
						var bookid = $(this).parent().prev().prev().val();
						var id= $(this).next().val();
						readBook(bookid);
						return false;
					});

					function readBook(bookid) {
						
						
						url = baseUrl + "view/read.jsp?novelid="
								+ bookid+"&&isupdate="+isupdate;
						
						window.open(url, "_self");
						//取消行列的默认行为
						return false;

					}
					//点击个人中心
					$("#userspan").click(function(){
						var user = getUser();
						//查询消息记录数
						$.ajax({
							url:baseUrl+'message/getMessageCount.action',
							data:'userid='+user.id+"&&state="+0,
							type:"POST",
							success:function(e){
								$("#usermessage").children().empty();
								if(e.code==200){
									$("#usermessage").children().append(e.extend.count);
								}else if(e.code==400){
									$("#usermessage").children().append(0);
								}
							}
						});
						
						$("#userinflist").show();
						var nickname = user.nickname;
						$("#nickname").empty();
						$("#nickname").append('   '+nickname);
						var height =  window.screen.availHeight;
						$("#userinflist").css({
							'position':'absolute',
							'left':'0px',
							'top':'0px',
							
						});
						return false;
						
					});
					
					 //隐藏
					 $("#userhide").click(function(){
						 $("#userinflist").hide();
					 });
					$("#body").click(function(){
						$("#userinflist").hide();
					});
					
					
					//点击意见反馈
					$("#feedback").click(function(){
						feedback();
					});
				 
					function feedback(){
						$("#feedbackForm")[0].reset();
						$("#feedbackModal").modal('show');
						$("#feedbackSure").click(function(){
							//检查数据
							var jsondata = $("#feedbackForm").serializeArray();
							var feedbacktype = jsondata[0].value;
							var body = jsondata[1].value;
							var contactType = jsondata[2].value;
							var contact = jsondata[3].value;
							var user = getUser();
							var userid = user.id;
								$.ajax({
									url:baseUrl+"feedback/addfeedback.action",
									data:$("#feedbackForm").serialize()+"&&userid="+userid,
									type:"POST",
									success:function(e){
										if(e.code==200){
											alert('意见反馈成功');
										}else if(e.code==400){
											alert(e.msg);
										}
										$("#feedbackModal").modal('hide');
									}
								});
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
				
				});
	</script>

	<!-- 意见反馈 -->
	<div class="modal fade" tabindex="-1" role="dialog" id="feedbackModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">意见反馈</h4>
      </div>
      <div class="modal-body">
      
        <form class="form-horizontal" id="feedbackForm">
  
  
  <div class="form-group">
  <label class="radio-inline">
    类型：
</label>
  <label class="radio-inline">
  <input type="radio" name="feedbacktype" class="type" value="Bug"> Bug
</label>
<label class="radio-inline">
  <input type="radio" name="feedbacktype" class="type" value="意见"> 意见
</label>
<label class="radio-inline">
  <input type="radio" name="feedbacktype" class="type" value="其他"> 其他
</label>
  </div>

<div class="form-group">
    <div class="col-sm-10">
      <textarea cols="40" rows="3" placeholder="请输入你要留下的内容" name="body" ></textarea>
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
        <button type="button" class="btn btn-primary" id=feedbackSure>确定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


</body>
</html>