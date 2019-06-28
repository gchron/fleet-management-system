package pl.sda.fleetmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.sda.fleetmanagementsystem.model.User;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mariusz Kowalczuk
 */
@Builder
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRoleDto> getUserRoleDtos() {
        return userRoleDtos;
    }

    public void setUserRoleDtos(Set<UserRoleDto> userRoleDtos) {
        this.userRoleDtos = userRoleDtos;
    }
}
