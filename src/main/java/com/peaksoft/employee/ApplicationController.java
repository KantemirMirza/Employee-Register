package com.peaksoft.employee;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ApplicationController {
    private final UserDetailsService userDetailsService;

    @GetMapping("/index")
    public String dashboard(Model model, Principal principal){
            UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
            model.addAttribute("userDetails", userDetails);
        return"dashboard/index";
    }
}
