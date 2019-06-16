package pl.sda.fleetmanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.fleetmanagementsystem.dto.NewCarDto;
import pl.sda.fleetmanagementsystem.service.CarFinder;
import pl.sda.fleetmanagementsystem.service.CarService;

/**
 * @author Mariusz Kowalczuk
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarFinder carFinder;
    private final CarService carService;

    @RequestMapping({"", "/"})
    ModelAndView findAllCars(){
        ModelAndView modelAndView = new ModelAndView("cars/index.html");
        modelAndView.addObject("cars", carFinder.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    ModelAndView createCar(){
        ModelAndView modelAndView = new ModelAndView("cars/create.html");
        modelAndView.addObject("car", new NewCarDto());
        return modelAndView;
    }
    @PostMapping("/create")
    String createCar(@ModelAttribute NewCarDto car){
        carService.create(car);

        return "redirect:/cars/index.html";

    }
}
