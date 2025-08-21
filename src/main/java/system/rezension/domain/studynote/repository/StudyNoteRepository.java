package system.rezension.domain.studynote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import system.rezension.domain.studynote.entity.StudyNote;

public interface StudyNoteRepository extends JpaRepository<StudyNote, Long> {
}
