package com.aravind.rest.exceptions;

import com.aravind.rest.utils.ErrorCode;

/**
 * A Custom exception class that wraps all the exceptions.
 *
 * @author arvind.n
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1L;

    private final ErrorCode errorCode;
    private final String errorMessage;

    public BusinessException(ErrorCode errorCode, String errorMessage, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}


