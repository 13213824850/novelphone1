package com.novel.until;

import java.io.File;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import com.novel.redis.RedisClient;

@Service
public class VediUntil {

	@Value("${yulevedio}")
	private String yulevedio;//娱乐存放语音
	@Value("${vedio}")
	private String vediosrc;//语音文件名

	@Value("${vedioPath}")
	private String vedioPath;//语音写入路劲
	
	@Autowired
	private RedisClient jedisClient;
	
	
	public static final String APP_ID = "11020688";
	public static final String API_KEY = "5GK4EkNY5QpkTTry5GT0kKmn";
	public static final String SECRET_KEY = "TPspCjVt0RgYxuRywCpF3nolEr1ctxx8";
	public static AipOcr client = null;
    public static AipSpeech clientVedio = null;
	public static AipOcr getInstance() {

		if (client == null) {
			client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		}
		return client;
	}
	public static AipSpeech getInstanceVedio() {
		if (clientVedio == null) {
			clientVedio = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
		}
		return clientVedio;
	}

	
	public  String produceVedio(String vedio,String orderid) {
	   AipSpeech client = getInstanceVedio();
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		// 调用接口
		TtsResponse res = client.synthesis(vedio, "zh", 1, null);
		byte[] data = res.getData();
		if (data != null) {
			try {
				String vediomz =jedisClient.incr(vediosrc)+orderid.substring(0, 4)+".mp3";
				File file = new File(vedioPath);
				if(!file.exists()) {
					file.mkdirs();
				}
				Util.writeBytesToFileSystem(data,vedioPath+vediomz);
				return vediomz;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//娱乐合成
	public  String produceyuY(String message,String spd,String vol,String pit,String per) {
		AipSpeech client = getInstanceVedio();
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		 HashMap<String, Object> options = new HashMap<String, Object>();
		    options.put("spd", spd);
		    options.put("pit", pit);
		    options.put("per", per);
		    options.put("vol", vol);
		// 调用接口
		TtsResponse res = client.synthesis(message, "zh", 1, options);
		byte[] data = res.getData();
		if (data != null) {
			try {
				String vediomz =jedisClient.incr(vediosrc)+System.currentTimeMillis()+".mp3";
				File file = new File(yulevedio);
				if(!file.exists()) {
					file.mkdirs();
				}
				Util.writeBytesToFileSystem(data,yulevedio+vediomz);
				return vediomz;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
