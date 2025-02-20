package com.ecc.balancegame.controller;

import com.ecc.balancegame.dto.UserChoiceRequest;
import com.ecc.balancegame.service.UserChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/choices")
@RequiredArgsConstructor
public class UserChoiceController {

    private final UserChoiceService userChoiceService;

    /**
     * 사용자 선택 정보 저장
     * POST /api/choices
     *
     * @param request 사용자 선택 정보
     * @return 성공 메시지
     */
    @PostMapping
    public ResponseEntity<ApiResponse> saveUserSelections(@RequestBody UserChoiceRequest request) {
        userChoiceService.saveUserSelections(request);
        return new ResponseEntity<>(new ApiResponse("success", "선택 정보가 저장되었습니다."), HttpStatus.CREATED);
    }

    /**
     * 단순 성공/실패 메시지 응답 DTO
     */
    public static class ApiResponse {
        private String status;
        private String message;

        public ApiResponse(String status, String message) {
            this.status = status;
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
}
