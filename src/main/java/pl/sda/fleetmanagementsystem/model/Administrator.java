package pl.sda.fleetmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.sda.fleetmanagementsystem.dto.AdministratorDto;

import javax.persistence.Entity;

/**
 * @author Mariusz Kowalczuk
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
public class Administrator extends User {

    public AdministratorDto toDto() {
        return AdministratorDto.builder()
                .id(super.getId())
                .userName(super.getUserName())
                .password(super.getPassword())
                .build();
    }
}
