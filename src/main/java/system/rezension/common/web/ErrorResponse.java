package system.rezension.common.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import system.rezension.global.exception.enums.StatusCode;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        Integer code,
        String message,
        LocalDateTime timestamp,
        Map<String, String> details
) {

    public static ErrorResponse of(StatusCode statusCode) {
        return ErrorResponse.builder()
                .code(statusCode.getStatusCode())
                .message(statusCode.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ErrorResponse of(
            StatusCode statusCode,
            Map<String, String> details
    ) {
        return ErrorResponse.builder()
                .code(statusCode.getStatusCode())
                .message(statusCode.getMessage())
                .timestamp(LocalDateTime.now())
                .details(details)
                .build();
    }
}
