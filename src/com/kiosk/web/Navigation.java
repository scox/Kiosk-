package com.kiosk.web;

import org.springframework.ui.ModelMap;

import com.kiosk.model.User;

final public class Navigation {

	private Navigation() {
	}

	public static void setNavigation(ModelMap model, User usr) {

		model.addAttribute("user", usr);
	}

}
