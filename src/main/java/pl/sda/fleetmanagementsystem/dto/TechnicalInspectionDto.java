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
public class TechnicalInspectionDto {
    private Integer id;
    private CarDto carDto;
    private LocalDate dateOfNextInspection;

}
