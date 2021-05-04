package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Articles;

public interface IArticleRepository extends JpaRepository<Articles, Integer>{

}
