package system.rezension.domain.question.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import system.rezension.domain.question.entity.Question;

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
            e.printStackTrace(); // 에러 출력 (추후 로깅 처리 추천)
        }
    }

    /* 질문 + 답변 메일 작성 */
    public void sendQuestionMail(String toMail, Question question) {
        String title = "스터디 문제 공유";

        String content = new StringBuilder()
                .append("<h2>오늘의 문제</h2>")
                .append("<p><strong>문제:</strong> ").append(question.getQuestion()).append("</p>")
                .append("<p><strong>답변:</strong> ").append(question.getAnswer()).append("</p>")
                .toString();

        mailSend(toMail, title, content);
    }
}
