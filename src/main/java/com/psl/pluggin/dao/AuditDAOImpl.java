package com.psl.pluggin.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.psl.pluggin.controller.UserController;
import com.psl.pluggin.model.Audit;

@Component(value = "auditDao")
public class AuditDAOImpl implements AuditDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void save(Audit ad) {
		try {
			String query = "insert into Audit (uname,pwd,url,crte_tms,upd_tms) values (?,?,?,?,?)";
			jdbcTemplate.update(
					query,
					new Object[] { ad.getUname(), ad.getPwd(), ad.getUrl(),
							ad.getCreatedDate(), ad.getUpdatedDate() });
		} catch (Exception e) {
			logger.error("Error while adding values of User:" + ad.getUname()
					+ "\n" + e.getMessage());
		}

	}

}
