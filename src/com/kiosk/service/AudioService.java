package com.kiosk.service;

import java.util.List;

import com.kiosk.model.Audio;

public interface AudioService {

	public List<Audio> getAudio();

	public Audio getIndividualAudio(int id);

	public boolean addAudio(Audio a);

	public boolean deleteAudio(int id);

	public boolean editAudio(Audio a);

	public byte[] getMP3(int audioID);

}
