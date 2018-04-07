package com.poyu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poyu.model.Person;
import com.poyu.web.service.IPersonService;

@Controller
@RequestMapping(value="/person")
public class PersonController {

	private final IPersonService personService;
	
	@Autowired
	public PersonController(IPersonService personService){
		this.personService = personService;
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("listPersons", this.personService.listPersons());
		return "person";
	}

	// For add and update person both
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p) {
		if (p.getId() == 0) {
			// new person, add it
			this.personService.addPerson(p);
		} else {
			// existing person, call update
			this.personService.updatePerson(p);
		}
		return "redirect:/person/query";
	}

	@RequestMapping("/remove/{id}")
	public String removePerson(@PathVariable("id") int id) {
		this.personService.removePerson(id);
		return "redirect:/person/query";
	}

	@RequestMapping("/edit/{id}")
	public String editPerson(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", this.personService.getPersonById(id));
		model.addAttribute("listPersons", this.personService.listPersons());
		return "person";
	}

}
