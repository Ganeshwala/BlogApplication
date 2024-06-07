package com.SpringBoot.BlogApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {

	private int userId;
	private String userName;
	private String email;
	private String password;
	private String userAbout;
}
