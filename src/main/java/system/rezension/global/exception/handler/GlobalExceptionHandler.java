package system.rezension.global.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import system.rezension.domain.member.exception.MemberNotFoundException;
import system.rezension.global.exception.CustomStatusException;
import system.rezension.global.exception.dto.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomStatusException.class)
    public ResponseEntity<ErrorResponse> handleClassCastException(CustomStatusException ex) {
        return ResponseEntity
                .status(ex.getStatus().getStatusCode())
                .body(ErrorResponse.errorResponse(ex.getStatus()));
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFoundException(CustomStatusException ex) {
        return ResponseEntity
                .status(ex.getStatus().getStatusCode())
                .body(ErrorResponse.errorResponse(ex.getStatus()));
    }
}
