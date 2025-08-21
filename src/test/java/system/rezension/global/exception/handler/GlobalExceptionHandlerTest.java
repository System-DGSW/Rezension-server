package system.rezension.global.exception.handler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import system.rezension.global.exception.CustomStatusException;
import system.rezension.global.exception.dto.response.ErrorResponse;
import system.rezension.global.exception.enums.GlobalExceptionStatusCode;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    @Test
    @DisplayName("CustomStatusException 발생 시 ErrorResponse 반환 형식 테스트")
    void handleCustomStatusException_returnsProperErrorResponse() {
        // given
        GlobalExceptionStatusCode status = GlobalExceptionStatusCode.BAD_REQUEST;
        CustomStatusException ex = new CustomStatusException(status);
        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        // when
        ResponseEntity<ErrorResponse> response = handler.handleCustomStatusException(ex);

        // then
        assertThat(response.getStatusCode().value()).isEqualTo(status.getStatusCode());
        ErrorResponse errorResponse = response.getBody();
        System.out.println("ErrorResponse JSON: " +
                "{\"status\":" + errorResponse.getStatus() +
                ", \"code\":\"" + errorResponse.getCode() + "\"" +
                ", \"message\":\"" + errorResponse.getMessage() + "\"}");
        assertThat(errorResponse).isNotNull();
        assertThat(errorResponse.getStatus()).isEqualTo(status.getStatusCode());
        assertThat(errorResponse.getMessage()).isEqualTo(status.getMessage());
        assertThat(errorResponse.getCode()).isEqualTo(status.getExceptionName());
    }
}
