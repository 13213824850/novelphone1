package com.novel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novel.service.ReadRecordService;
import com.novel.until.Msg;

@RequestMapping("/read/")
@Controller
public class readController {

	@Autowired
	private ReadRecordService readRecordService;

	@ResponseBody
	@RequestMapping("addRead")
	public Msg addRead(@RequestParam("userid")Integer userid,@RequestParam("novelid")Integer novelid) {
		boolean flag = readRecordService.addRead(userid,novelid);
		if(!flag) {
			return Msg.fail("已加入");
		}
		return Msg.success();
	}

	@ResponseBody
	@RequestMapping("updateReadisUpdate")
	public Msg updateReadisUpdate(@RequestParam("userid")Integer userid,@RequestParam("novelid")Integer novelid) {
		readRecordService.updateReadisUpdate(userid,novelid);
		return Msg.success();
	}

	}
