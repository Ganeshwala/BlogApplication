package com.SpringBoot.BlogApp.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.sound.midi.MidiChannel;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.BlogApp.DTO.CategoryVo;
import com.SpringBoot.BlogApp.ExceptionsHandler.ResourceNotFoundException;
import com.SpringBoot.BlogApp.Models.Category;
import com.SpringBoot.BlogApp.Repositories.CategoryRepo;
import com.SpringBoot.BlogApp.Services.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryVo createCategory(CategoryVo categoryInfo) {
		Category categoryBo = this.convertCategoryVoToBo(categoryInfo);
		Category newCategory = this.categoryRepo.save(categoryBo);
		return this.convertCategoryBoToVO(newCategory);
	}

	@Override
	public CategoryVo updateCategory(CategoryVo categoryInfo,Integer cid) {
		Category categoryBo = this.categoryRepo.findById(cid).orElseThrow((() -> new ResourceNotFoundException("Category","id",cid)));
		categoryBo.setCategoryTitle(categoryInfo.getCategoryTitle());
		categoryBo.setDescription(categoryInfo.getDescription());
		
		this.categoryRepo.save(categoryBo);
		
		return this.convertCategoryBoToVO(categoryBo);
	}

	@Override
	public void deleteCategoryById(int categoryId) {
		Category categoryBo = this.categoryRepo.findById(categoryId).orElseThrow((() -> new ResourceNotFoundException("Category","id",categoryId)));
		this.categoryRepo.delete(categoryBo);
	}

	@Override
	public CategoryVo findCategoryById(int categoryId) {
		Category categoryBo = this.categoryRepo.findById(categoryId).orElseThrow((() -> new ResourceNotFoundException("Category","id",categoryId)));
		
		return this.convertCategoryBoToVO(categoryBo);
	}

	@Override
	public List<CategoryVo> findAllCategory() {
		List<Category> categoryList = this.categoryRepo.findAll();
		List<CategoryVo> categoryListVo = categoryList.stream().map(category -> this.convertCategoryBoToVO(category)).collect(Collectors.toList());
		return categoryListVo;
	}
	
	public CategoryVo convertCategoryBoToVO(Category categoryBo) {
		return this.modelMapper.map( categoryBo,CategoryVo.class);
	}
	
	public Category convertCategoryVoToBo(CategoryVo categoryVo) {
		return this.modelMapper.map(categoryVo, Category.class);
	}

}
