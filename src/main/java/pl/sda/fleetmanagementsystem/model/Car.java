package pl.sda.fleetmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.sda.fleetmanagementsystem.dto.CarDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@RequiredArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brand;
    private String model;
    private String productionYear;
    private String mileage;
    private Double engineCapacity;
    private LocalDate dateOfNextInspection;

    @ManyToOne
    private Driver driver;

    @OneToMany
    private Set<CarAccident> carAccidents;

    public CarDto toDto() {
        return CarDto.builder()
                .id(id)
                .brand(brand)
                .model(model)
                .productionYear(productionYear)
                .mileage(mileage)
                .engineCapacity(engineCapacity)
                .dateOfNextInspection(dateOfNextInspection)
                .driverDto(driver != null? driver.toDto():null)
                .carAccidentsDtos(
                        carAccidents
                        .stream()
                        .map(carAccident -> carAccident.toDto())
                                .collect(Collectors.toSet()))
                .build();

    }
}
