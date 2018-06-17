package com.novel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.novel.bean.Feedback;
import com.novel.bean.FeedbackExample;
import com.novel.bean.FeedbackExample.Criteria;
import com.novel.mapper.FeedbackMapper;
import com.novel.service.FeedBackService;
import com.novel.until.Msg;

@Service
public class FeedBackServiceimpl implements FeedBackService{

	
	@Autowired
	private FeedbackMapper feedbackMapper;
	
	
	@Override
	public Msg addFeedBack(Feedback feedback) {
		feedback.setState(0);
		feedback.setCreatetime(new Date());
		feedback.setUpdatetime(new Date());
		feedbackMapper.insert(feedback);
		return Msg.success();
	}


	@Override
	public Msg getFeedBack(String feedbacktype, Integer pn) {
		FeedbackExample example = new FeedbackExample();
		Criteria criteria = example.createCriteria();
		criteria.andFeedbacktypeEqualTo(feedbacktype);
		PageHelper.startPage(pn,5);
		List<Feedback> selectByExample = feedbackMapper.selectByExample(example);
		PageInfo pageinfo = new PageInfo<>(selectByExample, 5);
		return Msg.success().add("pageinfo", pageinfo);
	}


	@Override
	public Msg updateFeedbackstate(Integer id) {
		Feedback record = new Feedback();
		record.setId(id);
		record.setState(1);
		try {
			feedbackMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Msg.fail("异常");
		}
		return Msg.success();
	}

}
