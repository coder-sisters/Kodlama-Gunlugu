package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Articles;
import com.example.demo.entity.Comments;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;

@Controller
public class HomeController {
	
	public static String url = "http://localhost:8088";

	@Autowired
	private CommentService commentService;

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	
	//------------------------------       Index      ---------------------------------

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest req) {
		
		return "redirect:/anasayfa";
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home2(Model model, HttpServletRequest req) {
		
		return "redirect:/anasayfa";
	}


	@RequestMapping(value = "/anasayfa", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest req) {
		model.addAttribute("user",req.getSession().getAttribute("user"));
		
		System.out.println(req.getRemoteAddr());
		model.addAttribute("serverTime", "/");
		model.addAttribute("articles", articleService.getAllArticle());
		return "index";
	}

	//---------------------------     All Article List      -----------------------------
	
	@GetMapping({ "/Article" })
	public String allArticle(Model model) {
		model.addAttribute("allArticleList", articleService.getAllArticle());
		return "Article";
	}
	
	//---------------------------     Other Pages      -----------------------------

	@GetMapping({ "/page-about" })
	public String pageAbout(Model model) {
		return "page-about";
	}

	@GetMapping({ "/page-contact" })
	public String pageContact(Model model) {
		return "page-contact";
	}


	//---------------------------     All User List      ----------------------------- 
	
	@GetMapping({ "/AllUser" })
	public String singleVideo(Model model) {
		model.addAttribute("allUserList", userService.getAllUsers());
		return "AllUser";
	}

	
	//---------------------------     Admin Panel      -----------------------------
	
	
	@GetMapping({ "/panel" })
	public String panel(Model model) {
		return "panel";
	}

	// ----------------------------- Single Standard ---------------------------------

	
	
	
	@RequestMapping(value = "/single-standard/{id}", method = RequestMethod.GET)
	public String singlestandart(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("comments", commentService.getComments());

		model.addAttribute("id", id);
		System.out.println(id + " numaralı sayfa açıldı");
		return "single-standard";
	}

	@RequestMapping(value = "/getSingleStandard", method = RequestMethod.POST)
	public ResponseEntity<Articles> getSinglestandart(@RequestBody String id, HttpServletRequest request) {
		System.out.println("bu sayfa id : "+id);
		return new ResponseEntity<>(articleService.getArticleById(Integer.parseInt(id)), HttpStatus.CREATED);
	}
	

	@RequestMapping(value = "/getComments", method = RequestMethod.POST)
	public ResponseEntity<ArrayList<Comments>> getComments(HttpServletRequest request) {
		
		return new ResponseEntity<>(commentService.getComments(), HttpStatus.CREATED);
	};

	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public ResponseEntity<String> addComment(@RequestBody Comments comments, HttpServletRequest request) {
		
		System.out.println(comments.toString());

		//commentService.createComment(comments, request);

		return new ResponseEntity<>("Yorumunuz eklendi ! ", HttpStatus.CREATED);
	};
	
	
	

	// ----------------------------- Article ---------------------------------

	@GetMapping({ "/addArticle" })
	public String addArticle(Model model) {
		return "addArticle";
	}

	@RequestMapping(value = "/getArticles", method = RequestMethod.POST)
	public ResponseEntity<List<Articles>> getArticle(HttpServletRequest request) {
		return new ResponseEntity<>(articleService.getAllArticle(), HttpStatus.CREATED);
	};

	@RequestMapping(value = "/addArticle", method = RequestMethod.POST)
	public ResponseEntity<String> addArticle(@RequestBody Articles articles, HttpServletRequest request) {
		System.out.println(articles.toString());

		articleService.createArticle(articles, request);

		return new ResponseEntity<>("Yazınız eklendi ! ", HttpStatus.CREATED);
	};

}
