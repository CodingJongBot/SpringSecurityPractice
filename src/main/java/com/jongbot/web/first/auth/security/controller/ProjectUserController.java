package com.jongbot.web.first.auth.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ProjectUserController {
	
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	@GetMapping("/main")
	public String main() {
		return "userMain";
	}

}
