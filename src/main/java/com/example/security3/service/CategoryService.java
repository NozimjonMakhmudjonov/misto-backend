package com.example.security3.service;

import com.example.security3.entity.EntCategory;
import com.example.security3.mapper.CategoryMapper;
import com.example.security3.payload.CategoryCreateDTO;
import com.example.security3.payload.CategoryDTO;
import com.example.security3.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> getAll(){
        List<EntCategory> categories= categoryRepository.findAll();
        return categoryMapper.toDto(categories);
    }

    public CategoryDTO getById(Long id){
        EntCategory entCategory=categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Category is not found!"));
        return categoryMapper.toDto(entCategory);

    }

    @Transactional
    public CategoryDTO create(CategoryCreateDTO request){
        EntCategory entCategory=categoryMapper.fromDto(request);
        entCategory= categoryRepository.save(entCategory);
        return categoryMapper.toDto(entCategory);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryCreateDTO request){
        EntCategory entCategory=categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category is not found!"));
        categoryMapper.update(entCategory, request);
        entCategory=categoryRepository.save(entCategory);
        return categoryMapper.toDto(entCategory);
    }

    @Transactional
    public void delete(Long id){
        EntCategory entCategory=categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Category is not found!"));
        categoryRepository.delete(entCategory);
    }
}
