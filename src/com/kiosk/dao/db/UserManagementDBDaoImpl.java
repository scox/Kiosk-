package com.kiosk.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.kiosk.model.User;

/**
 * Author: Sam Cox
 * Date: 06/01/2012
 * UserManagmentDBDaoImpl.Java:  Implementation of methods requiring database queries for audio information
 */

@Repository
public class UserManagementDBDaoImpl extends BaseDao implements
		UserManagementDBDao {

	// private static final Logger LOG = Logger
	// .getLogger(UserManagementDaoImpl.class);

	@Override
	public int addUser(String username, String password, int access) {
		return getJdbcTemplate()
				.update("insert into administrator (username,password,access_right) values (?,?,?)",
						new Object[] { username, password, access });

	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return getJdbcTemplate().query("SELECT * from Administrator",
				new RowMapper<User>() {

					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User u = new User();
						u.setAccessRight(rs.getInt("Access_Right"));
						u.setPassword(rs.getString("password"));
						u.setUserID(rs.getInt("User_ID"));
						u.setUsername(rs.getString("username"));
						return u;
					}
				});

	}

	@Override
	public int deleteUser(int userID) {
		return getJdbcTemplate().update(
				"delete from administrator where user_id = ?",
				new Object[] { userID });

	}

	@Override
	public int editAccess(int userID, int access) {
		return getJdbcTemplate().update(
				"Update administrator set access_right = ? where user_id = ?",
				new Object[] { access, userID });

	}

	@Override
	public int changePassword(int userID, String newPassword) {
		return getJdbcTemplate().update(
				"Update administrator set password = ? where user_id = ?",
				new Object[] { newPassword, userID });

	}

}
