package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.dto.CreateUserAssignment;
import pl.sda.fleetmanagementsystem.model.Administrator;
import pl.sda.fleetmanagementsystem.model.Driver;
import pl.sda.fleetmanagementsystem.model.User;
import pl.sda.fleetmanagementsystem.model.UserRole;
import pl.sda.fleetmanagementsystem.repository.AdministratorRepository;
import pl.sda.fleetmanagementsystem.repository.DriverRepository;
import pl.sda.fleetmanagementsystem.repository.RoleRepository;
import pl.sda.fleetmanagementsystem.repository.UserRepository;

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
    private final UserRepository userRepository;
    private final AdministratorRepository administratorRepository;

    //TODO: obsłużyć wyjątek dotyczący próby użycia tego samego loginu

    public void register(CreateUserAssignment createUserAssignment) {

        User user = User.builder()
                .userName(createUserAssignment.getUserName())
                .password(passwordEncoder.encode(createUserAssignment.getPassword()))
                .roles(getUserRoles(createUserAssignment))
                .build();
        userRepository.save(user);

        switch (createUserAssignment.getRoleId()) {
            case 1:
                Driver driver =
                        Driver.builder()
                                .user(user)
                                .build();

                driverRepository.save(driver);
                break;
            case 2:
                Administrator administrator =
                        Administrator.builder()
                                .user(user)
                                .build();
                administratorRepository.save(administrator);
                break;
            default:
                System.out.println("No such role");
        }
    }

    private Set<UserRole> getUserRoles(CreateUserAssignment createUserAssignment) {
        Set<UserRole> roles = new HashSet<>();
        roles.add(roleRepository.findById(createUserAssignment.getRoleId()).orElseThrow(IllegalStateException::new));
        return roles;
    }

}
