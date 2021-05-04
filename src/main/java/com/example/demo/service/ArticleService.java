package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.entity.Articles;
import com.example.demo.repository.IArticleRepository;

@Service
@Transactional
public class ArticleService {


	@Autowired
	private IArticleRepository articleRepository; 

	//CRUD
	
	public Articles createArticle(Articles articles, HttpServletRequest request) {
		return articleRepository.save(articles);
	}
	
	public String deleteArticles(int id) {
		articleRepository.deleteById(id);
		return "comment id " + id;
	}
	
	public Articles update(Articles articles) {
		Articles deletedArticles = articleRepository.findById(articles.getId()).orElse(null);
		deletedArticles.setId(articles.getId());
		deletedArticles.setTitle(articles.getTitle());
		deletedArticles.setContent(articles.getContent());
		deletedArticles.setCreate_date(null);
		return articleRepository.save(deletedArticles);
	}
	
	
	//Read
	
	public Articles getArticleById(int id) {
		return articleRepository.findById(id).orElse(null);
	}
	
	public Articles getAll() {
		return (Articles) articleRepository.findAll();
		
	}
	
	public List<Articles> getAllArticle() {
		return  articleRepository.findAll();
	}
	
	
}
