package com.toolbox;


public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;

	public AppException(String code, String message) {
		super(createFriendlyErrMsg(message));
		this.code = code;
	}

	public AppException(String code, Throwable throwable) {
		super(throwable);
		this.code = code;
	}

	public AppException(String code, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;
	}

	/**
	 * 是异常处理类
	 * 
	 * @param msgBody
	 * @return
	 */
	private static String createFriendlyErrMsg(String msgBody) {

		String prefixStr = "抱歉，";
		String suffixStr = "";

		StringBuffer friendlyErrMsg = new StringBuffer();
		friendlyErrMsg.append(prefixStr);
		friendlyErrMsg.append(msgBody);
		friendlyErrMsg.append(suffixStr);

		return friendlyErrMsg.toString();

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}