package system.rezension.studynote.service.impl;

import system.rezension.domain.studynote.dto.request.StudyNoteCreateRequest;
import system.rezension.domain.studynote.dto.response.StudyNoteResponse;
import system.rezension.domain.studynote.entity.StudyNote;
import system.rezension.domain.studynote.repository.StudyNoteRepository;
import system.rezension.domain.member.entity.Member;
import system.rezension.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import system.rezension.domain.studynote.service.impl.StudyNoteServiceImpl;
import system.rezension.domain.studynote.dto.request.StudyNoteUpdateRequest;
import system.rezension.domain.studynote.service.StudyNotePermissionValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyNoteServiceImplTest {

    @Mock
    private StudyNoteRepository studyNoteRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private StudyNotePermissionValidator studyNoteValidator;

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

        // 테스트용 Member 생성
        Member member = Member.builder()
                .id(1L)
                .username("finefinee")
                .password("password")
                .build();

        StudyNote studyNote = StudyNote.builder()
                .id(1L)
                .title("테스트 제목")
                .content("테스트 내용")
                .member(member)  // Member 넣기
                .createdAt(LocalDateTime.now())
                .build();

        when(studyNoteRepository.save(any(StudyNote.class)))
                .thenReturn(studyNote);
        when(memberRepository.findByUsername("finefinee")).thenReturn(Optional.of(member));

        // when
        StudyNoteResponse response = studyNoteService.createStudyNote(userDetails, request);

        // then
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.title()).isEqualTo("테스트 제목");
        assertThat(response.content()).isEqualTo("테스트 내용");
        assertThat(response.username()).isEqualTo("finefinee"); // username 검증
    }

    @Test
    void readStudyNote_shouldReturnResponse() {
        // given
        UserDetails userDetails = User.withUsername("finefinee")
                .password("password")
                .roles("USER")
                .build();

        Member member = Member.builder()
                .id(1L)
                .username("finefinee")
                .password("password")
                .build();

        StudyNote studyNote = StudyNote.builder()
                .id(1L)
                .title("테스트 제목")
                .content("테스트 내용")
                .member(member)
                .createdAt(LocalDateTime.of(2023, 8, 21, 12, 0))
                .build();

        when(studyNoteRepository.findById(1L)).thenReturn(java.util.Optional.of(studyNote));

        // when
        StudyNoteResponse response = studyNoteService.readStudyNote(userDetails, 1L);

        // then
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.title()).isEqualTo("테스트 제목");
        assertThat(response.content()).isEqualTo("테스트 내용");
        assertThat(response.username()).isEqualTo("finefinee");
        assertThat(response.createdAt()).isEqualTo(LocalDateTime.of(2023, 8, 21, 12, 0));
    }

    @Test
    void updateStudyNote_shouldReturnResponse() {
        // given
        UserDetails userDetails = User.withUsername("finefinee").password("password").roles("USER").build();
        Member member = Member.builder().id(1L).username("finefinee").password("password").build();
        StudyNote studyNote = StudyNote.builder().id(1L).title("old").content("old").member(member).createdAt(LocalDateTime.now()).build();
        StudyNoteUpdateRequest req = new StudyNoteUpdateRequest(1L, Optional.of("new title"), Optional.of("new content"));

        when(studyNoteRepository.findById(1L)).thenReturn(Optional.of(studyNote));
        when(studyNoteRepository.save(studyNote)).thenReturn(studyNote);

        // when
        StudyNoteResponse response = studyNoteService.updateStudyNote(userDetails, req);

        // then
        assertThat(response.title()).isEqualTo("new title");
        assertThat(response.content()).isEqualTo("new content");
    }

    @Test
    void deleteStudyNote_shouldReturnResponse() {
        // given
        UserDetails userDetails = User.withUsername("finefinee").password("password").roles("USER").build();
        Member member = Member.builder().id(1L).username("finefinee").password("password").build();
        StudyNote studyNote = StudyNote.builder().id(1L).title("title").content("content").member(member).createdAt(LocalDateTime.now()).build();

        when(studyNoteRepository.findById(1L)).thenReturn(Optional.of(studyNote));

        // when
        StudyNoteResponse response = studyNoteService.deleteStudyNote(userDetails, 1L);

        // then
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.title()).isEqualTo("title");
    }

    @Test
    void readAllStudyNote_shouldReturnList() {
        // given
        UserDetails userDetails = User.withUsername("finefinee").password("password").roles("USER").build();
        Member member = Member.builder().id(1L).username("finefinee").password("password").build();
        StudyNote note1 = StudyNote.builder().id(1L).title("t1").content("c1").member(member).createdAt(LocalDateTime.now()).build();
        StudyNote note2 = StudyNote.builder().id(2L).title("t2").content("c2").member(member).createdAt(LocalDateTime.now()).build();

        when(memberRepository.findByUsername("finefinee")).thenReturn(Optional.of(member));
        when(studyNoteRepository.findAllByMember(member)).thenReturn(List.of(
            StudyNoteResponse.fromStudyNoteEntity(note1),
            StudyNoteResponse.fromStudyNoteEntity(note2)
        ));

        // when
        List<StudyNoteResponse> list = studyNoteService.readAllStudyNote(userDetails);

        // then
        assertThat(list).hasSize(2);
        assertThat(list.get(0).title()).isEqualTo("t1");
        assertThat(list.get(1).title()).isEqualTo("t2");
    }

    @Test
    void updateStudyNote_shouldThrow_whenNotOwner() {
        // given
        UserDetails userDetails = User.withUsername("other").password("password").roles("USER").build();
        Member member = Member.builder().id(1L).username("finefinee").password("password").build();
        StudyNote studyNote = StudyNote.builder().id(1L).title("old").content("old").member(member).createdAt(LocalDateTime.now()).build();
        StudyNoteUpdateRequest req = new StudyNoteUpdateRequest(1L, Optional.of("new title"), Optional.of("new content"));

        when(studyNoteRepository.findById(1L)).thenReturn(Optional.of(studyNote));
        doThrow(new RuntimeException("권한 없음")).when(studyNoteValidator).validate(userDetails, studyNote);

        // when & then
        assertThatThrownBy(() -> studyNoteService.updateStudyNote(userDetails, req)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void deleteStudyNote_shouldThrow_whenNotOwner() {
        // given
        UserDetails userDetails = User.withUsername("other").password("password").roles("USER").build();
        Member member = Member.builder().id(1L).username("finefinee").password("password").build();
        StudyNote studyNote = StudyNote.builder().id(1L).title("title").content("content").member(member).createdAt(LocalDateTime.now()).build();

        when(studyNoteRepository.findById(1L)).thenReturn(Optional.of(studyNote));
        doThrow(new RuntimeException("권한 없음")).when(studyNoteValidator).validate(userDetails, studyNote);

        // when & then
        assertThatThrownBy(() -> studyNoteService.deleteStudyNote(userDetails, 1L)).isInstanceOf(RuntimeException.class);
    }
}
