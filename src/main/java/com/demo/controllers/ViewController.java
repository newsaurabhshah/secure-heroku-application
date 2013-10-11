package com.demo.controllers;

import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.utilities.BaseUtil;


@Controller
public class ViewController {
	/**
	 * This is the Main Method Loader
	 * @param user
	 * @return
	 */
	@RequestMapping(value="main")
	@PreAuthorize("hasRole('user')")
	public ModelAndView main(Principal user){		
		return new ModelAndView("main","userDTO", BaseUtil.getUserPrincipal(user));
	}
}
