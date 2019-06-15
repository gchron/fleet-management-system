package pl.sda.fleetmanagementsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.fleetmanagementsystem.service.DriverService;

import java.util.List;

/**
 * @author Mariusz Kowalczuk
 */
@Controller
@RequestMapping("drivers")
public class DriverController {

    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @RequestMapping({"", "/"})
    public ModelAndView findAllDrivers() {
        ModelAndView modelAndView = new ModelAndView("index.html");
        modelAndView.addObject("drivers", driverService.findAll());
        return modelAndView;
    }
}
