package com.ecc.balancegame.controller;

import com.ecc.balancegame.dto.UserNameRequestDto;
import com.ecc.balancegame.dto.UserNameResponseDto;
import com.ecc.balancegame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 닉네임 설정
     * POST /api/user/username
     * RequestBody: { "userName": "닉네임" }
     */
    @PostMapping("/username")
    public ResponseEntity<?> saveUserName(@RequestBody UserNameRequestDto request) {
        try {
            UserNameResponseDto response = userService.saveUserName(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            // 400 Bad Request
            return ResponseEntity.badRequest().body(
                    // 간단히 에러 메시지 JSON으로 반환
                    new ErrorResponse(e.getMessage())
            );
        }
    }

    // 에러 응답용 DTO
    static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
