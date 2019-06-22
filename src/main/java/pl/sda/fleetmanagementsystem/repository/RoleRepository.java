package pl.sda.fleetmanagementsystem.repository;

/**
 * @author Mariusz Kowalczuk
 */

import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;

public interface RoleRepository extends JpaRepository<Integer, Role> {

}
