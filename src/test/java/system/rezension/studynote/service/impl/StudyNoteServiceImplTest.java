package system.rezension.studynote.service.impl;

import system.rezension.studynote.dto.request.StudyNoteCreateRequest;
import system.rezension.studynote.dto.response.StudyNoteResponse;
import system.rezension.studynote.entity.StudyNote;
import system.rezension.studynote.repository.StudyNoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyNoteServiceImplTest {

    @Mock
    private StudyNoteRepository studyNoteRepository;

    @InjectMocks
    private StudyNoteServiceImpl studyNoteService;

    @Test
    void createStudyNote_shouldReturnResponse() {
        // given
        UserDetails userDetails = User.withUsername("finefinee")
                .password("password")
                .roles("USER")
                .build();

        StudyNoteCreateRequest request = new StudyNoteCreateRequest("테스트 제목", "테스트 내용");

        StudyNote studyNote = StudyNote.builder()
                .id(1L)
                .title("테스트 제목")
                .content("테스트 내용")
                .member(null)  // 필요하다면 mock Member 넣기
                .createdAt(LocalDateTime.now())
                .build();

        when(studyNoteRepository.save(any(StudyNote.class)))
                .thenReturn(studyNote);

        // when
        StudyNoteResponse response = studyNoteService.createStudyNote(userDetails, request);

        // then
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.title()).isEqualTo("테스트 제목");
        assertThat(response.content()).isEqualTo("테스트 내용");
    }
}
