package com.kiosk.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kiosk.model.Transaction;

@Repository
public class AdminDBDaoImpl extends BaseDao implements AdminDBDao {

	// private static final Logger LOG = Logger.getLogger(AdminDBDaoImpl.class);

	@Override
	public List<Transaction> getTransactions() {

		return getJdbcTemplate()
				.query("SELECT Transaction_ID, Pin,PostCode,Email, Customer_Name, Customer_Addr,Phone_No,Creation_Date, Cust_Language,Cust_Level,Payment_Status, Payment_Amount from transaction,payment where transaction.TPayment_ID = payment.Payment_ID order by transaction_id asc",
						new RowMapper<Transaction>() {

							public Transaction mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Transaction t = new Transaction();

								t.setTransID(rs.getInt("Transaction_ID"));
								t.setPin(rs.getInt("Pin"));
								t.setName(rs.getString("Customer_Name"));
								t.setAddress(rs.getString("Customer_Addr"));
								t.setTelNo(rs.getString("Phone_No"));
								t.setCreatedOn(rs.getDate("Creation_Date"));
								t.setLanguage(rs.getString("Cust_Language"));
								t.setLevel(rs.getString("Cust_Level"));
								t.setPrice(rs.getDouble("Payment_Amount"));
								t.setPaymentStatus(rs
										.getString("Payment_Status"));
								t.setPostCode(rs.getString("PostCode"));
								t.setEmail(rs.getString("Email"));

								return t;

							}
						});

	}

}
