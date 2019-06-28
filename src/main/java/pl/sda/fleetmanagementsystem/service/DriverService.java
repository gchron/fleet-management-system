package pl.sda.fleetmanagementsystem.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.fleetmanagementsystem.dto.DriverLicenseAssignmentDto;
import pl.sda.fleetmanagementsystem.dto.DriverPetrolBillAssignmentDto;
import pl.sda.fleetmanagementsystem.model.Driver;
import pl.sda.fleetmanagementsystem.model.DrivingLicense;
import pl.sda.fleetmanagementsystem.model.PetrolBill;
import pl.sda.fleetmanagementsystem.repository.DriverRepository;
import pl.sda.fleetmanagementsystem.repository.DrivingLicenseRepository;
import pl.sda.fleetmanagementsystem.repository.PetrolBillRepository;
import pl.sda.fleetmanagementsystem.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final DrivingLicenseRepository drivingLicenseRepository;
    private final PetrolBillRepository petrolBillRepository;
    private final UserRepository userRepository;


    @Transactional
    public void setDrivingLicense(DriverLicenseAssignmentDto assignment){
        DrivingLicense drivingLicense = new DrivingLicense();

        Driver driver = driverRepository.findByUserId(getId(assignment.getUserId())).orElseThrow(IllegalArgumentException::new);
        drivingLicense.setExpireDate(LocalDate.parse(assignment.getExpireDate()));
        drivingLicense.setNumber(assignment.getNumber());
        drivingLicense.setDriver(driver);
        drivingLicenseRepository.save(drivingLicense);
        //driver.setDrivingLicense(drivingLicense);

    }


    @Transactional
    public void addBill(DriverPetrolBillAssignmentDto assignment){
        PetrolBill petrolBill = new PetrolBill();
        Driver driver = driverRepository.findByUserId(getId(assignment.getUserId())).orElseThrow(IllegalArgumentException::new);
        petrolBill.setDriver(driver);
        petrolBill.setDate(LocalDate.parse(assignment.getDate()));
        petrolBill.setValue(BigDecimal.valueOf(assignment.getValue()));
        petrolBillRepository.save(petrolBill);
        //driver.getBills().add(petrolBill);


    }
    private Integer getId(Integer userId) {
        return userRepository.findById(userId).orElseThrow(IllegalAccessError::new).toDto().getId();
    }
}
