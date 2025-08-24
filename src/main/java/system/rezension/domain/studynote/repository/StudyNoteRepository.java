package system.rezension.domain.studynote.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import system.rezension.domain.member.entity.Member;
import system.rezension.domain.studynote.entity.StudyNote;

import java.util.List;

public interface StudyNoteRepository extends JpaRepository<StudyNote, Long> {
    List<StudyNote> findAllByMember(Member member);

    Page<StudyNote> findByMemberId(Long memberId, Pageable pageable);

}
