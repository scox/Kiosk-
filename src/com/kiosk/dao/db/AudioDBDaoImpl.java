package com.kiosk.dao.db;

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
				.query("SELECT audio_id,a_level,a_language,filename,date_created,room_no,exhibit_no,track_info from Audio order by audio_id asc",
						new RowMapper<Audio>() {
 
							public Audio mapRow(ResultSet rs, int rowNum)
									throws SQLException {

								Audio a = new Audio();
								a.setAudioID(rs.getInt("audio_id"));
								a.setLanguage(rs.getString("a_language"));
								a.setLevel(rs.getString("a_level"));
								a.setRoomNo(rs.getInt("room_no"));
								a.setDateCreated(rs.getDate("date_created"));
								a.setFilename(rs.getString("filename"));
								a.setExhibitNumber(rs.getInt("exhibit_no"));
								a.setTrackInfo(rs.getString("track_info"));
								
								return a;

							}
						});

	}

	@Override
	public Audio getIndividualAudio(int id) {

		List<Audio> audios = getJdbcTemplate().query(
				"SELECT audio_id,a_level,a_language,track_info,filename,date_created,"
						+ "room_no,exhibit_no from Audio where audio_id = ?",
				new Object[] { id }, new RowMapper<Audio>() {

					public Audio mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						Audio a = new Audio();
						a.setAudioID(rs.getInt("audio_id"));
						a.setLanguage(rs.getString("a_language"));
						a.setLevel(rs.getString("a_level"));
						a.setRoomNo(rs.getInt("room_no"));
						a.setDateCreated(rs.getDate("date_created"));
						a.setFilename(rs.getString("filename"));
						a.setExhibitNumber(rs.getInt("exhibit_no"));
						a.setTrackInfo(rs.getString("track_info"));
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
	
		return getJdbcTemplate()
				.update("insert into audio (a_level, filename,track_info,date_created,a_language,room_no,exhibit_no)"
						+ " VALUES (?,?,?,?,?,?,?)",
						new Object[] { a.getLevel(), a.getFilename(),
								a.getTrackInfo(), today, a.getLanguage(),
								a.getRoomNo(),a.getExhibitNumber()});
	}

	@Override
	public int deleteAudio(int id) {
		return getJdbcTemplate().update("delete from audio where audio_id = ?",
				new Object[] { id });
	}

}
