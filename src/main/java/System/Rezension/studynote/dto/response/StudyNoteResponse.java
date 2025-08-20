package System.Rezension.studynote.dto.response;

import java.time.LocalDateTime;

public record StudyNoteResponse(
        Long id,
        String title,
        String content,
        String username,
        LocalDateTime createdAt
) {
}
