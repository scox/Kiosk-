package com.kiosk.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kiosk.model.Tariff;

@Repository
public class TariffDBDaoImpl extends BaseDao implements TariffDBDao {

	// private static final Logger LOG = Logger.getLogger(AdminDBDaoImpl.class);

	public List<Tariff> getTariff() {

		return getJdbcTemplate()
				.query("SELECT price_ID,price, audio_level FROM price group by Upper(audio_level) order by price_ID asc", new RowMapper<Tariff>() {

					public Tariff mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Tariff l = new Tariff();
						l.setLevel(rs.getString("audio_level"));
						l.setPrice(rs.getDouble("price"));
						l.setTariffID(rs.getInt("price_ID"));
						return l;
					}
				});

	}

	@Override
	public int deleteTariff(int tariffID) {
		return getJdbcTemplate().update("DELETE from price where price_id = ?", new Object[] {tariffID});
	}

	@Override
	public Tariff getIndividualTariff(int tariffID) {
		
		List<Tariff> tariffs = getJdbcTemplate()
		.query("SELECT price_ID,price, audio_level FROM price where price_ID = ?",new Object[] {tariffID}, new RowMapper<Tariff>() {

			public Tariff mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Tariff l = new Tariff();
				l.setLevel(rs.getString("audio_level"));
				l.setPrice(rs.getDouble("price"));
				l.setTariffID(rs.getInt("price_ID"));
				return l;
			}
		});
		
		Tariff tariffResult = null;

		if (tariffs != null && tariffs.size() > 0)
			tariffResult = tariffs.get(0);
		
		return tariffResult;
	}

	@Override
	public int updateTariff(int id, Double price, String level) {
		return getJdbcTemplate().update("update price set price = ?, audio_level =? where price_id = ?", new Object[] {price,level,id});
	}

	@Override
	public int addTariff(String level, Double price) {
		// TODO Auto-generated method stub
		return getJdbcTemplate().update("insert into price(audio_level, price) values (?,?)", new Object[] {level,price});
	}

	
	
	
}
