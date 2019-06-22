package pl.sda.fleetmanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mariusz Kowalczuk
 */
@Controller
@RequiredArgsConstructor
public class LoginController {


    @GetMapping("/login")
    ModelAndView loginPage(@RequestParam(required = false) String error) {
        ModelAndView modelAndView = new ModelAndView("login.html");
        modelAndView.addObject("error", error);
        return modelAndView;
    }
}
