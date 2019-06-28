package pl.sda.fleetmanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.fleetmanagementsystem.dto.DriverLicenseAssignmentDto;
import pl.sda.fleetmanagementsystem.dto.DriverPetrolBillAssignmentDto;
import pl.sda.fleetmanagementsystem.dto.PetrolBillDriverAssignment;
import pl.sda.fleetmanagementsystem.service.*;

import javax.servlet.http.HttpServletRequest;

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
    private final UserFinder userFinder;


    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @RequestMapping({"", "/"})
    ModelAndView showAllDrivers() {
        ModelAndView modelAndView = new ModelAndView("drivers/index.html");
        modelAndView.addObject("drivers", driverFinder.findAll());
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/{id}")
    ModelAndView showUser(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("drivers/main.html");
        modelAndView.addObject("driver", driverFinder.findById(id));
        return modelAndView;

    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/setDrivingLicense")
    String setDrivingLicense(HttpServletRequest httpServletRequest, Model model) {
        try {

            Integer id = userFinder.findByUserName(httpServletRequest.getUserPrincipal().getName()).getId();
            model.addAttribute("userId", id);
            model.addAttribute("assignment", new DriverLicenseAssignmentDto());

        } catch (RuntimeException e) {
            e.printStackTrace();
            return "redirect:/error";

        }
        return "drivers/setDrivingLicense";
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/setDrivingLicense")
    String setDrivingLicense(@ModelAttribute DriverLicenseAssignmentDto assignment) {

        driverService.setDrivingLicense(assignment);
        return "redirect:/";

    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/{id}/showCars")
    ModelAndView showCarsOfDriver(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("drivers/showCars.html");
        modelAndView.addObject("cars", carFinder.findByDriverId(id));
        return modelAndView;
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/cars/details/{carId}")
    ModelAndView showCarDetails(@PathVariable Integer carId) {
        ModelAndView modelAndView = new ModelAndView("cars/details.html");
        modelAndView.addObject("car", carFinder.findById(carId));
        return modelAndView;
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/addBill")
    String addBill(HttpServletRequest httpServletRequest, Model model) {
        try {

            Integer id = userFinder.findByUserName(httpServletRequest.getUserPrincipal().getName()).getId();
            model.addAttribute("userId", id);
            model.addAttribute("assignment", new DriverPetrolBillAssignmentDto());

        } catch (RuntimeException e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "drivers/addBill";
    }


    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/addBill")
    String addBill(@ModelAttribute DriverPetrolBillAssignmentDto assignmentDto) {
        driverService.addBill(assignmentDto);
        return "redirect:/";
    }

    //TODO: Tutaj przydałoby się zmienić na listę do wyboru, którego drivera chcemy rozliczyć
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/{driverId}/preparePayment")
    ModelAndView preparePayment(@PathVariable Integer driverId) {

        ModelAndView modelAndView = new ModelAndView("drivers/preparePayment.html");
        modelAndView.addObject("value", petrolBillService.computeValue(petrolBillFinder.findUnsettledBillofDriver(driverId)));
        modelAndView.addObject("driverId", driverId);
        modelAndView.addObject("payment", new PetrolBillDriverAssignment());
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/preparePayment")
    String preparePayment(@ModelAttribute PetrolBillDriverAssignment assignment) {
        paymentService.create(assignment);
        return "redirect:/";

    }


}
