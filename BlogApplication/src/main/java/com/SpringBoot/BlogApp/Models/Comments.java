package com.SpringBoot.BlogApp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int commentId;
	
	private String commentText;
	
	@ManyToOne
	private BlogPost post;
}
