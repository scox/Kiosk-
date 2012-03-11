package com.kiosk.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kiosk.dao.db.LoginDBDao;
import com.kiosk.model.User;
import com.kiosk.service.UserManagementService;

/**
 * Author: Sam Cox Date: 06/01/2012 UserManagementController.Java: This class
 * handles all user management activities. These are add user delete user,
 * change password and change access.
 */

@Controller
@SessionAttributes
public class UserManagementController {

	@Autowired
	private UserManagementService userManagementService;
	@Autowired
	private LoginDBDao loginDBDao;

	// private static final Logger LOG = Logger
	// .getLogger(UserManagementController.class);

	// handle presentation and direct to appropriate view based on user
	// management activity requested

	@RequestMapping("/userManagement.htm")
	@ModelAttribute
	public void userManagement(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "option") String option) {

		User user = (User) request.getSession().getAttribute("user");

		if (option.equalsIgnoreCase("doAddUser")) {

			model.put(
					"added",
					userManagementService.addUser(
							request.getParameter("username"),
							request.getParameter("password"),
							Integer.parseInt(request.getParameter("access"))));

		}

		else if (option.equalsIgnoreCase("doDeleteUser")) {

			model.put("deleteResult", userManagementService.deleteUser(Integer
					.parseInt(request.getParameter("userID"))));

		}

		else if (option.equalsIgnoreCase("doChangePassword")) {

			int userID = Integer.parseInt(request.getParameter("userID"));
			String newPassword = request.getParameter("newPW");
			boolean updated = userManagementService.changePassword(userID,
					newPassword);
			model.put("updated", updated);

			if (updated == true) {
				user = loginDBDao.getAuthenticateUser(user.getUsername(),
						newPassword);
			}

		}

		else if (option.equalsIgnoreCase("doEditAccess")) {
			model.put("editResult", userManagementService.editAccess(
					Integer.parseInt(request.getParameter("userID")),
					Integer.parseInt(request.getParameter("access"))));
		}

		Navigation.setNavigation(model, user);
		model.put("users", userManagementService.getUser());
		model.put("option", option);
	}

}
