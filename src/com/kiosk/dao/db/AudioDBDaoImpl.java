package com.kiosk.dao.db;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kiosk.model.Audio;

@Repository
public class AudioDBDaoImpl extends BaseDao implements AudioDBDao {

	// private static final Logger LOG = Logger.getLogger(AdminDBDaoImpl.class);

	@Override
	public List<Audio> getAudio() {

		return getJdbcTemplate()
				.query("SELECT audio_id,audio,a_level,a_language,title,description,date_created,room_no from Audio order by audio_id asc",
						new RowMapper<Audio>() {

							public Audio mapRow(ResultSet rs, int rowNum)
									throws SQLException {

								Audio a = new Audio();
								a.setAudioID(rs.getInt("audio_id"));
								a.setDescription(rs.getString("description"));
								a.setLanguage(rs.getString("a_language"));
								a.setLevel(rs.getString("a_level"));
								a.setRoomNo(rs.getInt("room_no"));
								a.setTitle(rs.getString("title"));
								a.setDateCreated(rs.getDate("date_created"));
								a.setMp3(rs.getBlob("audio"));
								return a;

							}
						});

	}

	@Override
	public Audio getIndividualAudio(int id) {

		List<Audio> audios = getJdbcTemplate().query(
				"SELECT audio_id,audio,a_level,a_language,title,description,date_created,"
						+ "room_no from Audio where audio_id = ?",
				new Object[] { id }, new RowMapper<Audio>() {

					public Audio mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Audio a = new Audio();
						a.setAudioID(rs.getInt("audio_id"));
						a.setDescription(rs.getString("description"));
						a.setLanguage(rs.getString("a_language"));
						a.setLevel(rs.getString("a_level"));
						a.setRoomNo(rs.getInt("room_no"));
						a.setTitle(rs.getString("title"));
						a.setDateCreated(rs.getDate("date_created"));
						a.setMp3(rs.getBlob("audio"));
						return a;

					}
				});

		Audio audioResult = null;

		if (audios != null && audios.size() > 0)
			audioResult = audios.get(0);

		return audioResult;

	}

	@Override
	public int addAudio(Audio a, String today) {
		// TODO Auto-generated method stub

		return getJdbcTemplate()
				.update("insert into audio (a_level, title,description,date_created,a_language,room_no,audio)"
						+ " VALUES (?,?,?,?,?,?,?)",
						new Object[] { a.getLevel(), a.getTitle(),
								a.getDescription(), today, a.getLanguage(),
								a.getRoomNo(), a.getAudio().getBytes() });
	}

	@Override
	public int deleteAudio(int id) {
		// TODO Auto-generated method stub
		return getJdbcTemplate().update("delete from audio where audio_id = ?",
				new Object[] { id });
	}

	@Override
	public int editAudio(Audio a) {
		return getJdbcTemplate()
				.update("update audio set title = ?, description = ?,a_language = ?, a_level =?, room_no =? where audio_id =?",
						new Object[] { a.getTitle(), a.getDescription(),
								a.getLanguage(), a.getLevel(), a.getRoomNo(),
								a.getAudioID() });
	}

	@Override
	public Blob getAudio(int audioID) {

		List<Blob> audios = getJdbcTemplate().query(
				"SELECT audio from Audio where audio_id = ?",
				new Object[] { audioID }, new RowMapper<Blob>() {

					public Blob mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						return rs.getBlob("audio");

					}
				});

		Blob audioResult = null;

		if (audios != null && audios.size() > 0)
			audioResult = audios.get(0);

		return audioResult;

	}

}
