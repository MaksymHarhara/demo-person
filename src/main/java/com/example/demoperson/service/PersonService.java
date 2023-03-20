package com.example.demoperson.service;

import com.example.demoperson.dto.PersonDto;
import com.example.demoperson.model.Person;
import com.example.demoperson.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;

@Service
public class PersonService {

	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public PersonDto getPersonById(Long id) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return new PersonDto(person.getFirstName(), person.getLastName(), calculateAge(person.getDateOfBirth()));
	}

	private int calculateAge(LocalDate birthDate) {
		return Period.between(birthDate, LocalDate.now()).getYears();
	}
}
