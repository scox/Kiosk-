package com.kiosk.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.dao.db.AudioDBDao;
import com.kiosk.model.Audio;

@Service
public class AudioServiceImpl implements AudioService {

	@Autowired
	private AudioDBDao audioDBDao;

	@Override
	public List<Audio> getAudio() {
		return audioDBDao.getAudio();
	}

	@Override
	public Audio getIndividualAudio(int id) {
		// TODO Auto-generated method stub
		return audioDBDao.getIndividualAudio(id);
	}

	@Override
	public boolean addAudio(Audio a) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (audioDBDao
				.addAudio(a, sdf.format(Calendar.getInstance().getTime())) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteAudio(int id) {

		if (audioDBDao.deleteAudio(id) > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean editAudio(Audio a) {
		// TODO Auto-generated method stub

		if (audioDBDao.editAudio(a) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public byte[] getMP3(int audioID) {

		Blob blob = audioDBDao.getAudio(audioID);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buf = new byte[1024];
		byte[] bytes = null;
		try{
		InputStream in = blob.getBinaryStream();

		int n = 0;

		while ((n = in.read(buf)) >= 0) {

			baos.write(buf, 0, n);
		}

		in.close();

		bytes = baos.toByteArray();
		
		}
		catch(Exception e){
			bytes = null;
		}
		 
		return bytes;
		

	}

	
}