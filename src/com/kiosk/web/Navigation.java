package com.kiosk.web;

import org.springframework.ui.ModelMap;

import com.kiosk.model.User;

/**
 * Author: Sam Cox Date: 06/01/2012 Navigation.java - includes reusable model
 * attributes
 */
final public class Navigation {

	private Navigation() {
	}

	public static void setNavigation(ModelMap model, User user) {

		model.addAttribute("user", user);
	}

}
