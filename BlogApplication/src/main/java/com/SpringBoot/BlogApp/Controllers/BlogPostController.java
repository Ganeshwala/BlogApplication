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
	public ResponseEntity<List<BlogPostVo>> getAllPostByUserID(@PathVariable Integer userId){
		List<BlogPostVo> userBlogList = this.blogPostService.getBlogByUserId(userId);
		return new ResponseEntity<List<BlogPostVo>>(userBlogList,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<List<BlogPostVo>> getAllPostByCategoryID(@PathVariable Integer categoryId){
		List<BlogPostVo> categoryBlogList = this.blogPostService.getBlogByCategoryId(categoryId);
		return new ResponseEntity<List<BlogPostVo>>(categoryBlogList,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<BlogPostVo>> getAllPost(){
		return new ResponseEntity<List<BlogPostVo>>(this.blogPostService.getAllBlogs(),HttpStatus.OK);
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<BlogPostVo> getPostById(@PathVariable Integer postId){
		return new ResponseEntity<>(this.blogPostService.getBlogByPostId(postId),HttpStatus.OK);
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<BlogPostVo> updateBlogPostInfo(@RequestBody BlogPostVo blogpost,@PathVariable Integer postId){
		BlogPostVo updateBlogPost = this.blogPostService.updateBlogPost(blogpost, postId);
		return new ResponseEntity<>(updateBlogPost,HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{deletePostId}")
	public ResponseEntity<?> deleteBlogPostById(@PathVariable Integer deletePostId){
		this.blogPostService.deleteBlog(deletePostId);
		return new ResponseEntity<Object>(Map.of("Message","Category Delete Successfully"),HttpStatus.OK);
	}
}
