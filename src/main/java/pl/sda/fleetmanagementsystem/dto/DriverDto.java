package pl.sda.fleetmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
        return Driver.builder()
                .id(id)
                .userName(userName)
                .drivingLicense(drivingLicenseDto.toEntity())
                .build();
    }
}
