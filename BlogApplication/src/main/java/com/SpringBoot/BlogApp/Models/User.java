package com.SpringBoot.BlogApp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
	
	private String userName;
	private String email;
	private String password;
	private boolean isActive;
	private String userAbout;
}
