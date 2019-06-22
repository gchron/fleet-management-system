package pl.sda.fleetmanagementsystem.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.fleetmanagementsystem.dto.DriverDto;
import pl.sda.fleetmanagementsystem.dto.DriverLicenseAssignmentDto;
import pl.sda.fleetmanagementsystem.dto.DriverPetrolBillAssignmentDto;
import pl.sda.fleetmanagementsystem.model.Driver;
import pl.sda.fleetmanagementsystem.model.DrivingLicense;
import pl.sda.fleetmanagementsystem.model.PetrolBill;
import pl.sda.fleetmanagementsystem.repository.DriverRepository;
import pl.sda.fleetmanagementsystem.repository.DrivingLicenseRepository;
import pl.sda.fleetmanagementsystem.repository.PetrolBillRepository;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final DrivingLicenseRepository drivingLicenseRepository;
    private final PetrolBillRepository petrolBillRepository;

    public void create(DriverDto dto) {
        Driver driver = new Driver();
        driver.setId(dto.getId());
        driver.setDrivingLicense(dto.getDrivingLicenseDto()!= null? dto.getDrivingLicenseDto().toEntity():null);
//        driver.setCars(dto.getCarsDtos().stream().map(carDtos -> carDtos.toEntity()).collect(Collectors.toSet()));
//        driver.setBills(dto.getPetrolBillDtos().stream().map(pbBillDtos -> pbBillDtos.toEntity()).collect(Collectors.toSet()));

        driverRepository.save(driver);
    }
    @Transactional
    public void setDrivingLicense(Integer driverId, DriverLicenseAssignmentDto assignment){
        DrivingLicense drivingLicense = new DrivingLicense();
        Driver driver = driverRepository.findById(driverId).orElseThrow(IllegalArgumentException::new);
        drivingLicense.setExpireDate(LocalDate.parse(assignment.getExpireDate()));
        drivingLicense.setNumber(assignment.getNumber());
        drivingLicense.setDriver(driver);
        drivingLicenseRepository.save(drivingLicense);
        driver.setDrivingLicense(drivingLicense);

    }
    @Transactional
    public void addBill(DriverPetrolBillAssignmentDto assignment){
        PetrolBill petrolBill = new PetrolBill();
        Driver driver = driverRepository.findById(assignment.getDriverId()).orElseThrow(IllegalArgumentException::new);
        petrolBill.setDriver(driver);
        petrolBill.setDate(LocalDate.parse(assignment.getDate()));
        petrolBill.setValue(BigDecimal.valueOf(assignment.getValue()));
        petrolBillRepository.save(petrolBill);
        driver.getBills().add(petrolBill);


    }
}
