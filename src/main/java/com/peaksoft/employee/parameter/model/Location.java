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
public class Location {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;

	    @ManyToOne
	    @JoinColumn(nullable = false)
	    private State state;

	    @NotEmpty(message = "{NotEmpty.locationCity}")
	    @Size(min = 2, max = 32, message = "{Size.locationCity}")
	    private String city;

		@NotEmpty(message = "{NotEmpty.locationAddress}")
		@Size(min = 2, max = 64, message = "{Size.locationAddress}")
	    private String address;

	    @NotEmpty(message = "{NotEmpty.locationDetails}")
	    @Size(min = 2, max = 32, message = "{Size.locationDetails}")
	    private String details;

	    @OneToMany(mappedBy="location", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	    private List<Employee> employees;
}
