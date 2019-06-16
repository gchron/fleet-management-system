package pl.sda.fleetmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.fleetmanagementsystem.model.DrivingLicense;

import java.time.LocalDate;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DrivingLicenseDto {
    private Integer id;
    private LocalDate expireDate;
    private String number;
    private DriverDto driverDto;

    public DrivingLicense toEntity() {
        return DrivingLicense
                .builder()
                .id(id)
                .expireDate(expireDate)
                .number(number)
                .driver(driverDto.toEntity())
                .build();
    }
}
