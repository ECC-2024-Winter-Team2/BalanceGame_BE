package com.ecc.balancegame.service;

import com.ecc.balancegame.domain.Category;
import com.ecc.balancegame.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //생성자 자동 생성
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 카테고리 목록 조회
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
