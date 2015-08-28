package com.psl.pluggin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.psl.pluggin.model.Audit;


@Component(value="auditDao")
public class AuditDAOImpl implements AuditDAO{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	public void save(Audit ad) {
		System.out.println("JBDC:"+jdbcTemplate);
		String query = "insert into Audit (uname,pwd,url,crte_tms,upd_tms) values (?,?,?,?,?)";
		jdbcTemplate.update(query, new Object[] { ad.getUname(), ad.getPwd(),ad.getUrl(),ad.getCreatedDate(),ad.getUpdatedDate()});
		
	}

}
