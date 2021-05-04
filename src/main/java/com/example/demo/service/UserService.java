package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.entity.Articles;
import com.example.demo.entity.User;
import com.example.demo.repository.IUserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private IUserRepository userRepository;

	public User createUser(User user, HttpServletRequest request) {

		String uuid = UUID.randomUUID().toString(); // KEY GENERATOR
		user.setKey(uuid);

		if (userRepository.save(user) != null) {
			System.out.println("kullanıcı kaydedildi");
		}

		return user;
	}

	public String deleteUser(int id) {
		userRepository.deleteById(id);
		return "comment id " + id;
	}

	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	public User getFindByUsernameAndPassword(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

	}

	public User getFindByUsername(User user) {
		return userRepository.findByUsername(user.getUsername());

	}

	public boolean getFindByKey(User key) {

		if (userRepository.findByUsername(key.getKey()) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
