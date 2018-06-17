<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    		<%
				request.setAttribute("baseUrl", request.getContextPath()+"/");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">

		<jsp:include page="head.jsp"></jsp:include>
		<hr>

		<div class="row">

			<div class="col-sm-offset-4 col-sm-8">

				<button class="feedback btn btn-primary">Bug</button>
				&nbsp;&nbsp;&nbsp;
				<button class="feedback btn btn-success">意见</button>
				&nbsp;&nbsp;&nbsp;
				<button class="feedback btn btn-info">其他</button>
				&nbsp;&nbsp;&nbsp;
			</div>

			<!-- 显示结果 -->

			<div class="col-sm-offset-2 col-sm-8">
				<table class="table table-hover">
					<thead>
					<th>用户id</th>
					<th>联系方式</th>
					<th>内容</th>
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
	<script type="text/javascript">
	
	$(document).ready(function() {

		$("#yj").addClass("active");
		var baseUrl = "${baseUrl}";

		var feedbacktype;
		
		//点击查询
		$(".feedback").click(function() {
			 feedbacktype = $(this).text();
            getFeedBack(feedbacktype,1);
		});

		function getFeedBack(feedbacktype,pn) {
			$.post(baseUrl + 'feedback/getFeedBack.action', {
				'pn' : pn,
				'feedbacktype' : feedbacktype
			}, function(e) {
				if (e.code == 400) {
					alert(e.msg);
				} else if (e.code == 200) {
					var pageinfo = e.extend.pageinfo;
					showFeedBack(pageinfo);
				}
			});
		}
		
		function showFeedBack(pageinfo){
			//清空遗留
			$("#tbody").empty();
			var feedbacks = pageinfo.list;
			$.each(feedbacks, function(index, feedback) {
				var useridtd = $("<td></td>").append(feedback.userid);
				var contecttd = $("<td></td>").append(
						feedback.contacttype+':'+feedback.contact);
				var bodytd = $("<td></td>").append(
						feedback.body);
				var state = $("<button></button>").append(
						feedback.state == 0 ? '未读' : '已读')
						.addClass('btn btn-danger bt-sm')
						.attr('id', feedback.id);
				//var edit = $("<button></button>").append('编辑').addClass('btn btn-info bt-sm');
				//点击禁用
				state.click(function() {
					$.post(baseUrl+'feedback/updateFeedbackstate.action',{'id':feedback.id},function(e){
						if(e.code==200){
							state.addClass('disabled');
							$("#"+feedback.id).empty();
							$("#"+feedback.id).append('已读');
						}else if(e.code==400){
							alert(e.msg);
						}
					})
				});
				/* edit.click(function(){
					var userdata = JSON.stringify(user);
					editUser(userdata);
				}); */

				var tdc = $("<td></td>").append(state);
			  var tr = 	$("<tr></tr>").append(useridtd).append(contecttd)
						.append(bodytd).append(tdc);
			  if(feedback.state==1){
				tr.css('background-color','#CCCCCC');
				state.addClass('disabled');
				}
			  tr.appendTo("#tbody");
						
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
			getFeedBack(feedbacktype,pn);
		}
		
		
		
		
		
		
	});
	
	</script>

</body>
</html>