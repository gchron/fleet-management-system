package pl.sda.fleetmanagementsystem.dto;

import lombok.*;
import pl.sda.fleetmanagementsystem.model.Administrator;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorDto {

    private Integer id;
    private UserDto userDto;

    public Administrator toEntity(){
        return Administrator
                .builder()
                .user(userDto.toEntity())
                .build();
    }
}
