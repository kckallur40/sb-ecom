package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) throw new APIException("ERROR: No categories present at the moment!!");
        return categoryList;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory != null)
            throw new APIException("ERROR: Category already exists by this name - " + category.getCategoryName());
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category> toDeleteCategoryOptional = categoryRepository.findById(categoryId);
        Category toDeleteCategory = toDeleteCategoryOptional.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepository.delete(toDeleteCategory);

        return "Category: " + categoryId + " deleted successfully!!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> needToUpdateCategoryOptional = categoryRepository.findById(categoryId);

        Category needToUpdateCategory = needToUpdateCategoryOptional.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        needToUpdateCategory.setCategoryId(categoryId);
        needToUpdateCategory.setCategoryName(category.getCategoryName());

        Category updatedCategory = categoryRepository.save(needToUpdateCategory);
        return updatedCategory;
    }
}
