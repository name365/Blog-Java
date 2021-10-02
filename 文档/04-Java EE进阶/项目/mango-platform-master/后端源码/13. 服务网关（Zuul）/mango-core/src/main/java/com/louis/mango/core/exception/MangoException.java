package com.louis.mango.core.exception;

/**
 * 自定义异常
 * @author Louis
 * @date Jan 12, 2019
 */
public class MangoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;
    
    public MangoException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public MangoException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public MangoException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public MangoException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
