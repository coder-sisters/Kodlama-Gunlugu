package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer>{
	
	 @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
	    User findByUsernameAndPassword(String username, String password);
	
	// List<User> findByUsernameAndPasswor(String emailAddress, String password);
	 
	 @Query("SELECT u FROM User u WHERE u.username = ?1 ")
	    User findByUsername(String username);
	 
	 @Query("SELECT u FROM User u WHERE u.key = ?1 ")
	    User findByKey(String key);

}
