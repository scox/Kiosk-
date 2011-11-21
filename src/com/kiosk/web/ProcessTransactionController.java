package com.kiosk.web;

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

//This class displays the data on confirm.jsp. It gets the selected items and creates the mailto link to be called and the 
//It also sets the selected item so the user can check before they proceed on confirm.jsp.

@Controller
@SessionAttributes
public class ProcessTransactionController {

	@Autowired
	private KioskService kioskService;

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

}
