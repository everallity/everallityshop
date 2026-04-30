package com.everallity.ecommerce_backend.constant;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {

    EMAIL_INVALID(200, "Invalid format of email", HttpStatus.BAD_REQUEST),
    CREDENTIALS_INVALID(400,"Credential Invalid", HttpStatus.BAD_REQUEST),
    INVALID_ROLE(301, "No such role exist", HttpStatus.NOT_FOUND),
    DUPLICATED_EMAIL(201, "This email has already been used", HttpStatus.IM_USED),
    PASSWORD_LENGTH_INVALID(300, "Invalid length of password", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_FOUND(100,"Account not existed", HttpStatus.NOT_FOUND),
    NONE(999,"Undefined error", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_JWT_TOKEN_GENERATION(998,"Failed to generate jwt token", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_JWT_TOKEN_VERIFICATION(997, "Failed to verify jwt token", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1001, "Unauthenticated request", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1000,"You don't have permission to perform this action", HttpStatus.FORBIDDEN);


    private final int errorCode;
    private final String message;
    private final HttpStatusCode httpStatusCode;

    ErrorCode(int errorCode, String message, HttpStatus httpStatusCode) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
