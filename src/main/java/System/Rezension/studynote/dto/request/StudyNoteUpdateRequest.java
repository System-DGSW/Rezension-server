package System.Rezension.studynote.dto.request;

import java.util.Optional;

public record StudyNoteUpdateRequest(
        Long id,
        Optional<String> title,
        Optional<String> content
) {
}
