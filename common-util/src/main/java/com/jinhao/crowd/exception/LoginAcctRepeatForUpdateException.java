package com.jinhao.crowd.exception;

/**
 * Created on 2020/10/15.
 *
 * @author Valar Morghulis
 */
public class LoginAcctRepeatForUpdateException extends RuntimeException{

    static final long serialVersionUID = 21L;

    public LoginAcctRepeatForUpdateException() {
        super();
    }

    public LoginAcctRepeatForUpdateException(String message) {
        super(message);
    }

    public LoginAcctRepeatForUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctRepeatForUpdateException(Throwable cause) {
        super(cause);
    }

    protected LoginAcctRepeatForUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
