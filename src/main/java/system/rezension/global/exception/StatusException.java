package system.rezension.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import system.rezension.global.exception.enums.StatusCode;

@Getter
@RequiredArgsConstructor
public class StatusException extends RuntimeException {
    private final StatusCode statusCode;
}
