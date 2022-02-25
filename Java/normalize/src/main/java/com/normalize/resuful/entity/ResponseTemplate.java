package com.normalize.resuful.entity;

import com.alibaba.fastjson.annotation.JSONType;
import com.normalize.resuful.enums.HttpResponseStatusEnum;

/**
 * @author Li Jianxin
 * @JSONType 设置序列化时属性顺序
 *
 * 		HTTP返回结果实体类
 */
@JSONType(orders = { "code", "message", "data"})
public class ResponseTemplate {
	
	private Integer code;
	
	private String message;
	
	private Object data;

	private ResponseTemplate(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public static ResponseTemplate success() {
		return new ResponseTemplate(HttpResponseStatusEnum.SUCCESS.getCode(), "success", null);
	}
	
	public static ResponseTemplate success(Object data) {
		return new ResponseTemplate(HttpResponseStatusEnum.SUCCESS.getCode(), "success", data);
	}
	
	public static ResponseTemplate fail() {
		return new ResponseTemplate(HttpResponseStatusEnum.FAIL.getCode(), "fail", null);
	}
	
	public static ResponseTemplate fail(String msg) {
		return new ResponseTemplate(HttpResponseStatusEnum.FAIL.getCode(), msg, null);
	}

	public static ResponseTemplate notLogin(String msg) {
		return new ResponseTemplate(HttpResponseStatusEnum.NOT_LOGIN.getCode(), msg, null);
	}

	/**
	 * 自定义状态返回
	 *
	 * @param status
	 * @param msg
	 * @param data
	 * @return
	 */
	public static ResponseTemplate custom(HttpResponseStatusEnum status, String msg, Object data) {
		return new ResponseTemplate(status.getCode(), msg, data);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}


