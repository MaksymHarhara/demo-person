package com.example.demoperson.controller;

import com.example.demoperson.dto.PersonDto;
import com.example.demoperson.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/{id}")
	public PersonDto getPersonById(@PathVariable Long id) {
		return personService.getPersonById(id);
	}
}
