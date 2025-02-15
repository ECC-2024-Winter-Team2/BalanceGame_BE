package com.ecc.balancegame.controller;

import com.ecc.balancegame.dto.UserPercentageResponse;
import com.ecc.balancegame.service.UserPercentageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class UserPercentageController {

    private final UserPercentageService userPercentageService;

    public UserPercentageController(UserPercentageService userPercentageService) {
        this.userPercentageService = userPercentageService;
    }

    @GetMapping("/{userId}")
    public UserPercentageResponse getUserPercentage(
            @PathVariable Long userId,
            @RequestParam Long categoryId) {
        return userPercentageService.getUserPercentage(userId, categoryId);
    }
}
