package com.kiosk.dao.db;

import java.sql.Blob;
import java.util.List;

import com.kiosk.model.Audio;

public interface AudioDBDao {

	public List<Audio> getAudio();

	public Audio getIndividualAudio(int id);

	public int addAudio(Audio a, String today);

	public int deleteAudio(int id);

	public int editAudio(Audio a);

	public Blob getAudio(int audioID);

}
