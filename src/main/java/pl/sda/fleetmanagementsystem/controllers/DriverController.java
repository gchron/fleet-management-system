package pl.sda.fleetmanagementsystem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.fleetmanagementsystem.dto.DriverDto;
import pl.sda.fleetmanagementsystem.service.DriverFinder;
import pl.sda.fleetmanagementsystem.service.DriverService;

/**
 * @author Mariusz Kowalczuk
 */
@Controller
@RequestMapping("/drivers")
@AllArgsConstructor
public class DriverController {

    private DriverFinder driverFinder;
    private DriverService driverService;

    @RequestMapping({"", "/"})
    public ModelAndView showAllDrivers() {

        ModelAndView modelAndView = new ModelAndView("drivers/index.html");
        modelAndView.addObject("drivers", driverFinder.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    ModelAndView createDriverView() {
        ModelAndView modelAndView = new ModelAndView("drivers/create.html");
        modelAndView.addObject("driver", new DriverDto());
        return modelAndView;
    }

    @PostMapping("/create")
    String createUser(@ModelAttribute DriverDto driver) {
        driverService.create(driver);
        return "redirect:/drivers";
    }
}
