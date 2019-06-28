package pl.sda.fleetmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.sda.fleetmanagementsystem.model.Driver;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mariusz Kowalczuk
 */
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {
    private Integer id;
    private UserDto userDto;
    private DrivingLicenseDto drivingLicenseDto;
    private Set<CarDto> carsDtos;
    private Set<PetrolBillDto> petrolBillDtos;


    public Driver toEntity() {
        return Driver.builder()
                .user(userDto != null ? userDto.toEntity() : null)
                .drivingLicense(drivingLicenseDto != null ? drivingLicenseDto.toEntity() : null)
                .cars(Optional.ofNullable(carsDtos).map(Collection::stream).orElseGet(Stream::empty).map(CarDto::toEntity).collect(Collectors.toSet()))
                .bills(Optional.ofNullable(petrolBillDtos).map(Collection::stream).orElseGet(Stream::empty).map(PetrolBillDto::toEntity).collect(Collectors.toSet()))
                .build();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public DrivingLicenseDto getDrivingLicenseDto() {
        return drivingLicenseDto;
    }

    public void setDrivingLicenseDto(DrivingLicenseDto drivingLicenseDto) {
        this.drivingLicenseDto = drivingLicenseDto;
    }

    public Set<CarDto> getCarsDtos() {
        return carsDtos;
    }

    public void setCarsDtos(Set<CarDto> carsDtos) {
        this.carsDtos = carsDtos;
    }

    public Set<PetrolBillDto> getPetrolBillDtos() {
        return petrolBillDtos;
    }

    public void setPetrolBillDtos(Set<PetrolBillDto> petrolBillDtos) {
        this.petrolBillDtos = petrolBillDtos;
    }
}
