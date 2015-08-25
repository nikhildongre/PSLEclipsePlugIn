package com.psl.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.psl.spring.model.Person;

//@Repository("personDAO")
public class MockPersonDAOImpl implements PersonDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MockPersonDAOImpl.class);

	Map<Integer,Person> persons = new HashMap<Integer,Person>();
	int count = 1;
	@Override
	public void addPerson(Person p) {
		p.setId(count++);
		persons.put(p.getId(), p);
		logger.info("Person saved successfully, Person Details="+p);
	}

	@Override
	public void updatePerson(Person p) {
		persons.put(p.getId(),p);  
		logger.info("Person updated successfully, Person Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons() {
		persons.values();
		List<Person> personList = new ArrayList<Person>();
		personList.addAll(persons.values());
		return personList;
	}

	@Override
	public Person getPersonById(int id) {
		logger.info("Person loaded successfully");
		return persons.get(id);
	}

	@Override
	public void removePerson(int id) {
		persons.remove(id);
		logger.info("Person deleted successfully");
	}

}
