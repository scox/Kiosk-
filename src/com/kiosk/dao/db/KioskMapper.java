package com.kiosk.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.kiosk.model.Transaction;

public final class KioskMapper implements RowMapper<Transaction> {

	// if needed
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {

		return null;

	}

}
