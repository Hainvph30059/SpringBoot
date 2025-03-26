package com.srping.identify_course.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Error"),
    USER_EXISTSED(1001, "User exsisted"),
    USERNAME_INVALID(1003, "User name must be at least 3 characters"),
    INVALID_PASSWORD(1004, "Password must be at least 8 characters"),
    INVALID_KEY(1005, "Invalid Message Key"),
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
