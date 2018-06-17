package com.novel.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.bean.NovelLower;
import com.novel.mapper.NovelLowerMapper;
import com.novel.service.NovelLowerService;
import com.novel.until.Msg;

@Service
public class NovelLowerServiceimpl implements NovelLowerService{

	
	@Autowired
	private NovelLowerMapper lowerMapper;
	@Override
	public void updateState(int state, Integer novelid) {
		NovelLower record  = new NovelLower();
		record.setNovelid(novelid);
		record.setIslower(state);
		lowerMapper.updateByPrimaryKeySelective(record);
		
	}
	@Override
	public Msg getNovelLowerState(Integer novelid) {
		NovelLower selectByPrimaryKey = lowerMapper.selectByPrimaryKey(novelid);
		if(selectByPrimaryKey!=null) {
			if(selectByPrimaryKey.getIslower()==1) {
				return Msg.fail("该小说因违规下架 暂时无法观看");
			}
		}
		return Msg.success();
	}
	@Override
	public void addLower(Integer novelid) {
		NovelLower record = new NovelLower();
		record.setIslower(1);
		record.setCreatetime(new Date());
		record.setNovelid(novelid);
		record.setUpdatetime(new Date());
		lowerMapper.insert(record);
		
	}

}
