package com.example.secyrity.controller;

import com.example.secyrity.service.RoleService;
import com.example.secyrity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/")
public class DefoltController {
	private UserService userService;
	private RoleService roleService;

	public DefoltController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}
	@GetMapping
	public String addUser(Model model) {

		return "/index";
	}
}