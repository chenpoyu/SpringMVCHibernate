package com.poyu.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poyu.dao.IPersonDao;
import com.poyu.model.Person;
import com.poyu.api.service.IApiPersonService;

@Service
public class ApiPersonServiceImpl implements IApiPersonService {

	@Autowired
	private IPersonDao personDao;

	public void setPersonDao(IPersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	@Transactional
	public void addPerson(Person p) {
		this.personDao.addPerson(p);
	}

	@Override
	@Transactional
	public void updatePerson(Person p) {
		this.personDao.updatePerson(p);
	}

	@Override
	public List<Person> listPersons() {
		return this.personDao.listPersons();
	}

	@Override
	public Person getPersonById(int id) {
		return this.personDao.getPersonById(id);
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		this.personDao.removePerson(id);
	}

}
