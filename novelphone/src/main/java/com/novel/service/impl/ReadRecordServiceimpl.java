package com.novel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.novel.bean.Novel;
import com.novel.bean.ReadRecord;
import com.novel.bean.ReadRecordExample;
import com.novel.bean.ReadRecordExample.Criteria;
import com.novel.mapper.ReadRecordMapper;
import com.novel.redis.RedisClient;
import com.novel.service.NovelService;
import com.novel.service.ReadRecordService;
import com.novel.until.Until;

@Service
public class ReadRecordServiceimpl implements ReadRecordService{

	@Value("${Recommend}")
	private String Recommend;
	@Autowired
	private RedisClient redisClient;
	@Autowired
	private ReadRecordMapper readRecordMapper;
	@Autowired
	private NovelService novelService;
	
	@Override
	public List<ReadRecord> getReads(int userid) {
		ReadRecordExample example = new ReadRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		List<ReadRecord> readRecords = readRecordMapper.selectByExample(example);
		return readRecords;
	}

	@Override
	public boolean addRead(Integer userid, Integer novelid) {
		
		
		//查询书籍是否存在
		Novel novel = novelService.getetNovelByid(novelid);
		if(novel==null) {
			return false;
		}
		ReadRecord record = new ReadRecord();
		record.setUserid(userid);
		record.setNovelid(novelid);
		ReadRecordExample example = new ReadRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		criteria.andNovelidEqualTo(novelid);
		List<ReadRecord> record1 = readRecordMapper.selectByExample(example);
		if(record1.size()==1) {
			return false;
		}
		record.setIsupdate(0);
		int insert = readRecordMapper.insert(record);
		if(insert>0) {
			String noveltype = Until.getNovelType(novel.getType());
			if(noveltype==null) {
				return true;
			}
			//向redis中添加一条推荐榜记录novel：recommend( 推荐key)
			redisClient.Zincrby(Recommend, novelid.toString(), 1d);
			redisClient.Zincrby(noveltype, novelid.toString(), 1d);
			return true;
		}
		return false;
	}

	@Override
	public void updateReadisUpdate(Integer userid, Integer novelid) {
		ReadRecord record = new ReadRecord();
		record.setIsupdate(0);
		ReadRecordExample example = new ReadRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		criteria.andNovelidEqualTo(novelid);
		readRecordMapper.updateByExampleSelective(record, example);
		
	}

	
	

}
