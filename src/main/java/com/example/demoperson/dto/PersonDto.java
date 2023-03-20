package com.example.demoperson.dto;

import lombok.Getter;

@Getter
public class PersonDto {

	private final String firstName;
	private final String lastName;
	private final int age;

	public PersonDto(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
}
