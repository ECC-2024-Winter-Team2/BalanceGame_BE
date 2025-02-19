package com.ecc.balancegame.service;

import com.ecc.balancegame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}
