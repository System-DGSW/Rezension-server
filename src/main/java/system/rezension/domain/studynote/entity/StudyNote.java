package system.rezension.domain.studynote.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import system.rezension.domain.member.entity.Member;
import system.rezension.domain.question.entity.Question;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudyNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",  nullable = false)
    private Member member;

    @OneToMany(mappedBy = "studyNote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> question;

    @Enumerated(EnumType.STRING)
    private Subscription subscription;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;
}
