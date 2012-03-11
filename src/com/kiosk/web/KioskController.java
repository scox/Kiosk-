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

import com.kiosk.model.Result;
import com.kiosk.model.Transaction;
import com.kiosk.service.KioskService;

/**
 * Author: Sam Cox Date: 06/01/2012 KioskController.Java: This class handles the
 * customer side of the application. It deals with the process of registering
 * for a handset and processing the transaction and provides the presentation
 * layer to the customer
 */

@Controller
@SessionAttributes
public class KioskController {

	@Autowired
	private KioskService kioskService;

	// return customer registration form to purchase playback unit
	@RequestMapping("/customerDetails.htm")
	@ModelAttribute
	public void getCustomerDetails(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		model.put("command", new Transaction());
		model.put("language", kioskService.getLanguages());
		model.put("level", kioskService.getLevels());

	}

	// processes customer transaction and adds confirmation status to the model.
	@RequestMapping(value = "/confirm.htm", method = RequestMethod.POST)
	public void showConfirmationScreen(
			@ModelAttribute("command") Transaction command, ModelMap model) {

		String[] temp;
		String tempString = command.getLevel().replace("(£", " ");
		tempString = tempString.replace(")", "").trim();
		temp = tempString.split(" ");
		command.setLevel(temp[0].toString());
		command.setPrice(Double.parseDouble(temp[1].toString()));

		Result r = kioskService.uploadTransaction(command);
		model.addAttribute("uploaded", r);

	}

	//
	// @RequestMapping(method = RequestMethod.GET)
	// public void showForm() {
	// }

}
