package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.dto.UserDto;
import pl.sda.fleetmanagementsystem.model.Administrator;
import pl.sda.fleetmanagementsystem.model.Driver;
import pl.sda.fleetmanagementsystem.model.UserRole;
import pl.sda.fleetmanagementsystem.repository.AdministratorRepository;
import pl.sda.fleetmanagementsystem.repository.DriverRepository;
import pl.sda.fleetmanagementsystem.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final DriverRepository driverRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdministratorRepository administratorRepository;

    public void register(UserDto userDto) {
        switch (userDto.getRoleId()) {
            case 1:
                Driver driver =
                        Driver.builder()
                        .userName(userDto.getUserName())
                        .password(passwordEncoder.encode(userDto.getPassword()))
                        .roles(getUserRoles(userDto))
                        .build();

                driverRepository.save(driver);
                break;
            case 2:
                Administrator administrator =
                        Administrator.builder()
                                .userName(userDto.getUserName())
                                .password(passwordEncoder.encode(userDto.getPassword()))
                                .roles(getUserRoles(userDto))
                                .build();
                administratorRepository.save(administrator);
                break;


        }


    }

    private Set<UserRole> getUserRoles(UserDto userDto) {
        Set<UserRole> roles = new HashSet<>();
        roles.add(roleRepository.findById(userDto.getRoleId()).orElseThrow(IllegalStateException::new));
        return roles;
    }

}
