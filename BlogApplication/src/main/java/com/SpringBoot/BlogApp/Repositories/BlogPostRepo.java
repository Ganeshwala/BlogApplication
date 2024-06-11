package com.SpringBoot.BlogApp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.BlogApp.Models.BlogPost;
import com.SpringBoot.BlogApp.Models.Category;
import com.SpringBoot.BlogApp.Models.User;

public interface BlogPostRepo extends JpaRepository<BlogPost, Integer>{

	/*List<BlogPost> findByUser(User user);
	
	List<BlogPost> findByCategory(Category category);*/
}
