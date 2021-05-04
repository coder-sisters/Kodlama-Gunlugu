package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	//-------------------------      Login       -------------------------

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value="status", required = false) String status, Model model) {
		if(status != null) {
			System.out.println(status);
			if(status.equals("OK")) {
				model.addAttribute("status" , "üyeliğiniz başarıyla tamamlandı !");
			}
			else {
				model.addAttribute("status" , "Hata Tekrar deneyiniz !");
			}
		}
		return "login";
	}
	
	@RequestMapping(value = "/controlUser", method = RequestMethod.POST)
	public ResponseEntity<String> controlUser(@RequestBody User user, HttpServletRequest request) {

		User userm = userService.getFindByUsernameAndPassword(user);
		if (userm !=null) {
			System.out.printf("oturum açıldı", user.getUsername().toString());
			
			request.getSession().setAttribute("user", userm);
			
			return new ResponseEntity<>("ok", HttpStatus.CREATED);
		} else {

			return new ResponseEntity<>("error", HttpStatus.CREATED);
		}
	}

	//-------------------------      Register       -------------------------
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		return "register";
	}
	
	@RequestMapping(value = "/reg/{key}", method = RequestMethod.GET)
	public String regOk(@PathVariable User key, Model model) {

		if(userService.getFindByKey(key) ) {
			return "redirect:/login?status=ok";
		}
		return "redirect:/login?status=error";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user, HttpServletRequest request) {
		
		
		
		int status = control(user);

		if (status != 0) {
			return new ResponseEntity<>(status + "", HttpStatus.OK);
		}

		System.out.println(user.toString());

		if (userService.createUser(user, request).equals(user)) {
			return new ResponseEntity<>("OK", HttpStatus.CREATED);
		} else {

			return new ResponseEntity<>("ERROR", HttpStatus.CREATED);
		}
		
	}

	private int control(User user) {
		if (!user.getPassword2().equals(user.getPassword())) {
			return 1;
		}
		return 0;   
	}
	
	
	//-------------------------      Panel       -------------------------
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		request.getSession().setAttribute("user", request);
		
		return "redirect:/login";
	}

}
