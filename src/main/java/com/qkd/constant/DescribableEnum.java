package com.qkd.constant;


public enum DescribableEnum implements Describable{


	SUCCESS(1, "成功"),

	SYSTEM_ERROR(11000, "系统异常"),
	UNKNOWNUSER(11001, "用户名不存在"),
	ERROR_PWD(11002, "密码错误"),
	NULL_PWD(11003, "密码不能为空"),
	ERROR_OLD_PWD(11004, "原始密码不正确"),
	YZM_OVER_TIME(11005, "验证码失效"),
	YZM_NULL(11006, "验证码不能为空"),
	YZM_ERROR(11007, "验证码错误"),
	EXPIREDSESSION(11008, "session过期，请重新登录"),
	NoteEmpty(11009, "必填写段非空"),
	PARAMES_ERROR(11010, "参数异常"),
	/** 通用模块 11101-11199 */
	FILE_UPLOAD_ERROR(11101, "上传文件出错"),
	FILE_DEL_ERROR(11102, "删除文件出错"),
	FILE_NOT_EXIST(11103, "文件不存在"),
	FILE_DOWNLOAD_ERROR(11104, "上传下载出错"),
	/** 学生作业模块 12000-12099 */
	USER_IS_NOT_STUDENT(12000, "当前用户不是学生"),

	FAIL(99999, "失败")
	;

	private Integer code;// 描述编码
	private String msg;// 描述信息

	private DescribableEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}

}
