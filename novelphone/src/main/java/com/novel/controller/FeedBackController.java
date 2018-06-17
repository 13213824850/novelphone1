package com.novel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novel.bean.Feedback;
import com.novel.bean.User;
import com.novel.service.FeedBackService;
import com.novel.service.UntilService;
import com.novel.until.Msg;

@Controller
@RequestMapping("/feedback/")
public class FeedBackController {

	
	@Autowired
	private FeedBackService feedBackService;
	@Autowired
	private UntilService untilService;
	
	/*
	 * 添加意见
	 */
	@ResponseBody
	@RequestMapping("addfeedback")
	public Msg addFeedBack(Feedback feedback) {
		return feedBackService.addFeedBack(feedback);
	}
	
	
	/*
	 * 管理员查询反馈
	 */
	@ResponseBody
	@RequestMapping("getFeedBack")
	public Msg getFeedBack(HttpServletRequest request, @RequestParam("pn")Integer pn, @RequestParam("feedbacktype")String feedbacktype) {
		User user = untilService.getUserRedis(request);
		if(user==null) {
			return Msg.fail("未登录");
		}
		
		return feedBackService.getFeedBack(feedbacktype,pn);
	}
	/*
	 * 更改反馈状态
	 */
	@ResponseBody
	@RequestMapping("updateFeedbackstate")
	public Msg updateFeedbackstate(HttpServletRequest request, @RequestParam("id")Integer id) {
		User user = untilService.getUserRedis(request);
		if(user==null) {
			return Msg.fail("未登录");
		}
		return feedBackService.updateFeedbackstate(id);
	}
}
