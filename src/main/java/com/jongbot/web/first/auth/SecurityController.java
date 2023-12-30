package com.jongbot.web.first.auth;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SecurityController {
	
	@GetMapping("user/loginSuccess")
	public String loginSuccess(String param) {
		log.info("Login is Success");		
		return "forward:/loginSuccess.html";
	}
	   
}
