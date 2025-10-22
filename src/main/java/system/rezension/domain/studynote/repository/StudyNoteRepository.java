package system.rezension.domain.studynote.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import system.rezension.domain.member.entity.Member;
import system.rezension.domain.studynote.entity.StudyNote;
import system.rezension.domain.studynote.entity.Visibility;
import system.rezension.domain.studynote.entity.Subscription;

import java.util.List;

public interface StudyNoteRepository extends JpaRepository<StudyNote, Long> {
    List<StudyNote> findAllByMember(Member member);

    @EntityGraph(attributePaths = {"member"})
    Page<StudyNote> findByMemberId(Long memberId, Pageable pageable);

    @EntityGraph(attributePaths = {"member"})
    Page<StudyNote> findByMemberIdAndVisibility(Long memberId, Visibility visibility, Pageable pageable);

    List<StudyNote> findBySubscription(Subscription subscription);
}
