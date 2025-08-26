package system.rezension.domain.studynote.dto.response;

import lombok.Builder;
import system.rezension.domain.studynote.entity.StudyNote;

import java.time.LocalDateTime;

@Builder
public record StudyNoteResponse(
        Long id,
        String title,
        String content,
        String username,
        LocalDateTime createdAt
) {
    // StudyNote를 넣으면 ResponseDto로 반환
    public static StudyNoteResponse fromStudyNoteEntity(StudyNote studyNote) {
        return StudyNoteResponse.builder()
                .content(studyNote.getContent())
                .createdAt(studyNote.getCreatedAt())
                .title(studyNote.getTitle())
                .username(studyNote.getMember().getUsername())
                .id(studyNote.getId())
                .build();
    }
}
