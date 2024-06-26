package com.SpringBoot.BlogApp.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.SpringBoot.BlogApp.DTO.UserDTO;
import com.SpringBoot.BlogApp.ExceptionsHandler.ResourceNotFoundException;
import com.SpringBoot.BlogApp.Models.User;
import com.SpringBoot.BlogApp.Repositories.UserRepository;
import com.SpringBoot.BlogApp.Services.UserService;

@Service
public class UserDTOServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO userVo) {
		User userBo = this.converUserDtoVoToUserBo(userVo); // converting VO(Visual Object)  to BO (Business Object) 
		User user = this.userRepository.save(userBo);  // save the BO to database
		return this.convertUserBoToUserDtoVo(user); // return that BO as a form of VO
	}

	@Override
	public UserDTO updateUser(UserDTO userInfo, Integer userId) {
		User user = this.userRepository.findById(userId)
				                       .orElseThrow((() -> new ResourceNotFoundException("User","id",userId)));
		
		user.setUserName(userInfo.getUserName());
		user.setEmail(userInfo.getEmail());
		user.setPassword(userInfo.getPassword());
		user.setUserAbout(userInfo.getUserAbout());
		
		User updateUserBo = this.userRepository.save(user);
		
		return this.convertUserBoToUserDtoVo(updateUserBo);
	}

	@Override
	public UserDTO getUserByUserId(Integer userId) {
		User userBo = this.userRepository.findById(userId)
                .orElseThrow((() -> new ResourceNotFoundException("User","id",userId)));
		return this.convertUserBoToUserDtoVo(userBo);
	}

	@Override
	public void deleteUserByUserId(Integer userId) {
		User userBo = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		this.userRepository.delete(userBo);
		System.out.println("User Delete successfully");
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> userBo = this.userRepository.findAll();
        
		List<UserDTO> listUserVo = userBo.stream().map(user -> this.convertUserBoToUserDtoVo(user)).collect(Collectors.toList());
		
		return listUserVo;
	}
	
	public User converUserDtoVoToUserBo(UserDTO userVo) {
		/*User userBo = new User();
		userBo.setUserName(userVo.getUserName());
		userBo.setEmail(userVo.getEmail());
		userBo.setPassword(userVo.getPassword());
		userBo.setActive(true);
		userBo.setUserAbout(userVo.getUserAbout());
		return userBo;*/
		
		// we can also do this with using ModelMapper
		User userBo = this.modelMapper.map(userVo, User.class);
		return userBo;
		
	}
	
	public UserDTO convertUserBoToUserDtoVo(User userBo) {
		/*return UserDTO.builder()
			   .userId(userBo.getUserId())
			   .userName(userBo.getUserName())
			   .email(userBo.getEmail())
			   .password(userBo.getPassword())
			   .userAbout(userBo.getUserAbout())
			   .build();*/
		// we can also convert using ModelMapper
		return this.modelMapper.map(userBo, UserDTO.class);
	}

}
