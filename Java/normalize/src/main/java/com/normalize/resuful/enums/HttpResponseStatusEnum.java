package com.normalize.resuful.enums;

/**
 * @author liJianxin
 *
 * HTTP请求返回值状态码
 */
public enum HttpResponseStatusEnum {

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

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
