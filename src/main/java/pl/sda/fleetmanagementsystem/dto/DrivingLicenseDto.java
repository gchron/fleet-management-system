package pl.sda.fleetmanagementsystem.dto;

import lombok.*;
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

    public DrivingLicense toEntity() {
        return DrivingLicense
                .builder()
                .id(id)
                .expireDate(expireDate)
                .number(number)
                .build();
    }
}
