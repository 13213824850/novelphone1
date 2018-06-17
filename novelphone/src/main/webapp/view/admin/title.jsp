<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	    		<%
					request.setAttribute("baseUrl", request.getContextPath()+"/");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>


</head>
<body>
	<div class="container">

	<jsp:include page="head.jsp"></jsp:include>
		<hr>
		<div class="row">
			<!-- 左侧导航 -->
			<div class="col-sm-2">
				<ul class="nav nav-pills" style="font-size: 15px;">
					<li role="presentation" class="active"><a href="#">查看用户</a></li>
					<br>
					<br>
				</ul>
			</div>
			<!-- 右侧显示 -->
			<div class="col-sm-10">
				<div class="row">
					<div class="col-sm-10">
						<input type="text" placeholder="请输入用户名" name="name" id="name" />
						&nbsp;
						<button id="search" class="btn btn-primary">查询</button>
						&nbsp;
						<button id="searchs" class="btn btn-success">查询所有</button>
					</div>
				</div>
				<hr>

				<div class="row">
					<div class="col-sm-10">
						<table class="table table-hover">
							<thead>
								<th>用户id</th>
								<th>用户名</th>
								<th>用户昵称</th>
								<th>最后登录时间</th>
								<th>操作</th>
							</thead>

							<tbody id="tbody">

							</tbody>
						</table>

					</div>
				</div>
				<div class="row">

					
					<div class="col-sm-offset-6 col-sm-4">
						<!-- 分页 -->
						<nav aria-label="Page navigation">
						<ul class="pagination" id="ul">
						</ul>
						</nav>
					</div>
					<div class="col-sm-offset-8 col-sm-2" >
					<a style="color:#444444;float: left" id="pages"></a>
					</div>
					
				</div>
				
						
			</div>

		</div>


	</div>

	<script type="text/javascript">
		$(document).ready(function() {

			
			$("#yh").addClass('active');
			
							var baseUrl = "${baseUrl}";

							//点击查询
							$("#search").click(function() {
								getUsers(1);

							});

							var userstate;
							function getUsers(pn) {
								var name = $("#name").val();
								$.post(baseUrl + 'user/getUsers.action', {
									'pn' : pn,
									'name' : name
								}, function(e) {
									if (e.code == 400) {
										alert(e.msg);
									} else if (e.code == 200) {
										var pageinfo = e.extend.pageinfo;
										showUsers(pageinfo);
									}
								});
							}
							//显示用户
							function showUsers(pageinfo) {
								//清空遗留
								$("#tbody").empty();
								var users = pageinfo.list;
								$.each(users, function(index, user) {
									userstate = user.state;
									var idtd = $("<td></td>").append(user.id);
									var nametd = $("<td></td>").append(
											user.name);
									var nicknametd = $("<td></td>").append(
											user.nickname);
									
									
									
									//装换时间
									var time = dateformate(user.lasttime);
									
									
									var lasttimetd = $("<td></td>").append(time);
									var state = $("<button></button>").append(
											userstate == 0 ? '禁用' : '解禁')
											.addClass('btn btn-danger bt-sm')
											.attr('id', user.id);
									//var edit = $("<button></button>").append('编辑').addClass('btn btn-info bt-sm');
									//点击禁用
									state.click(function() {
										disabledUser(user.id,
												userstate == 0 ? 1 : 0);
									});
									/* edit.click(function(){
										var userdata = JSON.stringify(user);
										editUser(userdata);
									}); */

									var tdc = $("<td></td>").append(state);
									$("<tr></tr>").append(idtd).append(nametd)
											.append(nicknametd).append(
													lasttimetd).append(tdc)
											.appendTo("#tbody");
								});
								//显示分页
								showPage(pageinfo);
							}

							//显示分页
							function showPage(pageinfo) {
								//清空nav
								$("#ul").empty();

								//判断是否有pre next
								var pre = $("<li></li>")
										.append(
												" <a href=\"#\" aria-label=\"Previous\">  <span aria-hidden=\"true\" >&laquo;</span> </a>");
								if (!pageinfo.hasPreviousPage) {
									pre.addClass('disabled');
								}
								pre.click(function() {
									topage(pageinfo.prePage);
								});
								$("#ul").append(pre);

								//判断页码是否够5
								var pagesizeshow = 0;
								$.each(pageinfo.navigatepageNums, function(
										index, page) {
									if (pagesizeshow >= 5) {
										return false;
									}
									var page1 = $("<li></li>").append(
											"<a href=\"#\">" + page + "</a>");
									if (page == pageinfo.pageNum) {
										page1.addClass('active');
									}

									page1.click(function() {
										topage(page);
									});

									$("#ul").append(page1);
									pagesizeshow = pagesizeshow + 1;
								});

								var next = $("<li></li>")
										.append(
												" <a href=\"#\" aria-label=\"Next\"> <span aria-hidden=\"true\" >&raquo;</span></a>");
								if (!pageinfo.hasNextPage) {
									next.addClass('disabled');
								}
								next.click(function() {
									topage(pageinfo.nextPage);
								});
								$("#ul").append(next);

								//添加页数
									$("#pages").empty();
								$("#pages").append(
										'共' + pageinfo.pages + '页   ').append(
										pageinfo.total + '记录');

							}

							//加载pn页
							function topage(pn) {
								getUsers(pn);
							}

							//禁用 解禁用户
							function disabledUser(userid, state) {
								$.post(baseUrl + 'user/disabledUser.action', {
									'userid' : userid,
									'state' : state
								}, function(e) {
									if (e.code == 200) {
										$("#" + userid).empty();
										userstate = state;
										if (state == 0) {
											$("#" + userid).append('禁用');
										} else if (state == 1) {
											$("#" + userid).append('解禁');
										}
									} else if (e.code == 400) {
										alert(e.msg);
									}
								});
							}
							
							
							function dateformate(ctime){
							    // 对Date的扩展，将 Date 转化为指定格式的String  
							    // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
							    // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
							    Date.prototype.Format = function (fmt) { //author: meizz   
							        var o = {  
							            "M+": this.getMonth() + 1, //月份   
							            "d+": this.getDate(), //日   
							            "H+": this.getHours(), //小时   
							            "m+": this.getMinutes(), //分   
							            "s+": this.getSeconds(), //秒   
							            "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
							            "S": this.getMilliseconds() //毫秒   
							        };  
							        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
							        for (var k in o)  
							        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
							        return fmt;  
							    }  
							      
							  //  调用：   
							  //  var time1 = new Date().Format("yyyy-MM-dd");  
							    var time2 = new Date().Format("yyyy-MM-dd HH:mm:ss");  
							      
							    var nowTime=new Date(ctime);  
							   return nowTime.Format("yyyy-MM-dd HH:mm:ss");//上月当前时间  
								
							}
							

						});
	</script>
</body>
</html>