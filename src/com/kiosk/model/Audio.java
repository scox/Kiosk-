package com.kiosk.model;

import java.sql.Blob;
import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Audio {

	private int audioID;
	private String level;
	private String language;
	private Blob mp3;
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

	public Blob getMp3() {
		return mp3;
	}

	public void setMp3(Blob mp3) {
		this.mp3 = mp3;
	}

	
	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

}
