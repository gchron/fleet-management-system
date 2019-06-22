package pl.sda.fleetmanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.fleetmanagementsystem.dto.CreateUserAssignment;
import pl.sda.fleetmanagementsystem.repository.RoleRepository;
import pl.sda.fleetmanagementsystem.service.UserService;

/**
 * @author Mariusz Kowalczuk
 */
@Controller
@RequiredArgsConstructor
public class UserController {
    private final RoleRepository roleRepository;
    private final UserService userService;

    @GetMapping("/register")
    public ModelAndView registerUser(){
        ModelAndView modelAndView = new ModelAndView("register.html");
        modelAndView.addObject("roles", roleRepository.findAll());
        modelAndView.addObject("assignment", new CreateUserAssignment());
        return modelAndView;
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute CreateUserAssignment assignment){
        userService.register(assignment);
        return "redirect:/";
    }


}
