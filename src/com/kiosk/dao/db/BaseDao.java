package com.kiosk.dao.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class BaseDao extends JdbcDaoSupport {

	@Autowired
	public void setTheDataSource(DataSource ds) {
		super.setDataSource(ds);
	}

	@Override
	protected final void checkDaoConfig() {
		super.checkDaoConfig();
	}

}
