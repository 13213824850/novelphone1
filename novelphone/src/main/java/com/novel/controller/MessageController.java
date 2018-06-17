package com.novel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novel.bean.Message;
import com.novel.service.MessageService;
import com.novel.until.Msg;

@Controller
@RequestMapping("/message/")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	/*
	 * userid 用户id
	 * state 消息是否已读 0未读 1 已读 2 全部
	 */
	@ResponseBody
	@RequestMapping("getMessageCount")
	public Msg getMessageCount(@RequestParam("userid")Integer userid,@RequestParam("state")Integer state) {
	 List<Message> messageCount = messageService.getMessageCount(userid,state);
		return Msg.success().add("count", messageCount.size());
	}
	
	@ResponseBody
	@RequestMapping("getMessages")
	public Msg getMessages(@RequestParam("userid")Integer userid,Integer state) {
		List<Message> messageCount = messageService.getMessageCount(userid,state);
		return Msg.success().add("messages", messageCount);
	}
	
	/*
	 * 更改message的状态
	 */
	@ResponseBody
	@RequestMapping("updateStateMessage")
	public Msg updateStateMessage(@RequestParam("id")Integer id,@RequestParam("saveid")Integer saveid) {
		return messageService.updateStateMessage(id,saveid);
		
	}
	/*
	 * 删除message
	 */
	@ResponseBody
	@RequestMapping("deleteMessage")
	public Msg deleteMessage(@RequestParam("ids")String ids, @RequestParam("saveid")Integer saveid) {
			return messageService.deleteMessage(ids,saveid);
	}
}
