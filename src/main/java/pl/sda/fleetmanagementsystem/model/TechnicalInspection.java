package pl.sda.fleetmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.sda.fleetmanagementsystem.dto.TechnicalInspectionDto;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
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
