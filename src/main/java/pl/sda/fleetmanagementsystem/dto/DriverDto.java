package pl.sda.fleetmanagementsystem.dto;

import lombok.*;
import pl.sda.fleetmanagementsystem.model.Driver;

import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {
    private Integer id;
    private String userName;
    private String password;
    private DrivingLicenseDto drivingLicenseDto;
    private Set<CarDto> carsDtos;
    private Set<PetrolBillDto> petrolBillDtos;


    public Driver toEntity() {
        Driver driver = new Driver();
        driver.setId(id);
        driver.setUserName(userName);
        driver.setDrivingLicense(drivingLicenseDto!=null?drivingLicenseDto.toEntity():null);
        return driver;
    }
}
