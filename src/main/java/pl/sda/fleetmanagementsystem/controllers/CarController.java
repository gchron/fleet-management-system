package pl.sda.fleetmanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.fleetmanagementsystem.dto.*;
import pl.sda.fleetmanagementsystem.service.CarFinder;
import pl.sda.fleetmanagementsystem.service.CarService;
import pl.sda.fleetmanagementsystem.service.DriverFinder;

import java.time.LocalDate;

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

        return "redirect:/cars";

    }

    @GetMapping("/setDriver/{carId}")
    ModelAndView setDriver(@PathVariable Integer carId) {
        ModelAndView modelAndView = new ModelAndView("cars/setDriver.html");
        modelAndView.addObject("drivers", driverFinder.findAll());
        modelAndView.addObject("carId", carId);
        modelAndView.addObject("assignment", new CarDriverAssignmentDto());
        return modelAndView;
    }


    @PostMapping("/setDriver")
    String setDriver(@ModelAttribute CarDriverAssignmentDto assignment) {
        carService.setDriver(assignment.getCarId(), assignment.getDriverId());

        return "redirect:/cars";

    }

    @GetMapping("/setTechnicalInspection")
    ModelAndView setInspectionDate() {
        ModelAndView modelAndView = new ModelAndView("cars/setTechnicalInspection.html");
        modelAndView.addObject("cars", carFinder.findAll());
        modelAndView.addObject("assignment", new CarInspectionAssignmentDto());
        return modelAndView;
    }

    @PostMapping("/setTechnicalInspection")
    String setInspectionDate(@ModelAttribute CarInspectionAssignmentDto assignment) {
        carService.setTechnicalInspection(assignment.getCarId(), LocalDate.parse(assignment.getDateOfNextInspection()));

        return "redirect:/cars";

    }

    @GetMapping("/deleteFromList")
    ModelAndView deleteCar() {
        ModelAndView modelAndView = new ModelAndView("cars/delete.html");
        modelAndView.addObject("cars", carFinder.findAll());
        modelAndView.addObject("assignment", new CarAssignmentDto());
        return modelAndView;

    }

    @PostMapping("/deleteFromList")
    String deleteCar(@ModelAttribute CarAssignmentDto assignment) {
        carService.delete(assignment.getCarId());
        return "redirect:/cars";
    }
    @GetMapping("/delete")
    String deleteCar(@RequestParam Integer id){
        carService.delete(id);
        return "redirect:/cars";
    }


    @GetMapping("/edit")
    ModelAndView updateCar(@RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView("cars/edit.html");
        modelAndView.addObject("car", carFinder.findById(id));
        return modelAndView;

    }

    @PostMapping("/edit")
    String updateCar(@ModelAttribute UpdateCarDto car) {
        carService.update(car);
        return "redirect:/cars";

    }

    @GetMapping("/show")
    public ModelAndView showCarsList() {
        ModelAndView modelAndView = new ModelAndView("cars/show.html");
        modelAndView.addObject("cars", carFinder.findAll());
        return modelAndView;
    }

    @GetMapping({"/details/{carId}", "drivers/cars/details/{carId}"})
    public ModelAndView showCarDetails(@PathVariable Integer carId) {
        ModelAndView modelAndView = new ModelAndView("cars/details.html");
        modelAndView.addObject("car", carFinder.findById(carId));


        return modelAndView;


    }

}

