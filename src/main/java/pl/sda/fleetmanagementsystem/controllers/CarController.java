package pl.sda.fleetmanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.fleetmanagementsystem.dto.CarDriverAssigmentDto;
import pl.sda.fleetmanagementsystem.dto.NewCarDto;
import pl.sda.fleetmanagementsystem.service.CarFinder;
import pl.sda.fleetmanagementsystem.service.CarService;
import pl.sda.fleetmanagementsystem.service.DriverFinder;

/**
 * @author Mariusz Kowalczuk
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarFinder carFinder;
    private final CarService carService;
    private final DriverFinder driverFinder;

    @RequestMapping({"", "/"})
    ModelAndView findAllCars() {
        ModelAndView modelAndView = new ModelAndView("cars/index.html");
        modelAndView.addObject("cars", carFinder.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    ModelAndView createCar() {
        ModelAndView modelAndView = new ModelAndView("cars/create.html");
        modelAndView.addObject("car", new NewCarDto());
        return modelAndView;
    }

    @PostMapping("/create")
    String createCar(@ModelAttribute NewCarDto car) {
        carService.create(car);

        return "redirect:/cars/index.html";

    }

    @GetMapping("/{carId}/setDriver")
    ModelAndView setDriver(@PathVariable Integer carId) {
        ModelAndView modelAndView = new ModelAndView("cars/setDriver.html");
        modelAndView.addObject("drivers", driverFinder.findAll());
        modelAndView.addObject("carId", carId);
        modelAndView.addObject("assignment", new CarDriverAssigmentDto());
        return modelAndView;
    }


    @PostMapping("/setDriver")
    String setDriver(@ModelAttribute CarDriverAssigmentDto assignment) {
        carService.setDriver(assignment.getCarId(), assignment.getDriverId());

        return "redirect:/cars/index.html";

    }
}

