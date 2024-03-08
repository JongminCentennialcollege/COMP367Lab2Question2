package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import java.time.LocalTime;


@Controller
public class WelcomeController {
    
    @GetMapping("/")
    public String welcome(Model model) {
String greeting;
        
    
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isBefore(LocalTime.of(12, 0))) {
            greeting = "Good morning";
        } else {
            greeting = "Good afternoon";
        }

      
        String name = "JongminLee"; 

        model.addAttribute("message", greeting + ", " + name + ", Welcome to COMP367");
        return "welcome"; 
    }
}