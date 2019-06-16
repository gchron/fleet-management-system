package pl.sda.fleetmanagementsystem.dto;

import lombok.*;
import pl.sda.fleetmanagementsystem.model.CarAccident;

import java.time.LocalDate;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarAccidentDto {

    private Integer id;
    private CarDto carDto;
    private DriverDto driverDto;
    private LocalDate accidentDate;
    private String description;

    public CarAccident toEntity() {
        return CarAccident.builder()
                .car(carDto.toEntity())
                .driver(driverDto.toEntity())
                .accidentDate(accidentDate)
                .description(description)
                .build();

    }
}
