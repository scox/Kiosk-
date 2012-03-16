package com.kiosk.service;

import java.io.IOException;
import java.util.List;

import com.kiosk.model.Audio;
/**
 * Author: Sam Cox
 * Date: 06/01/2012
 * AudioService.Java: Java interface
 */


public interface AudioService {

	public boolean addAudio(Audio a);

	public boolean deleteAudio(int id);

	public byte[] getMP3(String filePath) throws IOException;
	
	public Audio getIndividualAudio(int id);
	
	public List<Audio> getAudio();

}
