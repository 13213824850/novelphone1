package com.novel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.novel.bean.Chapter;
import com.novel.bean.ChapterExample;
import com.novel.bean.Content;
import com.novel.bean.ContentExample;
import com.novel.bean.ContentKey;
import com.novel.bean.Message;
import com.novel.bean.Novel;
import com.novel.bean.NovelExample;
import com.novel.bean.NovelExample.Criteria;
import com.novel.bean.Report;
import com.novel.bean.User;
import com.novel.mapper.ChapterMapper;
import com.novel.mapper.ContentMapper;
import com.novel.mapper.NovelMapper;
import com.novel.redis.RedisClient;
import com.novel.service.MessageService;
import com.novel.service.NovelLowerService;
import com.novel.service.NovelService;
import com.novel.service.ReportService;
import com.novel.until.Msg;

import redis.clients.jedis.JedisPool;

@Service
public class NovelServiceimpl implements NovelService {


	@Value("${Recommend}")
	private String Recommend;
	@Autowired
	private NovelMapper novelMapper;
	@Autowired
	private ContentMapper contentMapper;
	@Autowired
	private ChapterMapper chapterMapper;
	@Autowired
	private RedisClient redisClient;
	@Autowired
	private ReportService reportService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private NovelLowerService  novelLowerService;
	
	public List<Novel> getNovels(String novelName) {
		NovelExample example = new NovelExample();
		Criteria criteria = example.createCriteria();
		criteria.andTitleLike("%"+novelName+"%");
		List<Novel> selectByExample = novelMapper.selectByExample(example);
		return selectByExample;
	}

	@Override
	public Content getChapterContent(Integer contentid) {
		ContentKey key = new ContentKey();
		Content content = contentMapper.selectByPrimaryKey(key);
		return content;
	}

	@Override
	public Novel getNovel(Integer id) {
		Novel novelbd = novelMapper.selectByPrimaryKey(id);
		return novelbd;
	}

	@Override
	public List<Chapter> getChapters(int novelId) {
		ChapterExample example = new ChapterExample();
		ChapterExample.Criteria criteria = example.createCriteria();
		criteria.andNovelidEqualTo(novelId);
		example.setOrderByClause("num asc");
		 List<Chapter> chapters = chapterMapper.selectByExample(example);
		return chapters;
	}

	@Override
	public List<Novel> getUserNovels(List<Integer> novelids) {
		NovelExample example = new NovelExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(novelids);
		List<Novel> userNovels = novelMapper.selectByExample(example);
		return userNovels;
	}

	@Override
	public Content readNovel(Integer contentid,Integer novelid) {
		ContentKey key = new ContentKey(contentid, novelid);
		Content content = contentMapper.selectByPrimaryKey(key);
		return content;
	}

	@Override
	public List<Novel> getRecommendNovel(int start, int end,String type) {
		NovelExample example = new NovelExample();
		Criteria criteria = example.createCriteria();
		List<Integer> zincrB = new ArrayList<>();
		if (type != null) {
			zincrB = redisClient.getZincrB(type, start, end);
		}else {
			// 先从redis获取排行值
			zincrB = redisClient.getZincrB(Recommend, start, end);
		}
		
		if (zincrB.size() == 0) {
			return null;
		}
		criteria.andIdIn(zincrB);
		List<Novel> recommendNovels = novelMapper.selectByExample(example);
		return recommendNovels;
	}

	@Override
	public Msg deleteNovel(Report report) {
		//查询是否存在
		Novel selectByPrimaryKey = novelMapper.selectByPrimaryKey(report.getNovelid());
		if(selectByPrimaryKey==null) {
		return Msg.fail("删除的小说不存在");
		}
		novelLowerService.addLower(report.getNovelid());
		
	/*	//开始删除
		ChapterExample example = new ChapterExample();
		com.novel.bean.ChapterExample.Criteria criteria = example.createCriteria();
		criteria.andNovelidEqualTo(report.getNovelid());
		chapterMapper.deleteByExample(example);
		//删除内容
		ContentExample contentExample = new ContentExample();
		com.novel.bean.ContentExample.Criteria criteriacontent = contentExample.createCriteria();
		criteriacontent.andNovelidEqualTo(report.getNovelid());
		contentMapper.deleteByExample(contentExample);
		//删除小说名字
		novelMapper.deleteByPrimaryKey(report.getNovelid());*/
		//修改举报记录
		reportService.updateState(report.getId());
		//向举报人发送信息
		Message message = new Message();
		message.setTitle("举报消息反馈");
		String body = "尊敬的用户您好，您举报的小说"+selectByPrimaryKey.getTitle()+""
				+ "我们已经在"+new Date()+"处理了。处理结果如下<br>"
				+"由用户"+selectByPrimaryKey.getProduct()+"提供的小说因为"+report.getReporttype()+"原因现已删除。"
				+"同时我们对此声表道歉 ，信息你的反馈";
		message.setBody(body);
		message.setSendname("Admin:");
		message.setSaveid(report.getUserid());
		message.setCreatetime(new Date());
		message.setUpdatetime(new Date());
		messageService.addMessage(message);
		return Msg.success();
	}

	@Override
	public Msg getFootPrint(String ids) {
		String[] split = ids.split("-");
		List<Integer> listid = new ArrayList<>();
		for (String string : split) {
			listid.add(Integer.parseInt(string));
		}
		NovelExample example = new NovelExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(listid);
		List<Novel> recommendNovels = novelMapper.selectByExample(example);
		return Msg.success().add("recommendNovels", recommendNovels);
	}

	@Override
	public Msg novelcount() {
		int novelcount = novelMapper.selectCount();
		return Msg.success().add("novelcount", novelcount);
	}
	@Override
	public Novel getetNovelByid(Integer id) {
		Novel novel = novelMapper.selectByPrimaryKey(id);
		return novel;
	}

	@Override
	public Msg getContentCount(Integer novelid) {
		Integer count = chapterMapper.selectContentCount(novelid);
		return Msg.success().add("count", count);
	}


}
