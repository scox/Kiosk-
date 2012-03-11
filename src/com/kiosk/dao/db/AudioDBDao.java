package com.kiosk.dao.db;

import java.util.List;

import com.kiosk.model.Audio;

/**
 * Author: Sam Cox Date: 06/01/2012 AudioDBDao.Java: Audio interface to group
 * related methods
 */

public interface AudioDBDao {

	public List<Audio> getAudio();

	public Audio getIndividualAudio(int id);

	public int addAudio(Audio a, String today);

	public int deleteAudio(int id);

}
