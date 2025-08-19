package com.arka.recipe_app.exception;

public class OtpException extends RuntimeException{
    public OtpException(String message) {
        super(message);
    }

    public OtpException(String message, Throwable cause) {
        super(message, cause);
    }

    public OtpException(Throwable cause) {
        super(cause);
    }

    public OtpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
