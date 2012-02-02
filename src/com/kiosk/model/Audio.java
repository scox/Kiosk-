package com.kiosk.model;

import java.sql.Blob;
import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Audio {

	private int audioID;
	private String level;
	private String language;
	private Blob mp3;
	private String title;
	private String description;
	private int roomNo;
	private Date dateCreated;
	private CommonsMultipartFile audio;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

}
