package com.poyu.web.service;

import java.util.List;

import com.poyu.model.Person;

public interface IPersonService {

	public void addPerson(Person p);

	public void updatePerson(Person p);

	public List<Person> listPersons();

	public Person getPersonById(int id);

	public void removePerson(int id);

}
