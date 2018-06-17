package com.novel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.bean.Message;
import com.novel.bean.MessageExample;
import com.novel.bean.MessageExample.Criteria;
import com.novel.mapper.MessageMapper;
import com.novel.service.MessageService;
import com.novel.until.Msg;

@Service
public class MessageServiceimpl implements MessageService{

	@Autowired
	private MessageMapper messageMapper;
	
	@Override
	public int addMessage(Message message) {
		int insert = messageMapper.insert(message);
		return insert;
	}

	@Override
	public List<Message> getMessageCount(Integer userid,Integer state) {
		MessageExample example = new MessageExample();
		Criteria criteria = example.createCriteria();
		criteria.andSaveidEqualTo(userid);
		if(state!=2) {
			criteria.andStateEqualTo(state);
		}
		example.setOrderByClause("state desc");
		List<Message> selectByExample = messageMapper.selectByExample(example);
		return selectByExample;
	}

	@Override
	public Msg updateStateMessage(Integer id, Integer saveid) {
		MessageExample example = new MessageExample();
		Criteria criteria = example.createCriteria();
		criteria.andSaveidEqualTo(saveid);
		criteria.andIdEqualTo(id);
		Message record = new Message();
		record.setState(1);
		try {
			messageMapper.updateByExampleSelective(record, example);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Msg.fail("更新异常");
		}
		return Msg.success();
	}

	@Override
	public Msg deleteMessage(String ids, Integer saveid) {
		MessageExample example = new MessageExample();
		Criteria criteria = example.createCriteria();
        //判断删除是一条还是多条
		if(ids.contains("-")) {
			List<Integer> listids = new ArrayList<>();
			String[] split = ids.split("-");
			for (String string : split) {
				listids.add(Integer.parseInt(string));
			}
			criteria.andIdIn(listids);
		}else {
			criteria.andIdEqualTo(Integer.parseInt(ids));
		}
		criteria.andSaveidEqualTo(saveid);
		messageMapper.deleteByExample(example);
		return null;
	}

}
