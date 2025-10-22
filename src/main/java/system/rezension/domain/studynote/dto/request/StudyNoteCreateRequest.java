package system.rezension.domain.studynote.dto.request;

import system.rezension.domain.studynote.entity.Visibility;
import system.rezension.domain.studynote.entity.Subscription;

public record StudyNoteCreateRequest(
        String title,
        String content,
        Visibility visibility,
        Subscription subscription
) {
}
