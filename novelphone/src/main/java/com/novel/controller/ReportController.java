package com.novel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novel.bean.Report;
import com.novel.bean.User;
import com.novel.redis.RedisClient;
import com.novel.service.ReportService;
import com.novel.service.UntilService;
import com.novel.until.Msg;

@RequestMapping("/report/")
@Controller
public class ReportController {

	@Autowired
	private UntilService untilService;
	@Autowired
	private RedisClient redisClient;
	@Autowired
	private ReportService reportService;
	
	/*
	 * 添加举报信息 
	 */
	@ResponseBody
	@RequestMapping("addReport")
	public Msg addReport(Report report) {
		int result = reportService.addReport(report);
		if(result==0) {
			return Msg.fail("举报失败");
		}
		return Msg.success();
	}
	/*
	 * 查询report
	 */
	@ResponseBody
	@RequestMapping("getReport")
	public Msg getReport(HttpServletRequest request, @RequestParam("pn")Integer pn,@RequestParam("reporttype")String reporttype) {
		User user = untilService.getUserRedis(request);
		if(user==null) {
			return Msg.fail("未登录");
		}
		return reportService.getReport(reporttype,pn);
	}
	
	/*
	 * 更爱其状态为已读
	 */
	@ResponseBody
	@RequestMapping("updateState")
	public Msg updateState(HttpServletRequest request, @RequestParam("id")Integer id) {
		User user = untilService.getUserRedis(request);
		if(user==null) {
			return Msg.fail("未登录");
		}
		return reportService.updateState(id);
	}
	
	
	
}
