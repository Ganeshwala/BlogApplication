package com.SpringBoot.BlogApp.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Users")
@Builder
public class User {

	@Id
	@SequenceGenerator(name = "userSeq",sequenceName = "uSeq",initialValue = 100,allocationSize = 1)
	@GeneratedValue(generator = "userSeq",strategy = GenerationType.SEQUENCE)
	private int userId;
	@Size(min = 3,message = "userName must be greater than 3")
	private String userName;
	@Email
	private String email;
	@NotNull
	private String password;
	private boolean isActive;
	private String userAbout;
	
	@OneToMany(mappedBy = "userObj",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<BlogPost> blogPosts;
}
