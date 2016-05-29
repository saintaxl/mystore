package com.mycloud.exception;

/**
 * @author I076649
 * */
public class SystemException extends BaseException {

    private static final long serialVersionUID = -6118079967918159773L;

    public SystemException(Throwable cause) {
        super(cause);
    }
    
    public SystemException(String errorCode) {
        super(errorCode);
    }

    public SystemException(String errorCode, Object... args) {
        super(errorCode, args);
    }
    
    public SystemException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }
    
    public SystemException(String errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }
}
