package com.SpringBoot.BlogApp.Models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BlogPost {

	@SequenceGenerator(name = "pSqe",sequenceName = "postSqe",initialValue = 100,allocationSize = 1)
	@GeneratedValue(generator = "pSqe",strategy = GenerationType.IDENTITY)
	@Id
	private int postId;
	private String postTitle;
	private String postContent;
	private String image;
	private Date postCreateDate;
	private Date postModifyDate;
	
	@ManyToOne
	@JoinColumn(name = "userInfo")
	private User userObj;
	
	@ManyToOne
	@JoinColumn(name = "categoryInfo")
	private Category categoryObj;
	
}
