package com.jinhao.crowd.exception;

/**
 * Created on 2020/10/15.
 *
 * @author Valar Morghulis
 */
public class LoginAcctRepeatException extends RuntimeException{

    static final long serialVersionUID = 9L;

    public LoginAcctRepeatException() {
        super();
    }

    public LoginAcctRepeatException(String message) {
        super(message);
    }

    public LoginAcctRepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctRepeatException(Throwable cause) {
        super(cause);
    }

    protected LoginAcctRepeatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
