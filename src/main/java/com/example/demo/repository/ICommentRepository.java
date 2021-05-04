package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Comments;

public interface ICommentRepository extends JpaRepository<Comments, Integer>{

	
	@Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
    Comments findByUsernameAndPassword(String username, String password);
	
	@Query("SELECT c FROM Comments c ")
	Comments getAll();
	
	@Query("SELECT c FROM Comments c WHERE c.article_id = ?1")
	Comments getAllByArticleId(int article_id);
	
	
}
