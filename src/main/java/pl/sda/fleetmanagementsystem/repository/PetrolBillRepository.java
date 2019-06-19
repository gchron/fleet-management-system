package pl.sda.fleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fleetmanagementsystem.model.PetrolBill;

/**
 * @author Mariusz Kowalczuk
 */
public interface PetrolBillRepository extends JpaRepository<PetrolBill, Integer> {
}
