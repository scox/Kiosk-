package com.kiosk.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kiosk.service.EmergencyStopService;

//This class displays the data on confirm.jsp. It gets the selected items and creates the mailto link to be called and the 
//It also sets the selected item so the user can check before they proceed on confirm.jsp.

@Controller
@SessionAttributes
public class EmergencyStopController {

	@Autowired
	private EmergencyStopService emergencyStopService;

	@RequestMapping("emergencyStop.htm")
	@ModelAttribute
	public ModelAndView addAudio(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();

		boolean es = emergencyStopService.triggerEmergencyStop();
		mv.addObject("triggerStatus", es);
		return mv;

	}

}
