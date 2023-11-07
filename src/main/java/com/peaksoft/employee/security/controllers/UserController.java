package com.peaksoft.employee.security.controllers;

import com.peaksoft.employee.parameter.service.models.User;
import com.peaksoft.employee.parameter.service.models.UserDTO;
import com.peaksoft.employee.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @GetMapping("/userAccount")
    public String client(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userDetails", userDetails);
        return"parameter/security/index";
    }

    @GetMapping("/userAccounts")
    public String listOfUsers(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("listOfAccounts", userService.listOfUsers());
        return"parameter/security/listOfUsers";
    }

    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("createUser", new UserDTO());
        return "parameter/security/register";
    }

    @PostMapping("/register")
    public String setRegister(@ModelAttribute("createUser") UserDTO userDTO, Model model ){
        userService.saveUser(userDTO);
        model.addAttribute("successMessage", "Registration Successful!!!");
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String login(){
        return"parameter/security/login";
    }

    @GetMapping("/userAccounts/{id}/delete")
    public String deleteAccount(@PathVariable Long id){
        User user = userService.findUserById(id);
        userService.deleteUser(user);
        return"redirect:/userAccounts";
    }

    @GetMapping("/userAccounts/{id}/info")
    public String getInfoAccount(Model model, @PathVariable Long id, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userDetails", userDetails);

        User user = userService.findUserById(id);
        model.addAttribute("infoUser", user);
        return"parameter/security/infoUser";
    }
}
