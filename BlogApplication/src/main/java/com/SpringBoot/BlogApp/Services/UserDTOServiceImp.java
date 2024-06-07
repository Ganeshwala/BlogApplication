package com.SpringBoot.BlogApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.SpringBoot.BlogApp.DTO.UserDTO;
import com.SpringBoot.BlogApp.Models.User;
import com.SpringBoot.BlogApp.Repositories.UserRepository;

public class UserDTOServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO createUser(UserDTO userVo) {
		User userBo = this.converUserDtoVoToUserBo(userVo); // converting VO(Visual Object)  to BO (Business Object) 
		User user = this.userRepository.save(userBo);  // save the BO to database
		return this.convertUserBoToUserDtoVo(user); // return that BO as a form of VO
	}

	@Override
	public UserDTO updateUser(UserDTO userInfo, Integer userId) {

		
		return null;
	}

	@Override
	public UserDTO getUserByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserByUserId(Integer userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private User converUserDtoVoToUserBo(UserDTO userVo) {
		User userBo = new User();
		userBo.setUserName(userVo.getUserName());
		userBo.setEmail(userVo.getEmail());
		userBo.setPassword(userVo.getPassword());
		userBo.setActive(true);
		return userBo;
	}
	
	public UserDTO convertUserBoToUserDtoVo(User userBo) {
		return UserDTO.builder()
			   .userId(userBo.getUserId())
			   .userName(userBo.getUserName())
			   .email(userBo.getEmail())
			   .password(userBo.getPassword())
			   .build();
			   
	}

}
