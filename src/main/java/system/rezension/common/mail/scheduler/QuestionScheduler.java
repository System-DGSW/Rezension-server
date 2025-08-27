package system.rezension.common.mail.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import system.rezension.common.mail.service.SendQuestionService;
import system.rezension.domain.question.entity.Question;
import system.rezension.domain.question.entity.Subscription;
import system.rezension.domain.question.repository.QuestionRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionScheduler {

    private final SendQuestionService sendQuestionService;
    private final QuestionRepository questionRepository;

    @Scheduled(cron = "0 0 9 * * *") // 매일 9시
    @Transactional
    public void sendQuestion() {
        // DAILY 구독 문제 전부 가져오기
        List<Question> dailyQuestions = questionRepository.findBySubscription(Subscription.DAILY);

        // ONE_TIME 구독 문제 전부 가져오기
        List<Question> oneTimeQuestions = questionRepository.findBySubscription(Subscription.ONE_TIME);

        // DAILY 문제 전송
        for (Question q : dailyQuestions) {
            // Question → StudyNote → Member → email
            String email = q.getStudyNote().getMember().getEmail();
            sendQuestionService.sendQuestionMail(email, q);
        }

        // ONE_TIME 문제 전송 후 NO로 변경
        for (Question q : oneTimeQuestions) {
            String email = q.getStudyNote().getMember().getEmail();
            sendQuestionService.sendQuestionMail(email, q);

            q.setSubscription(Subscription.NO); // 한 번 보냈으니 끔
        }
        questionRepository.saveAll(oneTimeQuestions);
    }
}