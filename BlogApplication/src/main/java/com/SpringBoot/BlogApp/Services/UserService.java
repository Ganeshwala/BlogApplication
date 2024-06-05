package com.SpringBoot.BlogApp.Services;

import java.util.List;

import com.SpringBoot.BlogApp.DTO.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO userInfo,Integer userId);
	UserDTO getUserByUserId(Integer userId);
	void deleteUserByUserId(Integer userId);
	List<UserDTO> getAllUsers();
}
