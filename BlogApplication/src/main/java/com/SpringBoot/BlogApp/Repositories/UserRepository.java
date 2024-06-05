package com.SpringBoot.BlogApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.BlogApp.Models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
