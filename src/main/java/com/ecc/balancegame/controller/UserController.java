package com.ecc.balancegame.controller;

import com.ecc.balancegame.domain.User;
import com.ecc.balancegame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/username")
    public ResponseEntity<?> createUser(@RequestBody Map<String, String> request) {
        try {
            String userName = request.get("userName");
            User user = userService.createUser(userName);

            // 성공 응답 반환 (201 Created)
            Map<String, Object> response = new HashMap<>();
            response.put("userId", user.getUserId());
            response.put("userName", user.getUserName());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            // 에러 응답 반환 (400 Bad Request)
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}