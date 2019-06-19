package pl.sda.fleetmanagementsystem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.fleetmanagementsystem.dto.DriverDto;
import pl.sda.fleetmanagementsystem.dto.DriverLicenseAssignmentDto;
import pl.sda.fleetmanagementsystem.dto.DrivingLicenseDto;
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
        modelAndView.addObject("drivingLicense", new DrivingLicenseDto());

        return modelAndView;
    }

    @PostMapping("/create")
    String createUser(@ModelAttribute DriverDto driver) {
        driverService.create(driver);
        return "redirect:/drivers";
    }

    @GetMapping("/main/{id}")
    ModelAndView showUser(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("drivers/main.html");
        modelAndView.addObject("driver", driverFinder.findById(id));
        return modelAndView;

    }

    @GetMapping("/setDrivingLicense")
    ModelAndView setDrivingLicense(@RequestParam String id) {

        ModelAndView modelAndView = new ModelAndView("drivers/setDrivingLicense");
        modelAndView.addObject("driverId", id);
        modelAndView.addObject("assignment", new DriverLicenseAssignmentDto());
        return modelAndView;
    }

    @PostMapping("setDrivingLicense")
    String setDrivingLicense(@ModelAttribute DriverLicenseAssignmentDto assignment) {

        driverService.setDrivingLicense(Integer.valueOf(assignment.getDriverId()), assignment);
        return "redirect:/drivers";

    }

}
