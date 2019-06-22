package pl.sda.fleetmanagementsystem.dto;

import lombok.Data;

@Data
public class CreateUserAssignment {
    private String userName;
    private String password;
    private String email;
    private Integer roleId;
}
