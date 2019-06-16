package pl.sda.fleetmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.fleetmanagementsystem.dto.DriverDto;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver extends User {

    @OneToOne
    private DrivingLicense drivingLicense;

    @OneToMany
    private Set<Car> cars;

    @OneToMany
    private Set<PetrolBill> bills;

    public DriverDto toDto(){
        return DriverDto.builder().userName(getUserName()).cars(cars).drivingLicense(drivingLicense).build();
    }
}
