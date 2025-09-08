// Scheduler
package system.rezension.common.mail.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import system.rezension.common.mail.service.EmailProducer;
import system.rezension.domain.question.entity.Question;
import system.rezension.domain.question.entity.Subscription;
import system.rezension.domain.question.repository.QuestionRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionScheduler {

    private final QuestionRepository questionRepository;
    private final EmailProducer emailProducer;

    @Scheduled(cron = "0 0 9 * * *")
    @Transactional
    public void sendQuestion() {
        List<Question> dailyQuestions = questionRepository.findBySubscription(Subscription.DAILY);
        List<Question> oneTimeQuestions = questionRepository.findBySubscription(Subscription.ONE_TIME);

        for (Question q : dailyQuestions) {
            String email = q.getStudyNote().getMember().getEmail();
            emailProducer.sendEmail(email, "Rezension: 오늘의 문제", buildContent(q));
        }

        for (Question q : oneTimeQuestions) {
            String email = q.getStudyNote().getMember().getEmail();
            emailProducer.sendEmail(email, "Rezension: 오늘의 문제", buildContent(q));
            q.setSubscription(Subscription.NO);
        }
        questionRepository.saveAll(oneTimeQuestions);
    }

    private String buildContent(Question q) {
        return "<h2>오늘의 문제</h2>" +
                "<p><strong>문제:</strong> " + q.getQuestion() + "</p>" +
                "<details><summary>답변 보기</summary>" +
                "<p><strong>정답:</strong> " + q.getAnswer() + "</p></details>";
    }
}