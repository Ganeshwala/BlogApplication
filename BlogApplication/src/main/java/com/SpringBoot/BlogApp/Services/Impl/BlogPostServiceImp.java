package com.SpringBoot.BlogApp.Services.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.BlogApp.DTO.BlogPostVo;
import com.SpringBoot.BlogApp.DTO.CategoryVo;
import com.SpringBoot.BlogApp.ExceptionsHandler.ResourceNotFoundException;
import com.SpringBoot.BlogApp.Models.BlogPost;
import com.SpringBoot.BlogApp.Models.Category;
import com.SpringBoot.BlogApp.Models.User;
import com.SpringBoot.BlogApp.Repositories.BlogPostRepo;
import com.SpringBoot.BlogApp.Repositories.CategoryRepo;
import com.SpringBoot.BlogApp.Repositories.UserRepository;
import com.SpringBoot.BlogApp.Services.BlogPostService;
import com.SpringBoot.BlogApp.Services.UserService;

@Service
public class BlogPostServiceImp implements BlogPostService {

	@Autowired
	private BlogPostRepo blogPostRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	@Override
	public BlogPostVo createBlogPost(BlogPostVo post,Integer userId,Integer categoryId) {
		
		User user = this.userRepository.findById(userId).orElseThrow((() -> new ResourceNotFoundException("User","id",userId)));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow((() -> new ResourceNotFoundException("CategoryPost","id",categoryId)));
		
		BlogPost blogPostBo = this.converVoToBo(post);
		blogPostBo.setPostCreateDate(new Date());
		blogPostBo.setPostModifyDate(new Date());
		if(post.getImage() == null || post.getImage().isEmpty()) {
			blogPostBo.setImage("Default.jpg");
		}
		blogPostBo.setUserObj(user);
		blogPostBo.setCategoryObj(category);
		
		this.blogPostRepo.save(blogPostBo);
		
		return this.convertBoToVo(blogPostBo);
	}

	@Override
	public BlogPostVo updateBlogPost(BlogPostVo blogPostInfo, Integer postId) {
		BlogPost blogPost = this.blogPostRepo.findById(postId).orElseThrow((() -> new ResourceNotFoundException("BlogPost","id",postId)));
		blogPost.setPostModifyDate(new Date());
		blogPost.setImage(blogPostInfo.getImage());
		blogPost.setPostTitle(blogPostInfo.getPostTitle());
		blogPost.setPostContent(blogPostInfo.getPostContent());
		
		User userByUserNameAndEmail = null;
		if(blogPostInfo.getUser() != null) {
			userByUserNameAndEmail = this.userRepository.findUserByUserNameAndEmail(blogPostInfo.getUser().getUserName(), blogPostInfo.getUser().getEmail());
			userByUserNameAndEmail.setEmail(blogPostInfo.getUser().getEmail() !=null ? blogPostInfo.getUser().getEmail() : userByUserNameAndEmail.getEmail());
			userByUserNameAndEmail.setUserName(blogPostInfo.getUser().getUserName() !=null ? blogPostInfo.getUser().getUserName() : userByUserNameAndEmail.getUserName());
			userByUserNameAndEmail.setUserAbout(blogPostInfo.getUser().getUserAbout() !=null ? blogPostInfo.getUser().getUserAbout() : userByUserNameAndEmail.getUserAbout());
			userByUserNameAndEmail.setPassword(blogPostInfo.getUser().getPassword() !=null ? blogPostInfo.getUser().getPassword() : userByUserNameAndEmail.getPassword());
			blogPost.setUserObj(userByUserNameAndEmail);
		}
		
		Category category = null;
		if(blogPostInfo.getCategory() != null) {
			category = this.categoryRepo.findByCategoryTitleAndContent(blogPostInfo.getCategory().getCategoryTitle(), blogPostInfo.getCategory().getDescription());
			category.setCategoryTitle(blogPostInfo.getCategory().getCategoryTitle() != null ? blogPostInfo.getCategory().getCategoryTitle() : category.getCategoryTitle());
			category.setDescription(blogPostInfo.getCategory().getDescription() != null ? blogPostInfo.getCategory().getDescription() : category.getDescription());
			blogPost.setCategoryObj(category);
		}
		
		return this.convertBoToVo(blogPost);
	}

	@Override
	public List<BlogPostVo> getAllBlogs() {
		List<BlogPost> blogPostList = this.blogPostRepo.findAll();
		List<BlogPostVo> blogPostVoList = blogPostList.stream().map(eachBlog -> this.convertBoToVo(eachBlog)).collect(Collectors.toList());
		return blogPostVoList;
	}

	@Override
	public BlogPostVo getBlogByPostId(Integer postId) {
		BlogPost blogPost = this.blogPostRepo.findById(postId).orElseThrow((() -> new ResourceNotFoundException("BlogPost","id",postId)));
		return this.convertBoToVo(blogPost);
	}

	@Override
	public void deleteBlog(Integer postId) {
		BlogPost blogPost = this.blogPostRepo.findById(postId).orElseThrow((() -> new ResourceNotFoundException("BlogPost","id",postId)));
		this.blogPostRepo.delete(blogPost);
	}

	@Override
	public List<BlogPostVo> getBlogByCategoryId(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow((() -> new ResourceNotFoundException("Category","id",categoryId)));
		List<BlogPost> blogPost = this.blogPostRepo.findByCategoryObj(category);
		List<BlogPostVo> blogPostList = blogPost.stream().map(post -> this.convertBoToVo(post)).collect(Collectors.toList());
		
		return blogPostList;
	}

	@Override
	public List<BlogPostVo> getBlogByUserId(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow((() -> new ResourceNotFoundException("User","id",userId)));
		List<BlogPost> blogPost = this.blogPostRepo.findByUserObj(user);
		List<BlogPostVo> blogPostList = blogPost.stream().map(post -> this.convertBoToVo(post)).collect(Collectors.toList());
		
		return blogPostList;
	}

	@Override
	public List<BlogPostVo> serachBlogByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private BlogPostVo convertBoToVo(BlogPost blogPostBo) {
		return this.modelMapper.map(blogPostBo, BlogPostVo.class);
	}
	
	private BlogPost converVoToBo(BlogPostVo blogPost) {
		return this.modelMapper.map(blogPost, BlogPost.class);
	}

}
