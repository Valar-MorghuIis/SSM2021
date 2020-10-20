package com.jinhao.crowd.exception;

/**
 * Created on 2020/10/14.
 *
 * @author Valar Morghulis
 */
public class AccessForbiddenException extends RuntimeException {

    static final long serialVersionUID = 2L;

    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    protected AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
