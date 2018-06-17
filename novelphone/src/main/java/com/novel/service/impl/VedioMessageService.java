package com.novel.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.novel.until.Msg;
import com.novel.until.VediUntil;

@Service
public class VedioMessageService {

	@Value("${yulevedio}")
	private String yulevedio;// 娱乐存放语音
	@Autowired
	private VediUntil vediUntil;


	public Object yuY(String per, String message, String spd, String vol, String pit) {
		String produceyuY = vediUntil.produceyuY(message, spd, vol, pit, per);
		return Msg.success().add("url", produceyuY);
	}

	public ResponseEntity<byte[]> dowloadYu(String url) {
		File file = new File(yulevedio + url);
		if (!file.exists()) {
			return null;
		}
		byte[] body = null;
		try {
			InputStream in = new FileInputStream(file);
			body = new byte[in.available()];
			in.read(body);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attchement;filename=" + file.getName());
			HttpStatus statusCode = HttpStatus.OK;
			ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
			return entity;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
