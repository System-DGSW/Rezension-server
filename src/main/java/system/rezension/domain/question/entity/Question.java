package system.rezension.domain.question.entity;

import jakarta.persistence.*;
import lombok.*;
import system.rezension.domain.studynote.entity.StudyNote;

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

    private String question;
    private String answer;

    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "study_note_id", nullable = false,  unique = true)
    private StudyNote studyNote;

    @Enumerated(EnumType.STRING)
    private Subscription subscription;
}
