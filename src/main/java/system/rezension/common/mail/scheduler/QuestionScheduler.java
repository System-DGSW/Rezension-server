package system.rezension.common.mail.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import system.rezension.common.mail.service.SendQuestionService;
import system.rezension.domain.studynote.entity.StudyNote;
import system.rezension.domain.studynote.entity.Subscription;
import system.rezension.domain.studynote.repository.StudyNoteRepository;
import system.rezension.domain.question.entity.Question;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionScheduler {

    private final SendQuestionService sendQuestionService;
    private final StudyNoteRepository studyNoteRepository;

    @Scheduled(cron = "0 0 9 * * *") // 매일 9시
    @Transactional
    public void sendQuestion() {
        // DAILY 구독 스터디노트 가져오기
        List<StudyNote> dailyNotes = studyNoteRepository.findBySubscription(Subscription.DAILY);

        // ONE_TIME 구독 스터디노트 가져오기
        List<StudyNote> oneTimeNotes = studyNoteRepository.findBySubscription(Subscription.ONE_TIME);

        // DAILY 문제 전송
        for (StudyNote note : dailyNotes) {
            String email = note.getMember().getEmail();
            for (Question q : note.getQuestion()) {
                sendQuestionService.sendQuestionMail(email, q);
            }
        }

        // ONE_TIME 문제 전송 후 구독 상태 변경
        for (StudyNote note : oneTimeNotes) {
            String email = note.getMember().getEmail();
            for (Question q : note.getQuestion()) {
                sendQuestionService.sendQuestionMail(email, q);
            }

            note.setSubscription(Subscription.NO); // 한 번 보냈으니 끔
        }
        studyNoteRepository.saveAll(oneTimeNotes);
    }
}
