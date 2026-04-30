package com.everallity.ecommerce_backend.exception;

import com.everallity.ecommerce_backend.constant.ErrorCode;
import com.everallity.ecommerce_backend.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<Void>> runtimeExceptionHandler(AppException exception) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setErrorCode(exception.getErrorCode().getErrorCode());
        apiResponse.setSuccess(false);
        apiResponse.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> validationExceptionHandler(MethodArgumentNotValidException exception) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();

        String fieldMessage = Objects.requireNonNull(exception.getFieldError()).getDefaultMessage();

        ErrorCode errorCode = ErrorCode.valueOf(fieldMessage);
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setSuccess(false);
        apiResponse.setErrorCode(errorCode.getErrorCode());
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
