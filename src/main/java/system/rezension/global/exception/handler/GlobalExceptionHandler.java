package system.rezension.global.exception.handler;

import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import system.rezension.global.exception.CustomStatusException;
import system.rezension.global.exception.dto.response.ErrorResponse;
import system.rezension.global.exception.enums.GlobalExceptionStatusCode;

import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomStatusException.class)
    public ResponseEntity<ErrorResponse> handleCustomStatusException(CustomStatusException ex) {
        return ResponseEntity
                .status(ex.getStatus().getStatusCode())
                .body(ErrorResponse.errorResponse(ex.getStatus()));
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchAlgorithmException(NoSuchAlgorithmException ex) {
        return ResponseEntity
                .status(GlobalExceptionStatusCode.ENCODE_FAILED.getStatusCode()) // 500
                .body(ErrorResponse.errorResponse(GlobalExceptionStatusCode.ENCODE_FAILED));
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<ErrorResponse> handlePropertyValueException(PropertyValueException ex) {
        return ResponseEntity
                .status(GlobalExceptionStatusCode.REQUIRE_ARGUMENTS.getStatusCode()) // 400
                .body(ErrorResponse.errorResponse(GlobalExceptionStatusCode.REQUIRE_ARGUMENTS));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(GlobalExceptionStatusCode.REQUIRE_ARGUMENTS.getStatusCode()) // 400
                .body(ErrorResponse.errorResponse(GlobalExceptionStatusCode.REQUIRE_ARGUMENTS));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(GlobalExceptionStatusCode.REQUIRE_ARGUMENTS.getStatusCode()) // 400
                .body(ErrorResponse.errorResponse(GlobalExceptionStatusCode.REQUIRE_ARGUMENTS));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ResponseEntity
                .status(GlobalExceptionStatusCode.REQUIRE_ARGUMENTS.getStatusCode()) // 400
                .body(ErrorResponse.errorResponse(GlobalExceptionStatusCode.REQUIRE_ARGUMENTS));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity
                .status(GlobalExceptionStatusCode.BAD_REQUEST.getStatusCode()) // 400
                .body(ErrorResponse.errorResponse(GlobalExceptionStatusCode.BAD_REQUEST));
    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<ErrorResponse> handleClassCastException(ClassCastException ex) {
        return ResponseEntity
                .status(GlobalExceptionStatusCode.INTERNAL_SERVER.getStatusCode()) // 500
                .body(ErrorResponse.errorResponse(GlobalExceptionStatusCode.INTERNAL_SERVER));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity
                .status(GlobalExceptionStatusCode.AlREADY_CREATED_DATA.getStatusCode()) // 409
                .body(ErrorResponse.errorResponse(GlobalExceptionStatusCode.AlREADY_CREATED_DATA));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity
                .status(GlobalExceptionStatusCode.AlREADY_CREATED_DATA.getStatusCode()) // 409
                .body(ErrorResponse.errorResponse(GlobalExceptionStatusCode.AlREADY_CREATED_DATA));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        return ResponseEntity
                .status(GlobalExceptionStatusCode.PAYLOAD_TOO_LARGE.getStatusCode()) // 413
                .body(ErrorResponse.errorResponse(GlobalExceptionStatusCode.PAYLOAD_TOO_LARGE));
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse> handleSignatureException(SignatureException ex) {
        return ResponseEntity
                .status(GlobalExceptionStatusCode.JWT_SIGNATURE.getStatusCode()) // 400
                .body(ErrorResponse.errorResponse(GlobalExceptionStatusCode.JWT_SIGNATURE));
    }
}
