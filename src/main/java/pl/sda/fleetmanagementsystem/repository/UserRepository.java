package pl.sda.fleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fleetmanagementsystem.model.Driver;
import pl.sda.fleetmanagementsystem.model.User;

public interface UserRepository extends JpaRepository<Driver, Integer> {

    User findByUserName(String name);
}
