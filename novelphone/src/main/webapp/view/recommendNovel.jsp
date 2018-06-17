<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("baseUrl", request.getContextPath()+"/");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>推荐书籍</title>

<script type="text/javascript"
	src="${baseUrl }static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="${baseUrl }static/js/jquery.cookie.js"></script>

<script src="${baseUrl }static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link
	href="${baseUrl }static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<div class="container" id="itembook">
		<br>
		<div class="row">

				<div class=" col-xs-12" style="text-align: center">
					<ul class="nav nav-pills" style="background-color:gainsboro;font-size: 15px">
						<li role="presentation btn-xs" id="index"><a href="#">书架</a></li>
						<li role="presentation" class="active"><a
							href="${baseUrl }view/recommendNovel.jsp">推荐</a></li>
						<li role="presentation"><a
							href="${baseUrl }view/sortnovels.jsp">分类</a></li>
						<li role="presentation" ><a href="${baseUrl }view/novels.jsp">搜索</a></li>
					</ul>
				</div>

			</div>
		<br>
	<%--	<div class="row">

				&lt;%&ndash;<div  style="overflow: hiden;"></div>&ndash;%&gt;

		</div>--%>

	</div>
</body>
</html>



<script type="text/javascript">
//页面加载后开始
$(document).ready(function(){
	var type = null;
	 type = GetQueryString("type");
	var foot = GetQueryString("foot"); 
	var baseUrl = "${baseUrl}";
	var getData = true;//判断当前是否需要加载数据
	
	//显示的次数
	var pageSize = 1;
	//一次显示的记录数
	 var pageNum = 4
	 //总记录数
	 var pageNums = 0;
	getRecommendNovels(0,3);//第一次加载8条记录


	
	//读取推荐榜
	function getRecommendNovels(start,end){
		if(!getData){
			return false;
		}
		
		var url;
		var data;
		//判断是查询分类还是推荐榜单
		if(type!=null){
			url = baseUrl+'novel/getRecommendNovels.action';
			data = "start="+start+"&&end="+end+"&&type="+type;
		}
		//查询足迹
		if(foot!=null){
			url = baseUrl+'novel/getFootPrint.action';
			var footids = $.cookie('footprint');
			var split = footids.split('-');
			if(footids=='undefined'||footids==''||footids==null){
				getData = false;
				return false;
			}
			var ids='';
			if((pageSize-1)*pageNum>split.length){
				getData = false;
				return false;
			}
			for(var i=(pageSize-1)*pageNum;i<(pageSize*pageNum>=split.length?split.length:pageSize*pageNum);i++){
				ids  = ids + split[i]+'-';
				
			}
			data = 'ids='+ids.substring(0,ids.length-1);
		}
	
		if(type==null&&foot==null){
			url = baseUrl+'novel/getRecommendNovels.action';
			 data = "start="+start+"&&end="+end;
		}
		
		
		$.ajax({
			url:url,
			data:data,
			type:"POST",
			success:function(e){
				if(e.code==200){
					//显示书籍
					var novels = e.extend.recommendNovels;
					showNovels(novels);
				}else if(e.code==400){
					$("<h3></h3>").append(e.msg).appendTo("#pageone");
					//同时禁止加载数据
					getData = false;
					
				}
			}
		});
	}
	
	
//显示书籍
	function showNovels(novels){
		$.each(novels,function(index,novel){
			pageNums = pageNums+1;
			var img  = $("<img></img>").attr('src',novel.image).addClass("img-responsive").attr("alt","图片加载失败");
			var title = $("<p></p>").append('书名:   '+novel.title);
			var auth = $("<p></p>").append('作者:    '+novel.auth);
			var type = $("<p></p>").append('类型 :    '+novel.type);
			var state = $("<p></p>").append('状态:    '+novel.state);
			var div = $("<div></div>").addClass("col-xs-6")
			.append(title)
			.append(auth)
			.append(type)
			.append(state);
			var imgdiv = $("<div></div>").addClass("col-xs-offset-1").addClass('col-xs-4').append(img);
			var divclick = $("<div></div>").addClass('row').append(imgdiv).append(div);
			$("#itembook").append(divclick);
			divclick.click(function(){
				window.open(baseUrl+'novel/getNovel.html?id='+novel.id,'_self');
			/* 	 var novel1 = JSON.stringify(novel);
				novel1 = novel1.substring(0,novel1.length-1)+",\"searchtype\":0}";
				var noveldata = JSON.parse(novel1);
				$.post(baseUrl+'novel/addNovel.action',noveldata,function(e){
					$("body").empty();
					$("body").append(e);
				}); */
			}); 
		});
	/* 	var more = $("<a></a>").append('加载更多').attr("id","getmore");
		var morediv = $("<div></div>").addClass("col-xs-offset-2 col-xs-4").append(more);
		morediv.click(function(){
			
		});
		$("#more").append(more); */
	}
	
	//滚动屏幕结束
	var bodyH1= $("body").height();
	var height = document.documentElement.clientHeight ;
/* 	console.log('height'+height);
	console.log('bodyH'+bodyH); */
	 $(window).scroll(function() { 
		var bodyH= $("body").height();
		//滚动条的高度
		var scro = $(document).scrollTop();
		//可见屏幕高度
		var height = document.documentElement.clientHeight ;
		/* console.log('height'+height);
		console.log('bodyH'+bodyH);
		console.log('scro'+scro); */
		//加载数据所需要的长度
		var h = height+scro+bodyH1*2;
		if(h>=bodyH){
			//来说加载数据
			getRecommendNovels(pageSize*pageNum,(pageSize+1)*pageNum-1);
			pageSize = pageSize+1;
		}
		});  
	/* 	 $(window).scroll(function() { 
			var scrollTop = $(this).scrollTop;//计算已经卷进去滚动条的的高度
			var scrollHeight = $(document).height;//当前页面的总高度
			var windowHeight = $(this).height;//当前window也就是浏览器的高度
			if (scrollTop + windowHeight == scrollHeight) {
				console.log('w');
				getRecommendNovels(pageSize*pageNum,(pageSize+1)*pageNum-1);
			}//这是一个全局的变量，页面滑到底部就加
			
		});  */
	
	
	
	$("#index").click(function(){
		var user = getUser();
		userid = JSON.parse(user).id;
		var url = baseUrl + "novel/getReadRecord.html?userid="+userid;
		window.open(url,'_self');
	});
	
	
	function getUser(){
		var user = $.cookie('user');
		if(user==null){
			window.open(baseUrl+'login.jsp','_self');
		}else{
			return user;
		}
	}
	
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
