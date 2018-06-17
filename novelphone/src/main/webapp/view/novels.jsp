<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
	 request.setAttribute("baseUrl", request.getContextPath()+"/");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="https://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>  -->

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果</title>
<script type="text/javascript"
	src="${baseUrl }static/js/jquery-3.3.1.min.js"></script>
<script src="${baseUrl }static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link
	href="${baseUrl }static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<div class="container">
	
		<div class="row">
			<div style="float: right" id="searchNovel" class="col-xs-12">
				<a id="index" href="#">返回书架</a>&nbsp;&nbsp;
				<input type="text" name="novelName" id="novelName"
					placeholder="作者、小说名"  /> <span id="getNovels"
					class="glyphicon glyphicon-search btn-lg"></span>
			</div>
		</div>
		<c:if test="${requestScope.novels!=null }">
			<!-- 遍历数据 -->
			<c:forEach items="${requestScope.novels }" var="novel">
				<div>
					<table>
						<tr>
							<td>
								<div class="itemBook">
				<input type="hidden" name="searchtype" value="${novel.product==null?1:0 }" />
					<div class="row">
						<div class="col-xs-4 ">
							<img src="${novel.image }" class="img-responsive" alt="Responsive image" ></img>
						</div>

						<div class="col-xs-offset-1 col-xs-6">
							<br> 书名: ${novel.title }<br> 作者: ${novel.auth }<br>
							最新章节:<a >${novel.newchapter }</a><br>

						</div>
					</div>
					
				</div>
				<!-- 隐藏域用于存放数据 -->
				<form class="resultBook">
					<input type="hidden" name="id"  id="id"  value="${novel.id }" /> 
					<input type="hidden" name="title" value="${novel.title }" />
					<input type="hidden" name="type" value="${novel.type }" />
					 <input	type="hidden" name="titleurl" value="${novel.titleurl }" /> <input
						type="hidden" name="image" value="${novel.image }" /> <input
						type="hidden" name="newchapter" value="${novel.newchapter }" /> <input
						type="hidden" name="newchapterurl" value="${novel.newchapterurl }" />
					<input type="hidden" name="auth" value="${novel.auth }" />
					 <input type="hidden" name="body" value="${novel.body }" />
				
				</form>
							</td>
						</tr>
					</table>

				</div>
				<br>
			</c:forEach>
			
				<div  id="huanyuan">
				<a   href="#" id="sanfang" >
					没有想要的试试第三方资源搜索</a>
				</div>
		</c:if>

	</div>
	<input type="hidden" value="${novelName==nul?0:novelName }" id="novelNamemsg"/>
	<script type="text/javascript">
		var baseUrl = "${baseUrl }";

        $("#index").click(function(){
            var userid = user.id;
            var url = baseUrl + "novel/getReadRecord.html?userid="+userid;
            window.open(url,'_self');
        });

        if($("#novelNamemsg").val()!=0){
			$("#novelName").attr('placeholder',$("#novelNamemsg").val());
			$("#novelName").attr('value',$("#novelNamemsg").val());
		}
		//本地到不到换源
        if($("#id").val()==''||$("#id").val()==null){
        	$("#huanyuan").hide();
        }
		$("#sanfang").click(function(){
			getNovels(1);
			return false;
		});
		
		
		var user = getCookie("user");
		user = JSON.parse(user);
	
		$("#getNovels").click(function() {
			getNovels(0);
		});
		//点击书籍添加
		var clicktime = 0; //点击事件防止表单重复提交
		$(".itemBook").click(function() {
			var data = $(this).next().serialize();
			var type = $(this).children().val();
            setTimeout("clicktime=0",3);
			if(clicktime<3){
                addUserBook(data, type);
            }
		});

		/*
		         搜索
		 */
		function getNovels(e) {
			var novelName = $("#novelName").val();
			if (novelName == '' || novelName.trim().length == 0) {
				return false;
			}
			var url = baseUrl + "novel/getNovels.html?novelName=" + novelName
					+ "&&searchtype="+e;
			window.open(url, '_self');
		}

		function addUserBook(data, type) {
          clicktime = 3;
			//判断是否登录 
			if (user == null) {
				if (!confirm("请登录后方可查看")) {
					return false;
				}

			}
			var product = user.nickname;
			var productid = user.id;
			var url = baseUrl + "novel/addNovel.html?productid=" + productid+ "&&product=" + product + "&&" + data + "&&searchtype=" + type;

			window.open(url,"_self",function (e) {
                    clicktime = 0;
            });
				/*$.ajax({
				url:url,
				data:"productid=" + productid+ "&&product=" + product + "&&" + data + "&&searchtype=" + type,
				type:"POST",
				success:function (e) {
					if(e.code==400){

					    alert("查找失败");
					  //  window.open(baseUrl+"novel/getNovel.html?novelid="+e.data.extend.novelid,"_self");
					}

                }
			});*/
		}

	
	

		function getCookie(name) {
			var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
			if (arr = document.cookie.match(reg))
				return unescape(arr[2]);
			else
				return null;
		}
	</script>
</body>
</html>