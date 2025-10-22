package system.rezension.domain.studynote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class StudyNoteController {
    private final StudyNoteService studyNoteService;

    // StudyNote 만들기
    @PostMapping
    public ResponseEntity<StudyNoteResponse> createStudyNote(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody StudyNoteCreateRequest request
    ) {
        StudyNoteResponse response = studyNoteService.createStudyNote(userDetails, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // StudyNote 단일 조회
    @GetMapping("/{studyNoteId}")
    public ResponseEntity<StudyNoteResponse> readStudyNote(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long studyNoteId
    ) {
        StudyNoteResponse response = studyNoteService.readStudyNote(userDetails, studyNoteId);
        return ResponseEntity.ok(response);
    }

    // StudyNote 페이지 단위 조회
    @GetMapping("/user/{memberId}")
    public ResponseEntity<Page<StudyNoteResponse>> readStudyNotePage(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long memberId,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<StudyNoteResponse> page = studyNoteService.readStudyNotePage(userDetails, memberId, pageable);
        return ResponseEntity.ok(page);
    }


    // StudyNote 수정
    @PutMapping("/{studyNoteId}")
    public ResponseEntity<StudyNoteResponse> updateStudyNote(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody StudyNoteUpdateRequest request,
            @PathVariable Long studyNoteId
    ) {
        StudyNoteResponse response = studyNoteService.updateStudyNote(userDetails, request, studyNoteId);
        return ResponseEntity.ok(response);
    }

    // StudyNote 삭제
    @DeleteMapping("/{studyNoteId}")
    public ResponseEntity<Void> deleteStudyNote(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long studyNoteId
    ) {
        studyNoteService.deleteStudyNote(userDetails, studyNoteId);
        return ResponseEntity.noContent().build();
    }
}
