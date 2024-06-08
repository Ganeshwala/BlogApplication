package com.SpringBoot.BlogApp.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBoot.BlogApp.DTO.CategoryVo;

@Service
public interface CategoryService {

	public CategoryVo createCategory(CategoryVo categoryInfo);
	
	CategoryVo updateCategory(CategoryVo categoryInfo,Integer cId);
	
	void deleteCategoryById(int categoryId);
	
	CategoryVo findCategoryById(int categoryId);
	
	List<CategoryVo> findAllCategory();
}
