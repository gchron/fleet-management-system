package pl.sda.fleetmanagementsystem.dto;

import lombok.*;

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
}
