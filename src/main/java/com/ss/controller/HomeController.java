package com.ss.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	
	@RequestMapping(value = { "/login", "/" })
	public String login() {
		return "login";
	}

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView graphview(ModelMap model,Principal principal) {
		String loggedInUserName=principal.getName();
		return new ModelAndView("home", "userName", loggedInUserName);
	}
	
	@RequestMapping(value = "/logout")
	public String logoutView() {
		return "redirect:login";
	}

}
