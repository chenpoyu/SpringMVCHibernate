package com.poyu.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poyu.model.Person;
import com.poyu.api.service.IApiPersonService;

@RestController
@RequestMapping(value = "/api/person", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiPersonController {

	@Autowired
	private IApiPersonService apiPersonService;

	@GetMapping("/")
	public List<Person> getPersons() {
		List<Person> list = apiPersonService.listPersons();
		return list;
	}

	@GetMapping("/{id}")
	public Person getPerson(@PathVariable("id") int id) {
		return apiPersonService.getPersonById(id);
	}

	@PostMapping("/")
	public Person addPerson(@RequestBody Person person) {
		apiPersonService.addPerson(person);
		return apiPersonService.getPersonById(person.getId());

	}

	@PutMapping("/{id}")
	public Person updatePerson(@PathVariable("id") int id, @RequestBody Person person) {
		person.setId(id);
		apiPersonService.updatePerson(person);
		return apiPersonService.getPersonById(person.getId());
	}

	@DeleteMapping("/{id}")
	public void deletePerson(@PathVariable("id") int id) {
		apiPersonService.removePerson(id);
	}
}
