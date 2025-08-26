package system.rezension.global.exception.dto.response;

import lombok.Builder;
import lombok.Getter;
import system.rezension.global.exception.enums.StatusCode;

@Getter
public final class ErrorResponse {
    private final int status;
    private final String code;
    private final String message;

    @Builder
    public ErrorResponse(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse errorResponse(StatusCode status) {
        return ErrorResponse.builder()
                .status(status.getStatusCode())
                .code(status.getExceptionName())
                .message(status.getMessage())
                .build();
    }

    public static ErrorResponse of(int status, String code, String message) {
        return ErrorResponse.builder()
                .message(message)
                .status(status)
                .code(code)
                .build();
    }
}
