package com.SpringBoot.BlogApp.Models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Comments> comments = new HashSet<>();
	
}
