package com.psl.pluggin.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.psl.pluggin.model.Audit;
@Component(value="auditDao")
public class AuditDAOImpl implements AuditDAO{
	
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void save(Audit ad) {
		System.out.println("JBDC:"+jdbcTemplate);
		String query = "insert into Audit (uname,pwd,url,crte_tms,upd_tms) values (?,?,?,?,?)";
		jdbcTemplate.update(query, new Object[] { ad.getUname(), ad.getPwd(),ad.getUrl(),ad.getCreatedDate(),ad.getUpdatedDate()});
		
	}

}
