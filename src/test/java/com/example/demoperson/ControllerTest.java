package com.example.demoperson;

import com.example.demoperson.model.Person;
import com.example.demoperson.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PersonRepository repository;

	@Test
	void testGetPersonById() throws Exception {
		Person person = new Person();
		person.setFirstName("Maksym");
		person.setLastName("Harhara");
		person.setDateOfBirth(LocalDate.of(2001, 5, 14));
		repository.save(person);
		Long personId = person.getId();

		System.out.println(personId);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/" + personId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Maksym"))
				.andExpect(jsonPath("$.lastName").value("Harhara"))
				.andExpect(jsonPath("$.age").value(21));
	}

	@Test
	void testGetPersonById_notFound() throws Exception {
		Long nonExistentId = 2L;

		mockMvc.perform(MockMvcRequestBuilders.get("/api/persons/" + nonExistentId))
				.andExpect(status().isNotFound());
	}
}
