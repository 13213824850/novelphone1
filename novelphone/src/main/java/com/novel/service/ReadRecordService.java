package com.novel.service;

import java.util.List;

import com.novel.bean.ReadRecord;

public interface ReadRecordService {
	public List<ReadRecord> getReads(int userid);

	public boolean addRead(Integer userid, Integer novelid);

	public void updateReadisUpdate(Integer userid, Integer novelid);
	
}
