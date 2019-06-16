package pl.sda.fleetmanagementsystem.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.dto.DriverDto;
import pl.sda.fleetmanagementsystem.model.Driver;
import pl.sda.fleetmanagementsystem.repository.DriverRepository;

@AllArgsConstructor
@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public void create(DriverDto dto) {
        Driver driver = new Driver();
        driver.setId(dto.getId());
        driver.setUserName(dto.getUserName());
        driver.setPassword(dto.getPassword());
        driver.setDrivingLicense(dto.getDrivingLicense());
        driver.setCars(dto.getCars());
        driver.setBills(dto.getBills());

        driverRepository.save(driver);
    }
}
