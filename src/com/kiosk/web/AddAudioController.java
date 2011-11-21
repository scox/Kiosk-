package com.kiosk.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kiosk.model.Audio;
import com.kiosk.service.AudioService;

//This class displays the data on confirm.jsp. It gets the selected items and creates the mailto link to be called and the 
//It also sets the selected item so the user can check before they proceed on confirm.jsp.

@Controller
@SessionAttributes
public class AddAudioController {

	@Autowired
	private AudioService audioService;

	@RequestMapping("addAudio.htm")
	@ModelAttribute
	public ModelAndView addAudio(Audio uploadAudio, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "type") String type,
			@RequestParam(value = "subtype") String subtype) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("added", audioService.addAudio(uploadAudio));
		mv.addObject("type", type);
		mv.addObject("subtype", subtype);
		mv.addObject("command", new Audio());
		mv.setViewName("manageAudios");

		return mv;

	}

}