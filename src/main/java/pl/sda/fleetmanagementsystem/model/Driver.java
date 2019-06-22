package pl.sda.fleetmanagementsystem.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.fleetmanagementsystem.dto.DriverDto;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mariusz Kowalczuk
 */

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Driver extends User {
    public Driver(DrivingLicense drivingLicense, Set<Car> cars, Set<PetrolBill> bills) {
        this.drivingLicense = drivingLicense;
        this.cars = cars;
        this.bills = bills;
    }

    @Builder
    public Driver(Integer id, String userName, String password, Set<UserRole> roles, DrivingLicense drivingLicense, Set<Car> cars, Set<PetrolBill> bills) {
        super(id, userName, password, roles);
        this.drivingLicense = drivingLicense;
        this.cars = cars;
        this.bills = bills;
    }

    @OneToOne
    private DrivingLicense drivingLicense;

    @OneToMany
    private Set<Car> cars;

    @OneToMany
    private Set<PetrolBill> bills;

    public DriverDto toDto(){
        return DriverDto.builder()
                .id(getId())
                .userName(getUserName())
                .password(getPassword())
                .drivingLicenseDto(drivingLicense != null? drivingLicense.toDto():null)
                .carsDtos(cars.stream().map(car -> car.toDto()).collect(Collectors.toSet()))
                .build();


    }

}
