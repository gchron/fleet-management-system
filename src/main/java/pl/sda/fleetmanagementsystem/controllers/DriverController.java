package pl.sda.fleetmanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.fleetmanagementsystem.dto.*;
import pl.sda.fleetmanagementsystem.service.*;

/**
 * @author Mariusz Kowalczuk
 */
@Controller
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverFinder driverFinder;
    private final DriverService driverService;
    private final CarFinder carFinder;
    private final PetrolBillFinder petrolBillFinder;
    private final PetrolBillService petrolBillService;
    private final PaymentService paymentService;

    @RequestMapping({"", "/"})
    ModelAndView showAllDrivers() {

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

        ModelAndView modelAndView = new ModelAndView("drivers/setDrivingLicense.html");
        modelAndView.addObject("driverId", id);
        modelAndView.addObject("assignment", new DriverLicenseAssignmentDto());
        return modelAndView;
    }

    @PostMapping("/setDrivingLicense")
    String setDrivingLicense(@ModelAttribute DriverLicenseAssignmentDto assignment) {

        driverService.setDrivingLicense(Integer.valueOf(assignment.getDriverId()), assignment);
        return "redirect:/drivers";

    }

    @GetMapping("/{id}/showCars")
    ModelAndView showCarsOfDriver(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("drivers/showCars.html");
        modelAndView.addObject("cars", carFinder.findByDriverId(id));
        return modelAndView;
    }

    @GetMapping("/cars/details/{carId}")
    ModelAndView showCarDetails(@PathVariable Integer carId) {
        ModelAndView modelAndView = new ModelAndView("cars/details.html");
        modelAndView.addObject("car", carFinder.findById(carId));
        return modelAndView;
    }

    @GetMapping("/{id}/addBill")
    ModelAndView addBill(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("drivers/addBill.html");
        modelAndView.addObject("bill", new DriverPetrolBillAssignmentDto());
        modelAndView.addObject("driverId", id);

        return modelAndView;
    }

    @PostMapping("/addBill")
    String addBill(@ModelAttribute DriverPetrolBillAssignmentDto assignmentDto) {
        driverService.addBill(assignmentDto);
        return "redirect:/drivers";
    }

    @GetMapping("/{driverId}/preparePayment")
    ModelAndView preparePayment(@PathVariable Integer driverId) {

        ModelAndView modelAndView = new ModelAndView("drivers/preparePayment.html");
        modelAndView.addObject("value", petrolBillService.computeValue(petrolBillFinder.findUnsettledBillofDriver(driverId)));
        modelAndView.addObject("driverId", driverId);
        modelAndView.addObject("payment", new PetrolBillDriverAssignment());
        return modelAndView;
    }
    @PostMapping("/preparePayment")
    String preparePayment(@ModelAttribute PetrolBillDriverAssignment assignment){
        paymentService.create(assignment);
        return "redirect:/drivers";

    }




}
