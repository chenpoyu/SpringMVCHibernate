package com.poyu.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.poyu.dao.IPersonDao;
import com.poyu.model.Person;

@Repository
public class PersonDaoImpl extends CommonBaseDaoImpl<Person> implements IPersonDao {

	private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

	@Override
	public void addPerson(Person p) {
		this.saveModel(p);
		logger.info("Person saved successfully, Person Details=" + p);
	}

	@Override
	public void updatePerson(Person p) {
		this.updateModel(p);
		logger.info("Person updated successfully, Person Details=" + p);
	}

	@Override
	public List<Person> listPersons() {
		List<Person> personsList = this.getAllModel(new Person());
		for (Person p : personsList) {
			logger.info("Person List::" + p);
		}
		return personsList;
	}

	@Override
	public Person getPersonById(int id) {
		Person p = this.getModel(new Person(), new Integer(id));
		logger.info("Person loaded successfully, Person details=" + p);
		return p;
	}

	@Override
	public void removePerson(int id) {
		Person p = this.getModel(new Person(), new Integer(id));
		if (null != p) {
			this.deleteModel(p);
		}
		logger.info("Person deleted successfully, person details=" + p);
	}

}
