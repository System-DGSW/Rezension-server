package System.Rezension.studynote.repository;

import System.Rezension.studynote.entity.StudyNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyNoteRepository extends JpaRepository<StudyNote, Long> {
}
