package com.example.demo.service;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Articles;
import com.example.demo.entity.Comments;
import com.example.demo.repository.ICommentRepository;

@Service
@Transactional
public class CommentService {


	@Autowired
	private ICommentRepository commentRepository; 

	//CRUD
	
	public Comments createComment(Comments comment) {
		return commentRepository.save(comment);
	}
	
	public String deleteComment(int id) {
		commentRepository.deleteById(id);
		return "comment id " + id;
	}
	
	
	//Read
	public Comments getCommentById(int id) {
		return commentRepository.findById(id).orElse(null);
	}
	
	public ArrayList<Comments> getComments() {
		return (ArrayList<Comments>) commentRepository.findAll();
	}
	
	public Comments getAll() {
		return (Comments) commentRepository.findAll();
	}
	
	
}
