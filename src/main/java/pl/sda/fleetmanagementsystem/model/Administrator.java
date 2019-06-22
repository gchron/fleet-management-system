package pl.sda.fleetmanagementsystem.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.sda.fleetmanagementsystem.dto.AdministratorDto;

import javax.persistence.Entity;
import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
@Getter
@Setter

@Entity
public class Administrator extends User {
    public Administrator() {
    }

    @Builder
    public Administrator(Integer id, String userName, String password, Set<UserRole> roles) {
        super(id, userName, password, roles);
    }

    public AdministratorDto toDto() {
        return AdministratorDto.builder()
                .id(super.getId())
                .userName(super.getUserName())
                .password(super.getPassword())
                .build();
    }
}
