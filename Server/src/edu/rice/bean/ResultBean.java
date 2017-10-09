package edu.rice.bean;

public class ResultBean<T> {
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static <T> ResultBean<T> success(T data) {
		ResultBean<T> resultBean = new ResultBean<T>();
		resultBean.setCode(ErrorCode.SUCCESS.getCode());
		resultBean.setMsg(SUCCESS);
		resultBean.setData(data);
		return resultBean;
	}
	
	public static <T> ResultBean<T> success() {
		ResultBean<T> resultBean = new ResultBean<T>();
		resultBean.setCode(ErrorCode.SUCCESS.getCode());
		resultBean.setMsg(SUCCESS);
		return resultBean;
	}
	
	public static <T> ResultBean<T> failure(ErrorCode errorCode) {
		ResultBean<T> resultBean = new ResultBean<T>();
		resultBean.setCode(errorCode.getCode());
		resultBean.setMsg(ERROR);
		return resultBean;
	}
	
	public static <T> ResultBean<T> failure(String code) {
		ErrorCode errorCode = ErrorCode.getErrorCode(code);
		return failure(errorCode);
	}
	
	
	private static final String SUCCESS = "SUCCESS";
	private static final String ERROR = "ERROR";
	private String code;
	private String msg;
	private T data;
}