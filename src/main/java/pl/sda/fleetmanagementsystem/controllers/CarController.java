package pl.sda.fleetmanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.fleetmanagementsystem.dto.*;
import pl.sda.fleetmanagementsystem.service.*;

import javax.servlet.http.HttpServletRequest;

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
    private final UserFinder userFinder;
    private final DriverService driverService;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @RequestMapping({"", "/"})
    ModelAndView findAllCars() {
        ModelAndView modelAndView = new ModelAndView("cars/index.html");
        modelAndView.addObject("cars", carFinder.findAll());
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/create")
    ModelAndView createCar() {
        ModelAndView modelAndView = new ModelAndView("cars/create.html");
        modelAndView.addObject("car", new NewCarDto());
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/create")
    String createCar(@ModelAttribute NewCarDto car) {
        carService.create(car);

        return "redirect:/";

    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/setDriver/{carId}")
    ModelAndView setDriver(@PathVariable Integer carId) {
        ModelAndView modelAndView = new ModelAndView("cars/setDriver.html");
        modelAndView.addObject("drivers", driverFinder.findAll());
        modelAndView.addObject("carId", carId);
        modelAndView.addObject("assignment", new CarDriverAssignmentDto());
        return modelAndView;
    }


    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/setDriver")
    String setDriver(@ModelAttribute CarDriverAssignmentDto assignment) {
        carService.setDriver(assignment.getCarId(), assignment.getDriverId());

        return "redirect:/";

    }



    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/deleteFromList")
    ModelAndView deleteCar() {
        ModelAndView modelAndView = new ModelAndView("cars/delete.html");
        modelAndView.addObject("cars", carFinder.findAll());
        modelAndView.addObject("assignment", new CarAssignmentDto());
        return modelAndView;

    }
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/setTechnicalInspection")
    ModelAndView setTechnicalInspectionDate(@RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView("cars/setTechnicalInspection.html");
        modelAndView.addObject("assignment", new CarInspectionAssignmentDto());
        modelAndView.addObject("car", carFinder.findById(id));
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/setTechnicalInspection")
    String setTechnicalInspectionDate(@ModelAttribute CarInspectionAssignmentDto assignment) {
        carService.setTechnicalInspection(assignment.getCarId(), assignment.getDateOfNextInspection());

        return "redirect:/";

    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/deleteFromList")
    String deleteCar(@ModelAttribute CarAssignmentDto assignment) {
        carService.delete(assignment.getCarId());
        return "redirect:/index";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/delete")
    String deleteCar(@RequestParam Integer id){
        carService.delete(id);
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/edit")
    ModelAndView updateCar(@RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView("cars/edit.html");
        modelAndView.addObject("car", carFinder.findById(id));
        return modelAndView;

    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/edit")
    String updateCar(@ModelAttribute UpdateCarDto car) {
        carService.update(car);
        return "redirect:/";

    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/show")
    public ModelAndView showCarsList() {
        ModelAndView modelAndView = new ModelAndView("cars/show.html");
        modelAndView.addObject("cars", carFinder.findAll());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'DRIVER')")
    @GetMapping("/details/{carId}")
    public ModelAndView showCarDetails(@PathVariable Integer carId) {
        ModelAndView modelAndView = new ModelAndView("cars/details.html");
        modelAndView.addObject("car", carFinder.findById(carId));


        return modelAndView;


    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/reportAccident")
    String reportAccident(HttpServletRequest httpServletRequest, Model model, @RequestParam Integer carId) {

        try {

            Integer userId = userFinder.findByUserName(httpServletRequest.getUserPrincipal().getName()).getId();
            model.addAttribute("driverId", driverFinder.findByUserId(userId).getId());
            model.addAttribute("carId", carId);
            model.addAttribute("assignment", new DriverAccidentAssignment());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "cars/reportAccident";

    }

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/reportAccident")
    String reportAccident(@ModelAttribute DriverAccidentAssignment assignment) {
        driverService.reportAccident(assignment);
        return "redirect:/";

    }

}

