package com.kiosk.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kiosk.model.Audio;
import com.kiosk.service.AudioService;

@Controller
@SessionAttributes
public class PlayAudioController {

	@Autowired
	private AudioService audioService;

	@RequestMapping("playAudio.htm")
	@ModelAttribute
	public void playAudio(Audio uploadAudio, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "audioID") int id) throws IOException {

		byte[] bin = audioService.getMP3(id);

		// OutputStream ops = response.getOutputStream();
		response.setContentType("audio/mpeg");
		response.setHeader("Content-disposition", "inline; filename=");
		response.setContentLength(bin.length);
		// ops.write(bin,0,bin.length);
		// ops.flush();
		response.getOutputStream().write(bin, 0, bin.length);
		
	}

}
