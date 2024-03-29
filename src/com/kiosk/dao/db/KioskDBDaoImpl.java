package com.kiosk.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kiosk.model.Tariff;
import com.kiosk.model.Transaction;

/**
 * Author: Sam Cox Date: 06/01/2012 KioskDBDaoImpl.Java: Implementation of
 * methods requiring database queries related to processing transactions and any
 * queries relating to the kiosk component
 */

@Repository
public class KioskDBDaoImpl extends BaseDao implements KioskDBDao {

	private static final Logger LOG = Logger.getLogger(KioskDBDaoImpl.class);

	@Override
	public int uplaodTransactionRecord(Transaction t, int paymentID,
			String today) {

		LOG.info("Insert transaction");
		return getJdbcTemplate()
				.update("insert into Transaction(TPayment_ID,Pin,Customer_Name,Customer_Addr,Phone_No"
						+ " ,Creation_Date,Cust_Language,Cust_Level,Payment_Status,PostCode,Email,Customer_Type,Member_Pin) Values (?,?,?,?,?,?,?,?,'Success',?,?,?,?)",
						new Object[] { paymentID, t.getPin(), t.getName(),
								t.getAddress(), t.getTelNo(), today,
								t.getLanguage(), t.getLevel(), t.getPostCode(),
								t.getEmail(), t.getCustomerType(),
								t.getMemberPin() });

	}

	public int uploadPaymentRecord(Transaction t) {

		// Convert card expiry month and year to date
		StringBuilder sb = new StringBuilder();
		sb.append(t.getYear());
		sb.append("-");
		sb.append(t.getMonth());
		sb.append("-");
		sb.append("01");

		return getJdbcTemplate().update(
				"insert into Payment(Card_Type,Payment_Amount,Card_No ,Sec_Code ,Exp_Date) "
						+ "Values (?,?,?,?,?)",
				new Object[] { t.getCardType(), t.getPrice(), t.getCardNo(),
						t.getSecCode(), sb.toString() });

	}

	// get tariff levels i.e Beginner,Advanced etc.
	public List<Tariff> getLevels() {

		return getJdbcTemplate()
				.query("SELECT price_ID,price, a_level FROM audio, price WHERE audio.a_level = price.audio_level"
						+ " GROUP BY audio.a_level", new RowMapper<Tariff>() {

					public Tariff mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Tariff l = new Tariff();
						l.setLevel(rs.getString("a_level"));
						l.setPrice(rs.getDouble("price"));
						l.setTariffID(rs.getInt("price_ID"));
						return l;
					}
				});

	}

	// get tariff languages i.e English, French etc
	public List<String> getLanguages() {

		return getJdbcTemplate().query(
				"select a_language from audio group by upper(a_language)",
				new RowMapper<String>() {

					public String mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return rs.getString("a_language");
					}
				});

	}

	@Override
	public int getLastPaymentID() {
		return getJdbcTemplate()
				.queryForInt(
						"SELECT Payment_ID FROM Payment ORDER BY Payment_ID DESC LIMIT 1");

	}

	@Override
	public int checkPinExists(int pin) {
		return getJdbcTemplate().queryForInt(
				"SELECT count(*) from transaction where pin = ?",
				new Object[] { pin });

	}

	@Override
	public int checkMemberPinExists(int memberPin) {
		return getJdbcTemplate().queryForInt(
				"SELECT count(*) from transaction where member_pin = ?",
				new Object[] { memberPin });
	}

}
