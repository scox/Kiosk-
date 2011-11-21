package com.kiosk.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kiosk.model.Transaction;
import com.kiosk.service.KioskService;

@Controller
@SessionAttributes
public class KioskController {

	@Autowired
	private KioskService kioskService;

	@RequestMapping("/customerDetails.htm")
	@ModelAttribute
	public void getCustomerDetails(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		model.put("command", new Transaction());

		model.put("language", kioskService.getLanguages());
		model.put("level", kioskService.getLevels());

	}

	@RequestMapping(method = RequestMethod.GET)
	public void showForm() {
	}

}
