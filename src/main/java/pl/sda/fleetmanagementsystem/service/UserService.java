package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.dto.CreateUserAssignment;
import pl.sda.fleetmanagementsystem.model.*;
import pl.sda.fleetmanagementsystem.repository.*;

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
    private final ClientRepository clientRepository;


    public void register(CreateUserAssignment createUserAssignment){

        User user = User.builder()
                .userName(createUserAssignment.getUserName())
                .password(passwordEncoder.encode(createUserAssignment.getPassword()))
                .roles(getUserRoles(createUserAssignment))
                .build();
        try{

            userRepository.save(user);
        }
        catch (RuntimeException e){
            System.out.println(e.getStackTrace());
        }

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
            case 3:
                Client client =
                        Client.builder()
                                .user(user)
                                .build();
                clientRepository.save(client);
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
