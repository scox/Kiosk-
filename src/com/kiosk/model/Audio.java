package com.kiosk.model;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Author: Sam Cox Date: 06/01/2012 Audio.Java: Java bean. Used to store audio
 * details
 */

public class Audio {

	private int audioID;
	private String level;
	private String language;
	private String filename;
	private int exhibitNumber;
	private String trackInfo;
	private int roomNo;
	private Date dateCreated;
	private CommonsMultipartFile audio;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getExhibitNumber() {
		return exhibitNumber;
	}

	public void setExhibitNumber(int exhibitNumber) {
		this.exhibitNumber = exhibitNumber;
	}

	public String getTrackInfo() {
		return trackInfo;
	}

	public void setTrackInfo(String trackInfo) {
		this.trackInfo = trackInfo;
	}

	public CommonsMultipartFile getAudio() {
		return audio;
	}

	public void setAudio(CommonsMultipartFile audio) {
		this.audio = audio;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getAudioID() {
		return audioID;
	}

	public void setAudioID(int audioID) {
		this.audioID = audioID;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

}
