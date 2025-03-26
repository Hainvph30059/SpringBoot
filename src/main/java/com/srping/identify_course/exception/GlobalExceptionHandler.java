package com.srping.identify_course.exception;

import com.srping.identify_course.dto.request.ApiReponse;
import org.springframework.expression.AccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // thông báo cho spring đây là nơi nhận và xử lý các ngoại lệ toàn cục
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiReponse> handlingRunTimeException(RuntimeException exception) { // bắt ngoại lệ với runtimeException
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setCode(1001);
        apiReponse.setMessage(exception.getMessage());

        return ResponseEntity.badRequest().body(apiReponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class) // bắt ngoại lệ validation
    ResponseEntity<ApiReponse> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setCode(errorCode.getCode());
        apiReponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiReponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiReponse> handlingAppException(AppException exception) { // bắt ngoại lệ với class tự định nghĩa
        ErrorCode errorCode = exception.getErrorCode();
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setCode(errorCode.getCode());
        apiReponse.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiReponse);
    }
// Bắt ngoại lệ chưa định nghĩa trong chương trình
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiReponse> handlingException(AppException exception) { // bắt ngoại lệ với class tự định nghĩa
        ErrorCode errorCode = exception.getErrorCode();
        ApiReponse apiReponse = new ApiReponse();
        apiReponse.setCode(errorCode.getCode());
        apiReponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiReponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiReponse> handlingAccessDeniedException(AccessDeniedException exception){
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                ApiReponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build()
        );
    }
}
