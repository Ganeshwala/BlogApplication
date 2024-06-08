package com.SpringBoot.BlogApp.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.BlogApp.DTO.CategoryVo;
import com.SpringBoot.BlogApp.Services.CategoryService;

@RestController
@RequestMapping("/blogApp")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/createCategory")
	public ResponseEntity<CategoryVo> newCategory(@RequestBody CategoryVo categoryVo){
		CategoryVo category = this.categoryService.createCategory(categoryVo);
		return new ResponseEntity<>(category,HttpStatus.CREATED);
	}
	
	@PutMapping("/category/{cid}")
	public ResponseEntity<CategoryVo> updateCategory(@RequestBody CategoryVo categoryVo,@PathVariable Integer cid){
		CategoryVo updateCategory = this.categoryService.updateCategory(categoryVo, cid);
		return new ResponseEntity<>(updateCategory,HttpStatus.OK);
	}
	
	@DeleteMapping("/category/{delCid}")
	public ResponseEntity<?> deleteCategory(@PathVariable("delCid") Integer cId){
		this.categoryService.deleteCategoryById(cId);
		return new ResponseEntity<Object>(Map.of("Message","Category Delete Successfully"),HttpStatus.OK);
	}
	
	@GetMapping("/category/{cid}")
	public ResponseEntity<CategoryVo> getCategoryById(@PathVariable Integer cid){
		CategoryVo categoryInfo = this.categoryService.findCategoryById(cid);
		return ResponseEntity.ok(categoryInfo);
	}
	
	@GetMapping("/allCategory")
	public ResponseEntity<List<CategoryVo>> getAllCategory(){ 
		return ResponseEntity.ok(this.categoryService.findAllCategory());
	}
	
}
