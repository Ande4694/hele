package com.example.api.Service;

import com.example.api.Repository.CategoryRepository;
import com.example.api.Model.Cars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Cars> getAllCategories() {
        return categoryRepository.findAll();
    }
}