package System.Rezension.question.entity;

import System.Rezension.studynote.entity.StudyNote;
import jakarta.persistence.*;
import lombok.*;

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
}
