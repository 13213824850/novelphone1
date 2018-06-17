package com.novel.service;

import java.util.List;

import com.novel.bean.Chapter;
import com.novel.bean.Novel;
import com.novel.until.Msg;

public interface PNovelService {
	public Object getHtml(String url);
	public List<Novel> getNovels(String novelName);
	public List<Chapter> getChapters(String url, int novelid);
	public void getContents(List<Chapter> chapters);
	public Novel getNovel(Novel novel);
	public void AsynAddNovel(Novel novel);
	public void addNovel(Novel novel);
	public Novel GetNovelState(Novel novel);
	//30分钟更新一次
	public Msg clockNovelUpdate();
	//3小时更新一次
	public Msg clockNovelUpdate3();
	//6小时更新一次
	public Msg clockNovelUpdate6();
	//13.5小时更新一次
	public Msg clockNovelUpdate13();
	//凌晨3点任务 更新其状态
	public Msg clockNovelUpdateState();
	//判断是否更新
	public Msg UpdateNovelStartAndStop(Boolean state);
	Msg updatenovelClock();
}
