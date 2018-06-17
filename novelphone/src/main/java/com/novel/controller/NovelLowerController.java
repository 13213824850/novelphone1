package com.novel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novel.service.NovelLowerService;
import com.novel.until.Msg;


@RequestMapping("/novellower/")
@Controller
public class NovelLowerController {
	
	@Autowired
	private NovelLowerService novelLowerService;
	
	/*
	 * 判断小说是否下架
	 */
	@RequestMapping("getstate")
	@ResponseBody
	public Msg getNovelLowerState(@RequestParam("novelid")Integer novelid) {
		return novelLowerService.getNovelLowerState(novelid);
	}

}
