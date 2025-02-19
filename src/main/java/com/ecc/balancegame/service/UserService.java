package com.ecc.balancegame.service;

import com.ecc.balancegame.domain.User;
import com.ecc.balancegame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(String userName) {
        // 닉네임이 비어 있는 경우 예외 처리
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("닉네임이 비어 있습니다.");
        }

        // 닉네임 중복 확인
        if (userRepository.existsByUserName(userName)) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        // 새 User 저장
        User user = new User();
        user.setUserName(userName);
        return userRepository.save(user);
    }
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}
