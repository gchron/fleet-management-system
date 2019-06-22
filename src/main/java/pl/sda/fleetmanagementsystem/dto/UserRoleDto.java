package pl.sda.fleetmanagementsystem.dto;

import lombok.*;
import pl.sda.fleetmanagementsystem.model.UserRole;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {
    private Integer id;
    private String role;

    public UserRole toEntity(){
        return UserRole.builder().
                role(role)
                .build();
    }
}
