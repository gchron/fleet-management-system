package pl.sda.fleetmanagementsystem.model;

import pl.sda.fleetmanagementsystem.dto.TechnicalInspectionDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class TechnicalInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Car car;
    private LocalDate dateOfNextInspection;

    public TechnicalInspectionDto toDto() {
        return TechnicalInspectionDto
                .builder()
                .id(id)
                .carDto(car.toDto())
                .dateOfNextInspection(dateOfNextInspection)
                .build();
    }
}
