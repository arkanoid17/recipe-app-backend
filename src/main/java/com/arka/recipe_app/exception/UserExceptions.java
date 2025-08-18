package com.arka.recipe_app.exception;

public class UserExceptions extends RuntimeException{
    public UserExceptions(String message) {
        super(message);
    }

    public UserExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExceptions(Throwable cause) {
        super(cause);
    }

    public UserExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
