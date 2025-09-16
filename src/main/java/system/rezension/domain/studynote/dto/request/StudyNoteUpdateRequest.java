package system.rezension.domain.studynote.dto.request;

import system.rezension.domain.question.entity.Visibility;

import java.util.Optional;

public record StudyNoteUpdateRequest(
        Optional<String> title,
        Optional<String> content,
        Optional<Visibility> visibility
) {
}
