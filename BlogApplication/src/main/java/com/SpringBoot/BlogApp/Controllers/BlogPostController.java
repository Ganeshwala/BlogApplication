package com.SpringBoot.BlogApp.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.BlogApp.DTO.BlogPostVo;
import com.SpringBoot.BlogApp.Services.BlogPostService;

@RestController
@RequestMapping("/blogApp")
public class BlogPostController {

	@Autowired
	private BlogPostService blogPostService;
	
	@PostMapping("/user/{userId}/category/{categotyId}/post")
	public ResponseEntity<BlogPostVo> createBlogPost(@RequestBody BlogPostVo post,@PathVariable("userId") Integer uId,@PathVariable Integer  categotyId){
		BlogPostVo blogPost = this.blogPostService.createBlogPost(post, uId, categotyId);
		return new ResponseEntity<>(blogPost, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<BlogPostVo>> getAllPosyByUserID(@PathVariable Integer userId){
		List<BlogPostVo> userBlogList = this.blogPostService.getBlogByUserId(userId);
		return new ResponseEntity<List<BlogPostVo>>(userBlogList,HttpStatus.OK);
	}
}
