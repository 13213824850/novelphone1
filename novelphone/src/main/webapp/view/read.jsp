<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%
	 request.setAttribute("baseUrl", request.getContextPath()+"/");%>
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
<title>阅读</title>

<%-- <meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<script type="text/javascript"
	src="${baseUrl }static/js/jquery-1.12.4.min.js"></script>
<script
	src="${baseUrl }static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> 
<link
	href="${baseUrl }static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
 --%>
</head>
<body style="background-color: #EEEEEE;" >

	<div data-role="page" id="pageone">
		<a href="" id="contentSource" style="font-size: 10px;color: black;">原网页</a>
		<div id="chapter" data-role="header"></div>
		<!-- 章节 -->
		<div id="contenttext" data-role="content" style="font-size: 20px;display:block;line-height: 25px;">
			<!-- //内容  -->
			<!-- <a style="font-size: 20px;color:#4D4D4D;font-family: serif;　line-height:10px;" id="contenttext"></a> -->
		</div>
		<!-- //页数 -->
		<div id="page" style="font-size: 11px; text-align: center;"
			data-role="footer"></div>



	</div>
	<!-- 隐藏的导航栏 -->
	<div id="showfooter"
		style="width: 100%;  background: #808080;" hidden>
		<div style="text-align: center; padding: 10px;"></div>
		<div style="width: 100%;text-align: center;"><a style="font-size: 15px;color: white;" id="chaptertext"></a></div>
		<div style="align-content: center; text-align: center;"><a style="color: white;font-size: 15px" id="prea">上一章</a><input type="range" data-popup-enabled="true" min=0 id="range">
		<a style="color: white;font-size: 15px" id="nexta">下一章</a><br></div>
	<hr>	<a style="color: white; margin-left: 10%;font-size: 20px;" id="chapteritem">目录</a>
		<a style="color: white; float: right; margin-right: 10%;font-size: 20px" id="index">返回书架</a>

	</div>
	<!-- 隐藏的目录栏 -->
	<div id="chapters"
		style="background-color: #808080; overflow: auto; width: 60%; color: white; font-size: 15px"
		hidden></div>

	<script type="text/javascript">
		window.onload = (function() {
			var baseUrl = "${baseUrl}";
			var novelid = GetQueryString("novelid");
			var isupdate = GetQueryString("isupdate");
			//添加读书的足迹
			//writefootprint(novelid);
			var contetnidcookie = getCookieContentid();//获取读书章节记录
			
			var contentFontSize = 20;
			/* var phonew =window.screen.availWidth;
			var phoneh = window.screen.availHeight;
			console.log(phonew+"  "+phoneh); */
			var scranheight = $(document.body).height();
			var scranwidth = $("#contenttext").width();
			var pageoneheight = $("#chapter").height();
			var page = $("#page").height();
			//判断每行显示的字数 可以显示多少行
			var fontlinecountt = Math.floor(scranwidth/contentFontSize);
			var fontrowcount =  Math.floor((scranheight-pageoneheight-page)/25)-2;
			console.log(fontlinecountt+"  "+fontrowcount);
			console.log(scranheight+"  "+scranwidth);
			console.log(scranheight+" "+pageoneheight+" "+ page+"  "+fontrowcount);
			var height = $("#contenttext").height();
			var locationcontentid;
			var pagenum = 1; //当前页数
			var array = new Array();
			var nextidstatic = 0;
			var preidstatic = 0;
			var chaptercount = 0 ;
			var contentnum//当前第几章节
			
			
			var url = baseUrl+"novel/getContentCount.action";
			$.post(url,{"novelid":novelid},function(e){
				if(e.code==200){
					chaptercount = e.extend.count;
					$("#range").attr("max",chaptercount);
					$("#range").attr("value",contetnidcookie);
				}else{
					return false;
				}
			})
			
			//判断小说是否违规
			$.ajax({
				url:baseUrl+'novellower/getstate.action',
				data:'novelid='+novelid,
				type:"post",
				success:function(e){
					if(e.code==400){
						alert(e.msg);
						window.history.back();
						return false;
					}
				}
			});
			
			
			//判断现身是否更新
			if(isupdate==1){
				var user = getUser();
				if(user!=null){
					userid = user.id;
				$.get(baseUrl+'read/updateReadisUpdate.action?userid='+userid+"&&novelid="+novelid);
				}
			}
			
			
			topage(contetnidcookie);
			//开始读书左右翻页
		
				function readPage(content, chapter, preid, nextid) {
					//开始分页
					array=[];
					array[0]="&nbsp;";
					pagenum = 1;
					
					//开始分页
					var pageindex = 0;
					var contentp = content.split('<br>');//判断有多少段文字
					var pagerows=0;//定义每个素组加的段数
					var overstr=null;
					var overrow=0;
					for(var i=0;i<contentp.length;i++){
						//判断每个数组中文字所占行数
						var row = Math.ceil(contentp[i].length/fontlinecountt);
						var spacesize = contentp[i].length%fontlinecountt;
						if(spacesize!=0){
							var space = "<br>";
							contentp[i] = contentp[i]+space;
						}
						pagerows = pagerows + row;
						//判断当前页面是否排满
					//	console.log(pagerows+"  "+pageindex);
						if(pagerows>fontrowcount){
							 overrow = pagerows-fontrowcount//超出的行数
							var nativecount = (row-overrow)*fontrowcount;
							array[pageindex] = array[pageindex] +contentp[i].substring(0,nativecount);
							overstr = contentp[i].substring(nativecount,contentp[i].length);
							pagerows = 0;
							pageindex = pageindex+1;
							array[pageindex]="&nbsp;";
						}else{
							if(overstr != null){
								array[pageindex] = array[pageindex]+overstr;
								overstr = null;
								pagerows = pagerows + overrow;
								overrow = 0;
							}
						    array[pageindex] = array[pageindex] +contentp[i];
						}
					
						
					}
					//显示页码
				    var pagesize = array.length;
					
					//展示第一页
					$("#contenttext").empty();
					$("#contenttext").append(array[0]);
					$("#chapter").text(chapter);
					$("#page").text(pagenum + '/' + pagesize);
					$("#chaptertext").text(chapter);
					
					$("body").on("swipeleft", function() {
						if (pagenum >= pagesize) {
							topage(nextid);
							return false;
						}
						$("#contenttext").empty();
						$("#contenttext").append(array[pagenum]);
						pagenum = pagenum + 1;
						$("#page").text(pagenum + '/' + pagesize);

					});

					$("body").on("swiperight", function() {
						if (pagenum <= 1) {
							topage(preid);
							return false;
						}
						pagenum = pagenum - 1;
						$("#contenttext").empty();
						$("#contenttext").append(array[pagenum-1]);
						$("#page").text(pagenum + '/' + pagesize);
					});

				}
	        
			
			function topage(contentid) {
				//解除邦迪事件
				$("body").off("swipeleft");
				$("body").off("swiperight");
				$("#range").attr("value",contetnidcookie);
					$.ajax({
						url : "${baseUrl }novel/readNoveljson.action",
						data : "contentid=" + contentid + "&&novelid="
								+ novelid,
						type : "POST",
						success : function(data) {
							if (data.code == 200) {
								var content = data.extend.content;
								preidstatic = content.preid;
								nextidstatic = content.nextid;
								//清空array
								readPage(content.content, content.chaptertext,
										content.preid, content.nextid);
								locationcontentid = content.id;
								contentnum = content.num;
								
							} else if (data.code == 400) {
								alert(data.msg);
							}
						}
					});
				     $.cookie((novelid+'').trim(),contentid, { expires: 365, path: '/' });
					
          
			}

			$("#contenttext").on("tap", function() {
				showView();
			});
			
			

		
		
			//点击事件
			var flag = true;
			function showView() {
				if (flag) {
					
				//查询小说章节数量
			
						$("#range").attr("max",chaptercount);
						$("#range").attr("value",contentnum);
					
					
					//定位导航栏目
				$('#showfooter').show(500);
				var clientx = (scranheight*0.8+'').trim();
				$('#showfooter').css({
					'position':'absolute',
					'left':'0px',
					'top':clientx+'px',
					'height':scranheight*0.2+'px'
				});

					flag = false;
				} else {
					$("#showfooter").hide();
					$("#chapters").hide();
					flag = true;
				}

			}
			
			
			//查询目录
			$("#chapteritem").click(function(){
				
				console.log("chapter");
				$("#showfooter").hide();
				$.ajax({
					url:"${baseUrl}novel/getChapters.action",
					data:"novelId="+novelid,
					type:"POST",
					success:function(e){
						$("#chapters").empty();
						$("#chapters").show();
						if(e.code==200){
							var currentindex;
							var chapters = e.extend.chapters;
							$.each(chapters,function(index,chapter){
								var tr = $("<p></p>").append(chapter.chaptertext).attr('id',chapter.contetnid);
								tr.css({
								'overflow': 'hidden',
								'text-overflow':'ellipsis',
								'white-space': 'nowrap'
								});
								//判断是否是当前章节
								if(chapter.contentid==locationcontentid){
									tr.css('color','red');
									currentindex = index;
								} 
								//添加点击事件
								tr.click(function(){
									$("#chapters").hide();
									topage(chapter.contentid,chapter.novelid);
								});
								$("#chapters").append(tr);
							});
							showChapterview(chapters.length,currentindex);
						}else if(e.code==400){
							alert(e.msg);
						}
					}
				});
				//固定高度
				$("#chapters").css({
					'height':scranheight,
					'position':'absolute',
					'top':'0px',
					 'left':'0px'
				});
				
			});				

			
			
		   $("#prea").click(function(){
			   topage(preidstatic);
		   });
		   $("#nexta").click(function(){
			   topage(nextidstatic);
		   });
			
			//返回书架
			$("#index").on("tap", function() {
				var user = $.cookie("user");
				var userid = JSON.parse(user).id;
				var url = "${baseUrl}novel/getReadRecord.html?userid="+userid;
				window.open(url,"_self");
			});	
			//若当前章节隐藏则显示
			function showChapterview(length,index){
				var height = (length/14)*scranheight;
				//单个p标签所需高度
				var singlep = height/length;
				console.log(singlep*index);
				console.log( $("#chapters").scrollTop());
				 $("#chapters").scrollTop(singlep*(index-7));
			}
			//正则获取传递的参数
			function GetQueryString(name) {
				var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
				var r = window.location.search.substr(1).match(reg);
				if (r != null)
					return unescape(r[2]);
				return null;
			}
			function getCookieContentid(){
				var contentid = 0;
				var	id = $.trim(novelid+'');
					 contentid = $.cookie((id+'').trim());
					//var contentid1 = getCookie(bookid);
					if (contentid == null || contentid == '') {
						//开始读第一章
						contentid = 0;
					}
				
			return contentid;
			}
			
			
			function getUser(){
				var user = $.cookie('user');
				if(user!=null){
				return JSON.parse(user);
				}
				return null;
			}
			
			
			function writefootprint(e){
				
				contetnidcookie = e;
				//写下足迹
				
				//先获取
				var novelids = $.cookie('footprint');
				if(novelids=='undefined'||novelids==''||novelids==null){
					novelids = e+'-';
				}else{
					novelids = e+'-'+novelids;
				}
				//判断足迹是否大于30
				var split = novelids.split('-');
				if(split.length>=30){
					$.each(novelids,function(index,id){
						if(index>=30){
							return false;
						}
						novelids = id+'-';
					});
				}
					$.cookie('footprint',novelids.substring(0,novelids.length-1), { expires: 30, path: '/' })
			}



			$("#contentsource").click(function () {
				window.open("http://www.biquge.com.tw/","_self");
            });
	
		});
		
		
		
		
		
	
	</script>


</body>
</html>