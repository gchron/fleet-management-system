package pl.sda.fleetmanagementsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.fleetmanagementsystem.service.DriverService;

/**
 * @author Mariusz Kowalczuk
 */
@Controller
@RequestMapping("/drivers")
public class DriverController {

    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @RequestMapping({"", "/"})

    public ModelAndView showAllDrivers() {

        ModelAndView modelAndView = new ModelAndView("drivers/index.html");
        modelAndView.addObject("drivers", driverService.findAll());
        return modelAndView;
    }
}
