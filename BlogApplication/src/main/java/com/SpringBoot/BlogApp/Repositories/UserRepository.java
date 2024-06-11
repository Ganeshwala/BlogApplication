package com.SpringBoot.BlogApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SpringBoot.BlogApp.Models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("from User us where us.userName=:name and us.email=:userEmail")
	public User findUserByUserNameAndEmail(@Param("name")String userName, @Param("userEmail")String email);
}
