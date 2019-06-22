package pl.sda.fleetmanagementsystem.model;

import lombok.*;
import pl.sda.fleetmanagementsystem.dto.UserDto;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mariusz Kowalczuk
 */

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 32)
    private String userName;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    public UserDto toDto(){
        return UserDto.builder()
                .id(id)
                .password(password)
                .userName(userName)
                .userRoleDtos(roles.stream().map(UserRole::toDto).collect(Collectors.toSet()))
                .build();
    }
}

