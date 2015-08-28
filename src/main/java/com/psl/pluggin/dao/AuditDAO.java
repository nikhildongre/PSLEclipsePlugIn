package com.psl.pluggin.dao;

import org.springframework.stereotype.Repository;

import com.psl.pluggin.model.Audit;


public interface AuditDAO {
	public void save(Audit ad);
}
