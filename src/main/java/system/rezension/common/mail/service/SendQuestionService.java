package system.rezension.common.mail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import system.rezension.domain.question.entity.Question;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendQuestionService {

    private final JavaMailSender javaMailSender;

    /* 발신자 이메일 (application.yml에서 설정) */
    @Value("${spring.mail.username}")
    private String serviceName;

    /* 이메일 전송 */
    public void mailSend(String toMail, String title, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(serviceName); // 서비스 계정 이메일
            helper.setTo(toMail); // 받는 사람
            helper.setSubject(title); // 메일 제목
            helper.setText(content, true); // HTML 가능
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error(String.valueOf(e)); // 에러 출력 (추후 로깅 처리 추천)
        }
    }

    /* 질문 + 답변 메일 작성 */
    @Async
    public void sendQuestionMail(String toMail, Question question) {
        String title = "Rezension: 오늘의 문제";

        String content = "<h2>오늘의 문제</h2>" +
                "<p><strong>문제:</strong> " + question.getQuestion() + "</p>" +
                "<details>" +
                "<summary>답변 보기</summary>" +
                "<p><strong>정답:</strong> " + question.getAnswer() + "</p>" +
                "</details>";

        mailSend(toMail, title, content);
    }
}
