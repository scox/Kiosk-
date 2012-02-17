package com.kiosk.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
		return audioDBDao.getIndividualAudio(id);
	}

	@Override
	public boolean addAudio(Audio a) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		a.setFilename(Constants.FILE_PATH + "\\" + a.getExhibitNumber() + "_"
				+ a.getLanguage() + "_" + a.getLevel()+".mp3");

		copyFileToDisk(a);

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
	public byte[] getMP3(String filePath) throws IOException {
		
			     File file = new File(filePath); 
			
			     if ( file.length() > 1024) { 
			
			   System.out.println("File to big");
			     } 
			  
			  
			     byte []buffer = new byte[(int) file.length()]; 
			     InputStream ios = null; 
		
			         ios = new FileInputStream(file); 
			         if ( ios.read(buffer) == -1 ) { 
			             throw new IOException("EOF reached while trying to read the whole file"); 
			         }         
			      
			         try { 
			              if ( ios != null )  
			                   ios.close(); 
			         } catch ( IOException e) { 
			         } 
			     
			     return buffer; 


	}

	public void copyFileToDisk(Audio a) {

		byte[] bytes = new byte[1024];
		bytes = a.getAudio().getBytes();

		try

		{

			FileOutputStream fos = new FileOutputStream(a.getFilename());

			

			fos.write(bytes);

			fos.close();

		}

		catch (FileNotFoundException ex)

		{

			System.out.println("FileNotFoundException : " + ex);

		}

		catch (IOException ioe)

		{

			System.out.println("IOException : " + ioe);

		}

	}
}