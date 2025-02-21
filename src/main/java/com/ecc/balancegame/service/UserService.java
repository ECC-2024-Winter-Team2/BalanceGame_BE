package com.ecc.balancegame.service;

import com.ecc.balancegame.domain.User;
import com.ecc.balancegame.dto.UserNameRequestDto;
import com.ecc.balancegame.dto.UserNameResponseDto;
import com.ecc.balancegame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    /**
     * 닉네임 저장 (새 유저 생성 또는 닉네임만 등록)
     * - 중복된 닉네임이 있는지 검사
     * - userName이 비어있지 않은지 검사
     */
    public UserNameResponseDto saveUserName(UserNameRequestDto request) {
        // 1) 요청 검증
        if (request.getUserName() == null || request.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("닉네임을 입력해야 합니다.");
        }

        // 2) 닉네임 중복 검사
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        // 3) 유저 생성
        User user = User.builder()
                .userName(request.getUserName())
                .build();

        userRepository.save(user);

        // 4) 응답 DTO 반환
        return new UserNameResponseDto(user.getUserId(), user.getUserName());
    }
}
