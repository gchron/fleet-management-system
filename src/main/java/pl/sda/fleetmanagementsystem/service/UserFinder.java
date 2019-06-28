package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.dto.UserDto;
import pl.sda.fleetmanagementsystem.model.User;
import pl.sda.fleetmanagementsystem.repository.UserRepository;

/**
 * @author Mariusz Kowalczuk
 */
@Service
@RequiredArgsConstructor
public class UserFinder {

    private final UserRepository userRepository;

    public UserDto findByUserName(String userName){
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Username with login "
                    + userName + " not found");
        }
        return user.toDto();

    }
}
