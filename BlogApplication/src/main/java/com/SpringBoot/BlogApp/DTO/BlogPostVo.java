package com.SpringBoot.BlogApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostVo {

	private String postTitle;
	private String postContent;
	private String image;
	private CategoryVo category;
	private UserDTO user;
}
