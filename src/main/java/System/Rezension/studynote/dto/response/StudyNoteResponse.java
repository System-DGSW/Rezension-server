package System.Rezension.studynote.dto.response;

public record StudyNoteResponse(
        Long id,
        String title,
        String content,
        String username
) {
}
