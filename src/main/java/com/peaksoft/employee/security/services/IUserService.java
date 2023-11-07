package com.peaksoft.employee.security.services;

import com.peaksoft.employee.parameter.service.models.User;
import com.peaksoft.employee.parameter.service.models.UserDTO;

import java.util.List;

public interface IUserService {
    public User saveUser(UserDTO userDTO);
    public User findByEmail(String email);
    public List<User> listOfUsers();
    public User findUserById(Long id);
    public void deleteUser(User user);
}
