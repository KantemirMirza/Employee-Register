package com.peaksoft.employee.parameter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Country {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "{NotEmpty.countryName}")
	@Size(min = 2, max = 32, message = "{Size.countryName}")
	private String countryName;

	@NotEmpty(message = "{NotEmpty.countryDescription}")
	@Size(min = 2, max = 32, message = "{Size.countryDescription}")
	private String description;

	@NotEmpty(message = "{NotEmpty.countryCapital}")
	@Size(min = 2, max = 32, message = "{Size.countryCapital}")
	private String capital;

	@NotEmpty(message = "{NotEmpty.countryCode}")
	@Size(min = 2, max = 32, message = "{Size.countryCode}")
	private String code;

	@NotEmpty(message = "{NotEmpty.countryNationality}")
	@Size(min = 2, max = 32, message = "{Size.countryNationality}")
	private String nationality;

	@NotEmpty(message = "{NotEmpty.countryContinent}")
	@Size(min = 2, max = 32, message = "{Size.countryContinent}")
	private String continent;
	
	@OneToMany(mappedBy="country", cascade = CascadeType.ALL)
	private List<State> states;

	@OneToMany(mappedBy="country", cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	private List<Employee> employees;
}
