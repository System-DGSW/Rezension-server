package system.rezension.domain.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.rezension.domain.question.entity.Question;
import system.rezension.domain.question.entity.Subscription;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    // DAILY 구독 문제 조회
    List<Question> findBySubscription(Subscription subscription);

    // ONE_TIME 구독 문제 조회
    List<Question> findBySubscriptionAndIdNotIn(Subscription subscription, List<Long> sentIds);

    Question findTodayDailyQuestion();
}
