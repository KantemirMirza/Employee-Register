package com.peaksoft.employee.parameter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Employee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Country country;

	@ManyToOne
	@JoinColumn(nullable = false)
	private State state;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Location location;

	@NotEmpty(message = "{NotEmpty.employeeFirstName}")
	@Size(min = 2, max = 32, message = "{Size.employeeFirstName}")
	private String firstName;

	@NotEmpty(message = "{NotEmpty.employeeLastName}")
	@Size(min = 2, max = 32, message = "{Size.employeeLastName}")
	private String lastName;

	@NotEmpty(message = "{NotEmpty.employeeOccupation}")
	@Size(min = 2, max = 32, message = "{Size.employeeOccupation}")
	private String occupation;

	private String gender;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	private String maritalStatus;

	@NotEmpty(message = "{NotEmpty.employeePhone}")
	@Pattern(regexp = "\\d{10}", message = "{Pattern.employeePhone}")
	private String phone;

	@NotEmpty(message = "{NotEmpty.employeeEmail}")
	@Email(message = "{Email.employeeEmail}")
	private String email;

	@NotEmpty(message = "{NotEmpty.employeePhoto}")
	@Pattern(regexp = ".*\\.(jpg|jpeg|png)$", message = "{Pattern.employeePhoto}")
	private String photo;
}
