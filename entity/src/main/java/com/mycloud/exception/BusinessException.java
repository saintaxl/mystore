package com.mycloud.exception;

/**
 * @author I076649
 * */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = -6370267525322739433L;

    public BusinessException(Throwable cause) {
    	super(cause);
    }
    
    public BusinessException(String errorCode) {
        super(errorCode);
    }

    public BusinessException(String errorCode, Object... args) {
        super(errorCode, args);
    }
    
    public BusinessException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public BusinessException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }
}
