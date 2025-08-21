package system.rezension.global.exception.enums;

public interface StatusCode {
    int getStatusCode();
    String getMessage();
    // .name 메서드를 사용 시, 열거형을 정의한 이름대로 값을 반환한다
    // 예를 들어 현재 파일의 같은 패키지인 ExceptionStatusCode의 NOT_FOUND, BAD_REQUEST 등이 그 예시이다
    String getExceptionName();
}