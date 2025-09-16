package system.rezension.domain.question.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.rezension.domain.question.entity.Question;
import system.rezension.domain.studynote.entity.Subscription;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @EntityGraph(attributePaths = {"studyNote", "studyNote.member"})
    List<Question> findBySubscription(Subscription subscription);

}
