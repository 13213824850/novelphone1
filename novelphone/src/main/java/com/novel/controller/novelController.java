package com.novel.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.novel.annotation.SameUrlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.novel.bean.Chapter;
import com.novel.bean.Content;
import com.novel.bean.Novel;
import com.novel.bean.ReadRecord;
import com.novel.bean.Report;
import com.novel.bean.User;
import com.novel.service.NovelService;
import com.novel.service.PNovelService;
import com.novel.service.ReportService;
import com.novel.service.UntilService;
import com.novel.service.impl.ReadRecordServiceimpl;
import com.novel.until.CookieUtils;
import com.novel.until.Msg;

@Controller
@RequestMapping("/novel/")
public class novelController {

	@Autowired
	private NovelService novelService;
	@Autowired
	private PNovelService pNovelService;
	@Autowired
	private ReadRecordServiceimpl readRecordService;
	
	@Autowired
	private ReportService reportService;

	@Autowired
	private UntilService untilService;
	
	/*
	 * 查询小说
	 * novelName 小说名
	 * type 0本地查询 1第三方查询
	 */
	@RequestMapping("getNovels")
	public String getNovels(@RequestParam("novelName") String novelName,@RequestParam("searchtype") Integer searchtype,HttpServletRequest request) {
		List<Novel> novels = new ArrayList<>();
		if(searchtype==0) {
			novels = novelService.getNovels(novelName);
			if(novels.size() != 0) {
				request.setAttribute("novels", novels);
			}else {
				//进入第三方search
				 novels = pNovelService.getNovels(novelName);
			}
		}else if(searchtype==1) {
			 novels = pNovelService.getNovels(novelName);
		}
		request.setAttribute("novels", novels);
		request.setAttribute("novelName", novelName);
		return "novels";
	}
	
	
	/*
	 * 选中书籍后直接开始解析数据
	 * type 0 本地搜索
	 */
	@RequestMapping("addNovel")
	@SameUrlData
	public Object addNovel(@RequestParam("searchtype") Integer searchtype,Novel novel,HttpServletRequest request  ) {
	
		if(searchtype==0) {
			novel = novelService.getNovel(novel.getId());
			return "redirect:./getNovel.action?id="+novel.getId();
		}
		//判断小说是否存在
		Novel  novel1 = pNovelService.getNovel(novel);
		if(novel1!=null) {
			//书籍已存在
			return "redirect:./getNovel.action?id="+novel.getId();
		}else {
			//异步调用第三方加载数据
			pNovelService.addNovel(novel);
			pNovelService.AsynAddNovel(novel);
		}
		if(novel.getId()==null){
			return Msg.fail("查询失败");
		}
		return "redirect:./getNovel.action?id="+novel.getId();
	}
	
	
	@RequestMapping("getNovel")
	public String getNovel(@RequestParam("id") Integer id,HttpServletRequest request  ) {
		
			Novel novel = novelService.getNovel(id);
			request.setAttribute("novel", novel);
			return "novel";
	}
	
	
	
	
	
	/*
	 * 查询目录
	 */
	@ResponseBody
	@RequestMapping("getChapters")
	public Msg  getChapters(@RequestParam("novelId") int novelId) {
		List<Chapter> chapters =  novelService.getChapters(novelId);
		if(chapters.size()==0) {
			return Msg.fail("加载失败");
		}
		return Msg.success().add("chapters", chapters);
	}

	/*
	 * 读取阅读记录
	 */
	
	@RequestMapping("getReadRecord")
	public String getReadRecord(HttpServletRequest request,@RequestParam("userid") int userid) {
		List<ReadRecord> readRecords =readRecordService.getReads(userid);
		if(readRecords.size()==0) {
			return "index";
		}
		request.setAttribute("readRecords", readRecords);
		//查询书籍信息
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> novelids = new ArrayList<Integer>();
		for (ReadRecord readRecord : readRecords) {
			int novelid = readRecord.getNovelid();
			novelids.add(novelid);
			map.put(novelid, readRecord.getIsupdate());
		}
		List<Novel> userNovels = novelService.getUserNovels(novelids);
		for (Novel novel : userNovels) {
			novel.setIsupdate(map.get(novel.getId()));
		}
		
		//实现排序
		Collections.sort(userNovels, new Comparator<Novel>() {

			@Override
			public int compare(Novel o1, Novel o2) {
				int i =o1.getIsupdate()-o2.getIsupdate();
					if(i>0) {
						return -1;
					}if(i==0) {
						return 0;
					}
					return 0;
			}
		});
		
		
		request.setAttribute("userNovels", userNovels);
		return "index";
	}
	
	/*
	 * 进入小说书籍首页点击开始阅读 contentid 小说内容id
	 * type 0 为本地 1时第三方加载数据 同时将异步数据存储
	 */
	@ResponseBody
	@RequestMapping("readNovel")
	public Msg readNovel(@RequestParam("novelid")Integer novelid) {
		
			Novel novel = novelService.getNovel(novelid);
			//查询是否数据已加载
			if(novel.getContentid()==0) {
				return Msg.fail("转码未完成请稍后重试");
			}
			
			return Msg.success();
		}
	
	
	@RequestMapping("indexRead")
	public String  indexRead() {
		return "read";
	}
	@ResponseBody
	@RequestMapping("readNoveljson")
	public Msg readNovel(@RequestParam("contentid")Integer contentid,@RequestParam("novelid")Integer novelid) {
		if(contentid==0) {
			Novel novel = novelService.getNovel(novelid);
			contentid = novel.getContentid();
		}
		
		Content content = novelService.readNovel(contentid,novelid);
		if(content==null) {
			return Msg.fail("为遭到");
		}
		return Msg.success().add("content", content);
	}
	
	
	/*
	 * 获取 推荐书籍 参数 start end 开始 结束的位置（从0开始100结束）type为查询的类型
	 */
	@ResponseBody
	@RequestMapping("getRecommendNovels")
	public Msg getRecommend(@RequestParam("start")Integer start,@RequestParam("end")Integer end
			,@RequestParam(value="type",required=false)String type) {
		if(end>=100) {
			return Msg.fail("已经是全部记录了");
		}
		List<Novel> recommendNovel = novelService.getRecommendNovel(start, end,type);
		if(recommendNovel==null||recommendNovel.size()==0) {
			return Msg.fail("已经是全部记录了");
		}
		
		return Msg.success().add("recommendNovels", recommendNovel);
	}
	
	//跳转搜索页面
	@RequestMapping("searchjump")
	public String searchjump() {
		return "novels";
	}
	

	/*
	 * 管理员删除novel
	 */
	@ResponseBody
	@RequestMapping("deleteNovel")
	public Msg deleteNovel(HttpServletRequest request,Report report) {
		User userRedis = untilService.getUserRedis(request);
		if(userRedis==null) {
			return Msg.fail("未登录");
		}
		return novelService.deleteNovel(report);
	}
	
	/*
	 * 查询足迹
	 */
	@ResponseBody
	@RequestMapping("getFootPrint")
	public Msg getFootPrint(@RequestParam("ids")String ids) {
		return novelService.getFootPrint(ids);
	}
	
	
	/*
	 * 查询数量
	 */
	@ResponseBody
	@RequestMapping("novelcount")
	public Msg novelcount(HttpServletRequest request) {
		User userRedis = untilService.getUserRedis(request);
		if(userRedis==null) {
			return Msg.fail("未登录");
		}
		return novelService.novelcount();
	}
	
	@ResponseBody
	@RequestMapping("UpdateNovelStartAndStop") 
	public Msg UpdateNovelStartAndStop(HttpServletRequest request,@RequestParam("state")Boolean state) {
		User userRedis = untilService.getUserRedis(request);
		if(userRedis==null) {
			return Msg.fail("未登录");
		}
		return pNovelService.UpdateNovelStartAndStop(state);
	}
	
	
	@ResponseBody
	@RequestMapping("getContentCount")
	public Msg getContentCount(@RequestParam("novelid") Integer novelid) {
		return novelService.getContentCount(novelid);
	}
}
