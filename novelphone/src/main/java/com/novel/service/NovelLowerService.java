package com.novel.service;

import com.novel.until.Msg;

public interface NovelLowerService {

	void updateState(int i, Integer novelid);

	Msg getNovelLowerState(Integer novelid);

	void addLower(Integer novelid);

}
