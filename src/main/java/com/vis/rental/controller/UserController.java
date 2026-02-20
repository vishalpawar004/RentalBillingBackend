package com.vis.rental.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	  @GetMapping("/test")
	    public String userTest() {
	        return "User ACCESS OK";
	    }

}
