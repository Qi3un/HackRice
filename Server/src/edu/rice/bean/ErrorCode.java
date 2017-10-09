package edu.rice.bean;


/**
 * 标准错误码表
 * @author lyu
 */
public enum ErrorCode {
	
	SUCCESS("0000","ok"),
	
	DEFAULT_ERROR("9000", "undefied"),
	
	PERMISSION_DENY("1001", "permission denied"),
	
	ENROLL_FAIL("1002", "enroll failed"),
	
	WRONG_PARAMS("1003", "wrong params"),
	
	DUPLICATES("1004", "duplication exists");
	
//	REQUEST_EXPIRE("1002", "request expired"),	
//	REQUEST_DENY("1003", "request denied"),
//	VERIFY_FAIL("1004", "verification failed"),
//	PERMISSION_DENY("1005", "permission denied"),
//	
//	INVALID_USER_ID("2001", "invalid user id"),
//	INVALID_USER_TYPE("2002", "invalid user type"),
//	WRONG_PARAMS("2003", "wrong params"),
//	INVALID_PROJECT_ID("2004", "invalid project id"),
//	
//	
//	USER_EXIST("3001", "user already exists"),
//	PROJECT_EXIST("3002", "project already exists"),
//	FILE_NOT_EXIST("3003", "file not exists"),
//	FIlE_ERROR("3004", "unknown file error");
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static ErrorCode getErrorCode(String code) {
		for (ErrorCode errorCode : ErrorCode.values())
			if (errorCode.getCode().equals(code))
				return errorCode;
		return ErrorCode.DEFAULT_ERROR;
	}
	
	private final String code;
	private final String message;
	
	private ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
