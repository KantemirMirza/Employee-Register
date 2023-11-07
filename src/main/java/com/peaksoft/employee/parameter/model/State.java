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
public class State {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //

	@ManyToOne
	@JoinColumn(nullable = false)
	private Country country; //

	@NotEmpty(message = "{NotEmpty.stateName}")
	@Size(min = 2, max = 32, message = "{Size.stateName}")
	private String stateName;

	@NotEmpty(message = "{NotEmpty.stateCapital}")
	@Size(min = 2, max = 32, message = "{Size.stateCapital}")
	private String capital;

	@NotEmpty(message = "{NotEmpty.stateCode}")
	@Size(min = 2, max = 32, message = "{Size.stateCode}")
    private String code;

	@NotEmpty(message = "{NotEmpty.stateDetails}")
	@Size(min = 2, max = 32, message = "{Size.stateDetails}")
	private String details;

	@OneToMany(mappedBy="state", cascade = CascadeType.ALL)
	private List<Location> locations;

	@OneToMany(mappedBy="state", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Employee> employees;
}
