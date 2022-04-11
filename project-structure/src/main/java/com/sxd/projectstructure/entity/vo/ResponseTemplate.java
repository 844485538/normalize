package com.sxd.projectstructure.entity.vo;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * @author Li Jianxin
 * @JSONType 设置序列化时属性顺序
 *
 * 		HTTP返回结果实体类
 */
@JSONType(orders = { "code", "message", "data"})
public class ResponseTemplate {

	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 信息
	 */
	private String message;

	/**
	 * 数据
	 */
	private Object data;

	private ResponseTemplate(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 * 成功 - 无返回
	 *
	 * @return
	 */
	public static ResponseTemplate success() {
		return new ResponseTemplate(HttpResponseStatusEnum.SUCCESS.getCode(), "success", null);
	}

	/**
	 * 成功 - 有返回
	 *
	 * @param data
	 * @return
	 */
	public static ResponseTemplate success(Object data) {
		return new ResponseTemplate(HttpResponseStatusEnum.SUCCESS.getCode(), "success", data);
	}

	/**
	 * 失败
	 *
	 * @return
	 */
	public static ResponseTemplate fail() {
		return new ResponseTemplate(HttpResponseStatusEnum.FAIL.getCode(), "fail", null);
	}

	/**
	 * 失败 - 返回错误信息
	 * @param msg
	 * @return
	 */
	public static ResponseTemplate fail(String msg) {
		return new ResponseTemplate(HttpResponseStatusEnum.FAIL.getCode(), msg, null);
	}

	/**
	 * 未登录
	 *
	 * @param msg
	 * @return
	 */
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

/**
 * HTTP返回值状态
 */
enum HttpResponseStatusEnum {

	/**
	 * 成功
	 */
	SUCCESS("SUCCESS",6200),

	/**
	 * 未登陆
	 */
	NOT_LOGIN("NOT_LOGIN", 6401),

	/**
	 * 失败
	 */
	FAIL("FAIL",6500);

	private String description;

	private Integer code;

	private HttpResponseStatusEnum(String description, Integer code) {
		this.description = description;
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	void setDescription(String description) {
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	void setCode(Integer code) {
		this.code = code;
	}

}


