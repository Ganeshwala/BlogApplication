package com.SpringBoot.BlogApp.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {

	@Size(min = 3,max = 10,message = "userName must be greater than 3 and less than 10")
	private String userName;
	@Email
	private String email;
	@NotEmpty
	private String password;
	private String userAbout;
}
