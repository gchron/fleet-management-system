package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.dto.DriverDto;
import pl.sda.fleetmanagementsystem.dto.DrivingLicenseDto;
import pl.sda.fleetmanagementsystem.model.DrivingLicense;
import pl.sda.fleetmanagementsystem.repository.DrivingLicenseRepository;

/**
 * @author Mariusz Kowalczuk
 */
@Service
@RequiredArgsConstructor
public class DrivingLicenseFinder {

    private final DrivingLicenseRepository drivingLicenseRepository;
    private final DriverService driverService;
    private final DriverFinder driverFinder;

    public DrivingLicenseDto getDrivingLicenseOfUser(Integer userID) {
        Integer driverId = driverService.getId(userID);
        DriverDto driverDto = driverFinder.findByUserId(userID);
        DrivingLicense drivingLicense = drivingLicenseRepository.findByDriver_Id(driverDto.getId()).orElseGet(DrivingLicense::new);
        DrivingLicenseDto drivingLicenseDto = new DrivingLicenseDto();
        drivingLicenseDto.setId(drivingLicense.getId());
        drivingLicenseDto.setExpireDate(drivingLicense.getExpireDate());
        drivingLicenseDto.setNumber(drivingLicense.getNumber());
        return drivingLicenseDto;


    }
}
