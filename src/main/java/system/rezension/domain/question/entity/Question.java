package system.rezension.domain.question.entity;

import jakarta.persistence.*;
import lombok.*;
import system.rezension.domain.studynote.entity.StudyNote;
import system.rezension.domain.studynote.entity.Subscription;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question; // 문제
    private String answer; // 정답

    @ManyToOne(fetch =  FetchType.LAZY) // 1:1 -> 1:N
    @JoinColumn(name = "study_note_id", nullable = false)
    private StudyNote studyNote;
}
