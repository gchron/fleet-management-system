package pl.sda.fleetmanagementsystem.dto;

import lombok.*;
import pl.sda.fleetmanagementsystem.model.TechnicalInspection;

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

    public TechnicalInspection toEntity() {
        return TechnicalInspection.builder()
                .car(carDto.toEntity())
                .dateOfNextInspection(dateOfNextInspection)
                .build();

    }
}
