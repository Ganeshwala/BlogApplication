package com.SpringBoot.BlogApp.Services;

import java.util.List;

import com.SpringBoot.BlogApp.DTO.BlogPostVo;

public interface BlogPostService {

	public BlogPostVo createBlogPost(BlogPostVo post,Integer userId,Integer categoryId);
	
	public BlogPostVo updateBlogPost(BlogPostVo blogPost,Integer postId);
	
	public List<BlogPostVo> getAllBlogs();
	
	public BlogPostVo getBlogByPostId(Integer postId);
	
	public void deleteBlog(Integer postId);
	
	public List<BlogPostVo> getBlogByCategoryId(Integer categoryId);
	
	public List<BlogPostVo> getBlogByUserId(Integer userId);
	
	public List<BlogPostVo> serachBlogByKeyword(String keyword);
}
