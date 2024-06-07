package com.SpringBoot.BlogApp.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.BlogApp.DTO.UserDTO;
import com.SpringBoot.BlogApp.Services.UserService;

@RestController
@RequestMapping("/blogApp")
public class UserController {

	@Autowired
	private UserService userService;
	
	//create new user
	@PostMapping("/createUser")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userVo){
		UserDTO createNewUser = this.userService.createUser(userVo);
		return new ResponseEntity<>(createNewUser,HttpStatus.CREATED);
	}
	
	//Put -> Update functionality
	@PutMapping("/users/{userId}")
	public ResponseEntity<UserDTO> updateUserInfo(@RequestBody UserDTO userVo,@PathVariable("userId") Integer uId){
		UserDTO updateUser = this.userService.updateUser(userVo, uId);
		return ResponseEntity.ok(updateUser);
	}
	
	// Delete -> to Delete User
	@DeleteMapping("/users/{uid}")
	public ResponseEntity<?> deleteUserInfo(@PathVariable Integer uid){
		this.userService.deleteUserByUserId(uid);
		return new ResponseEntity<Object>(Map.of("Message","User Delete Successfully"),HttpStatus.OK);
	}
	
	// Get -> list of Users
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUserDetails(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	// Get -> find single User
	@GetMapping("/users/{uid}")
	public ResponseEntity<UserDTO> getUserInfoByUid(@PathVariable Integer uid){
		return ResponseEntity.ok(this.userService.getUserByUserId(uid));
	}
	
}
