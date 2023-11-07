package com.peaksoft.employee.security.services;

import com.peaksoft.employee.parameter.service.models.Role;
import com.peaksoft.employee.parameter.service.models.User;
import com.peaksoft.employee.parameter.service.models.UserDTO;
import com.peaksoft.employee.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(UserDTO userDTO) {
        List<Role> userRoles = userDTO.getRoles();
        if (userRoles == null || userRoles.isEmpty()) {
            userRoles = List.of(new Role("USER"));
        }
        User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getPhone(),
                passwordEncoder.encode(userDTO.getPassword()), userRoles);
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found with: " + email));
    }
    @Override
    public List<User> listOfUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
