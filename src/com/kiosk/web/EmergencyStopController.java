	package com.kiosk.web;
	
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.SessionAttributes;
	import org.springframework.web.servlet.ModelAndView;
	
	import com.kiosk.service.EmergencyService;
	
	/**
	 * Author: Sam Cox Date: 06/01/2012 EmergencyController.Java: This class sole
	 * use is for the administrator to notify all users that their is an emergency.
	 * This connects to the server and sends the packet 4.
	 */
	
	@Controller
	@SessionAttributes
	public class EmergencyStopController {
	 
		@Autowired
		private EmergencyService emergencyStopService;
	
		// handle request from administrator to send an emergency request packet.
	
		@RequestMapping("emergencyStop.htm")
		@ModelAttribute
		public ModelAndView sendEmergencyPacket(HttpServletRequest request,
				HttpServletResponse response) {
	
			ModelAndView mv = new ModelAndView();
			boolean es = emergencyStopService.triggerEmergencyStop();
			mv.addObject("triggerStatus", es);
			return mv;
	
		}
	
	}
