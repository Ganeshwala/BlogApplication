package com.SpringBoot.BlogApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SpringBoot.BlogApp.Models.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	@Query("from Category ct where ct.categoryTitle=:title and ct.description=:content")
	Category findByCategoryTitleAndContent(String title,String content);
	
}
