package system.rezension.domain.studynote.dto.request;

import system.rezension.domain.studynote.entity.Visibility;

public record StudyNoteCreateRequest(
        String title,
        String content,
        Visibility visibility
) {
}
