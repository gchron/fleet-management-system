package pl.sda.fleetmanagementsystem.dto;

import lombok.*;
import pl.sda.fleetmanagementsystem.model.User;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String userName;
    private String password;
    private Set<UserRoleDto> userRoleDtos;

    public User toEntity() {
        return User.builder()
                .userName(userName)
                .password(password)
                .roles(userRoleDtos.stream().map(UserRoleDto::toEntity).collect(Collectors.toSet()))
                .build();
    }
}
