package com.novel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novel.service.impl.VedioMessageService;

@Controller
@RequestMapping("/vedio/")
public class VedioMessage {

	@Autowired
	private VedioMessageService vedioMessageService;


	
	//语音合成
	@ResponseBody
	@RequestMapping("yuying")
	public Object yuY(HttpServletRequest  request,@RequestParam("message")String message,@RequestParam("spd")String spd,@RequestParam("pit")String pit,
			@RequestParam("vol")String vol,@RequestParam("per")String per) {
		return vedioMessageService.yuY(per,message,spd,vol,pit);
	}
	
	//语音下载
	@RequestMapping("dowloadyu")
	public Object dowloadYu(@RequestParam("url")String url) {
		return vedioMessageService.dowloadYu(url);
	}
}
