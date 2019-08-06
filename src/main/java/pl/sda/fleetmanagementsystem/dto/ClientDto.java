package pl.sda.fleetmanagementsystem.dto;

import lombok.*;
import pl.sda.fleetmanagementsystem.model.Client;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Integer id;
    private UserDto userDto;

    public Client toEntity(){
        return Client
                .builder()
                .user(userDto.toEntity())
                .build();
    }
}
