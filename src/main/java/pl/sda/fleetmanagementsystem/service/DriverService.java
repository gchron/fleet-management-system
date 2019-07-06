package pl.sda.fleetmanagementsystem.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.fleetmanagementsystem.dto.DriverAccidentAssignment;
import pl.sda.fleetmanagementsystem.dto.DriverLicenseAssignmentDto;
import pl.sda.fleetmanagementsystem.dto.DriverPetrolBillAssignmentDto;
import pl.sda.fleetmanagementsystem.model.*;
import pl.sda.fleetmanagementsystem.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final DrivingLicenseRepository drivingLicenseRepository;
    private final PetrolBillRepository petrolBillRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final CarAccidentRepository carAccidentRepository;


    @Transactional
    public void setDrivingLicense(DriverLicenseAssignmentDto assignment){


        DrivingLicense drivingLicense = new DrivingLicense();

        Driver driver = driverRepository.findByUserId(getId(assignment.getUserId())).orElseThrow(IllegalArgumentException::new);
        drivingLicense.setId(assignment.getId());
        drivingLicense.setExpireDate(LocalDate.parse(assignment.getExpireDate()));
        drivingLicense.setNumber(assignment.getNumber());
        drivingLicense.setDriver(driver);
        drivingLicenseRepository.save(drivingLicense);
        driver.setDrivingLicense(drivingLicense);

    }


    @Transactional
    public void addBill(DriverPetrolBillAssignmentDto assignment){
        PetrolBill petrolBill = new PetrolBill();
        Driver driver = driverRepository.findByUserId(getId(assignment.getUserId())).orElseThrow(IllegalArgumentException::new);
        petrolBill.setDriver(driver);
        petrolBill.setDate(LocalDate.parse(assignment.getDate()));
        petrolBill.setValue(BigDecimal.valueOf(assignment.getValue()));
        petrolBillRepository.save(petrolBill);
        driver.getBills().add(petrolBill);


    }
    protected Integer getId(Integer userId) {
        return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new).toDto().getId();
    }

    @Transactional
    public void reportAccident(DriverAccidentAssignment assignment) {
        CarAccident carAccident = new CarAccident();
        Driver driver = driverRepository.findById(assignment.getDriverId()).orElseThrow(IllegalArgumentException::new);
        Car car = carRepository.findById(assignment.getCarId()).orElseThrow(IllegalArgumentException::new);
        carAccident.setCar(car);
        carAccident.setDriver(driver);
        carAccident.setAccidentDate(LocalDate.parse(assignment.getAccidentDate()));
        carAccident.setDescription(assignment.getDescription());
        carAccident.setSettled(false);
        car.getCarAccidents().add(carAccident);
        carAccidentRepository.save(carAccident);




    }
}
