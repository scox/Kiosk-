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
 * LoginDBDaoImpl.Java:  Implementation of methods related to the login process of an administrator
 */
@Repository
public class LoginDBDaoImpl extends BaseDao implements LoginDBDao {

	// private static final Logger LOG = Logger.getLogger(LoginDBDaoImpl.class);

	@Override
	public User getAuthenticateUser(String usr, String pw) {

		List<User> user = getJdbcTemplate()
				.query("select * from administrator where username = ? and password = ?",
						new Object[] { usr, pw }, new RowMapper<User>() {
							public User mapRow(ResultSet rs, int rowNum)
									throws SQLException {

								User u = new User();
								u.setAccessRight(rs.getInt("access_right"));
								u.setPassword(rs.getString("password"));
								u.setUserID(rs.getInt("user_ID"));
								u.setUsername(rs.getString("username"));
								return u;

							}
						});

		User userResult = null;

		if (user != null && user.size() > 0)
			userResult = user.get(0);

		return userResult;

	}

}
