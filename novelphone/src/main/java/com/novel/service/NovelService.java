package com.novel.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.novel.bean.Chapter;
import com.novel.bean.Content;
import com.novel.bean.Novel;
import com.novel.bean.Report;
import com.novel.until.Msg;

public interface NovelService {
	public List<Novel> getNovels(String novelName);

	public Content getChapterContent(Integer contentid);

	public Novel getNovel(Integer id);

	public List<Chapter> getChapters(int novelId);

	public List<Novel> getUserNovels(List<Integer> novelids);

	public Content readNovel(Integer contentid, Integer novelid);

	// 获取推荐书籍


	List<Novel> getRecommendNovel(int start, int end, String type);

	public Msg deleteNovel(Report report);

	public Msg getFootPrint(String ids);

	public Msg novelcount();

	Novel getetNovelByid(Integer id);

	public Msg getContentCount(Integer novelid);
}
