<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	    		<%
					request.setAttribute("baseUrl", request.getContextPath()+"/");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投诉管理</title>
</head>
<body>
	<div class="container">

		<jsp:include page="head.jsp"></jsp:include>
		<hr>
		<div class="row">

			<div class="col-sm-offset-4 col-sm-8">

				<button class="reporttype btn btn-primary">侵权</button>
				&nbsp;&nbsp;&nbsp;
				<button class="reporttype btn btn-success">涉黄、暴力</button>
				&nbsp;&nbsp;&nbsp;
				<button class="reporttype btn btn-info">其他</button>
				&nbsp;&nbsp;&nbsp;
			</div>

			<!-- 显示结果 -->
			<div class="col-sm-offset-2 col-sm-8">
				<table class="table table-hover">
					<thead>
						<th>小说名</th>
						<th>联系方式</th>
						<th>原因</th>
						<th>用户id</th>
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
			<div class="col-sm-offset-8 col-sm-2">
				<a style="color: #444444; float: left" id="pages"></a>
			</div>

		</div>

	</div>
	
	
		<script type="text/javascript">
	
	$(document).ready(function() {
      $("#ts").addClass("active");
		var baseUrl = "${baseUrl}";

		var reporttype;
		
		//点击查询
		$(".reporttype").click(function() {
			reporttype = $(this).text();
            getReport(reporttype,1);
		});

		function getReport(reporttype,pn) {
			$.post(baseUrl + 'report/getReport.action', {
				'pn' : pn,
				'reporttype' : reporttype
			}, function(e) {
				if (e.code == 400) {
					alert(e.msg);
				} else if (e.code == 200) {
					var pageinfo = e.extend.pageinfo;
					showReport(pageinfo);
				}
			});
		}
		
		function showReport(pageinfo){
			//清空遗留
			$("#tbody").empty();
			var reports = pageinfo.list;
			$.each(reports, function(index, report) {
				var novelnametd = $("<td></td>").append(report.novelname);
				var contecttd = $("<td></td>").append(report.contacttype+':'+report.contact);
				var reason = $("<td></td>").append(
						report.reason);
				var state = $("<button></button>").append(
						report.state == 0 ? '删除小说' : '已读')
						.addClass('btn btn-danger bt-sm')
						.attr('id', report.id);
				
				var edit = $("<button></button>").append('忽略').addClass('btn btn-info bt-sm').attr('id', report.id+'1');;
				if(report.state==1){
					state.addClass('disabled');
					edit.addClass('disabled');
				}
				
				//点击禁用
				state.click(function() {
					if(report.state==1){
						alert("已忽略");
						return false;
					}
					$.post(baseUrl+'novel/deleteNovel.action',{'id':report.id,'novelid':report.novelid,'userid':report.userid},function(e){
						if(e.code==200){
							$("#tr"+report.id).remove();
						}else if(e.code==400){
							alert(e.msg);
						}
					})
				});
				
				//将report显示已读
				 edit.click(function(){
					$.post(baseUrl+'report/updateState.action',{'id':report.id},function(e){
						if(e.code==200){
							$("#"+report.id+'1').addClass('disabled');
							$("#"+report.id).empty();
							$("#"+report.id).append('已读');
							state.addClass('disabled');
							
						}else if(e.code==400){
							alert(e.msg);
						}
					});
				}); 

				var tdc = $("<td></td>").append(state).append(edit);
			  var tr = 	$("<tr></tr>").append(novelnametd).append(contecttd)
						.append(reason).append(tdc).attr('id','tr'+report.id);
			  if(report.state==1){
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
			getReport(reporttype,pn);
		}
		
		
		
		
		
		
	});
	
	</script>
</body>
</html>