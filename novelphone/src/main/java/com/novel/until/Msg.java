package com.novel.until;

import java.util.HashMap;
import java.util.Map;

public class Msg {
	private int code;//200success 400fail
	private String msg;
    
	private Map<String, Object> extend = new HashMap<String, Object>();

	public static Msg success(){
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("成功");
		return result;
	}
	
	public static Msg fail(String msg){
		Msg result = new Msg();
		result.setCode(400);
		result.setMsg(msg);
		return result;
		}
	
	public  Msg add(String key,Object value) {
		 this.getExtend().put(key, value);
		 return this;
	}
	
	
	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Msg() {
		// TODO Auto-generated constructor stub
	}

	public Msg(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
}
