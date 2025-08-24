package system.rezension.domain.studynote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import system.rezension.domain.studynote.dto.request.StudyNoteCreateRequest;
import system.rezension.domain.studynote.dto.request.StudyNoteUpdateRequest;
import system.rezension.domain.studynote.dto.response.StudyNoteResponse;
import system.rezension.domain.studynote.service.StudyNoteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class StudyNoteController {
    private final StudyNoteService studyNoteService;

    // 1️⃣ StudyNote 만들기
    @PostMapping
    public ResponseEntity<StudyNoteResponse> createStudyNote(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody StudyNoteCreateRequest request
    ) {
        StudyNoteResponse response = studyNoteService.createStudyNote(userDetails, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2️⃣ StudyNote 단일 조회
    @GetMapping("/{studyNoteId}")
    public ResponseEntity<StudyNoteResponse> readStudyNote(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long studyNoteId
    ) {
        StudyNoteResponse response = studyNoteService.readStudyNote(userDetails, studyNoteId);
        return ResponseEntity.ok(response);
    }

    // 3️⃣ StudyNote 페이지 단위 조회
    @GetMapping
    public ResponseEntity<Page<StudyNoteResponse>> readStudyNotePage(
            @AuthenticationPrincipal UserDetails userDetails,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        // 로그인한 UserDetails에서 memberId 추출
        Long memberId = ((CustomUserDetails) userDetails).getId();

        Page<StudyNoteResponse> page = studyNoteService.readStudyNotePage(userDetails, memberId, pageable);
        return ResponseEntity.ok(page);
    }

    // 4️⃣ StudyNote 전체 조회
    @GetMapping("/all")
    public ResponseEntity<List<StudyNoteResponse>> readAllStudyNote(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<StudyNoteResponse> list = studyNoteService.readAllStudyNote(userDetails);
        return ResponseEntity.ok(list);
    }

    // 5️⃣ StudyNote 수정
    @PutMapping
    public ResponseEntity<StudyNoteResponse> updateStudyNote(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody StudyNoteUpdateRequest request
    ) {
        StudyNoteResponse response = studyNoteService.updateStudyNote(userDetails, request);
        return ResponseEntity.ok(response);
    }

    // 6️⃣ StudyNote 삭제
    @DeleteMapping("/{studyNoteId}")
    public ResponseEntity<StudyNoteResponse> deleteStudyNote(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long studyNoteId
    ) {
        StudyNoteResponse response = studyNoteService.deleteStudyNote(userDetails, studyNoteId);
        return ResponseEntity.ok(response);
    }
}
