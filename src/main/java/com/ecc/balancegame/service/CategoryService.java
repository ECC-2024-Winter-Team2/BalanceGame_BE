package com.ecc.balancegame.service;

import com.ecc.balancegame.domain.Category;
import com.ecc.balancegame.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // 카테고리 ID에 해당하는 카테고리 이름 반환 메서드
    public String getCategoryNameById(Long categoryId){
        return categoryRepository.findById(categoryId)
                .map(Category::getCategoryName)
                .orElseThrow(()-> new IllegalArgumentException("유효하지 않은 카테고리 ID 입니다."));
    }
}
