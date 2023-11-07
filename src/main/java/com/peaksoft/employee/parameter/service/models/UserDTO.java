package com.peaksoft.employee.parameter.service.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$";
    @NotEmpty(message = "{NotEmpty.userFirstName}")
    @Size(min = 2, max = 32, message = "{Size.userFirstName}")
    private String firstName;
    @NotEmpty(message = "{NotEmpty.userLastName}")
    @Size(min = 2, max = 32, message = "{Size.userLastName}")
    private String lastName;
    @NotEmpty(message = "{NotEmpty.userEmail}")
    @Email(message = "{Email.userEmail}")
    private String email;
    @NotEmpty(message = "{NotEmpty.userPhone}")
    @Pattern(regexp = "\\+90\\(\\d{3}\\)\\d{3}-\\d{3}", message = "{Pattern.userPhone}")
    private String phone;
    @NotEmpty(message = "{NotEmpty.userPassword}")
    @Size(min = 8, max = 32, message = "{Size.userPassword}")
    @Pattern(regexp = PASSWORD_PATTERN, message = "{Pattern.userPassword}")
    private String password;

    @NotEmpty(message = "{NotEmpty.confirmPassword}")
    @Size(min = 8, max = 32, message = "{Size.confirmPassword}")
    @Pattern(regexp = PASSWORD_PATTERN, message = "{Pattern.confirmPassword}")
    private String confirmPassword;

    private List<Role> roles;
}
