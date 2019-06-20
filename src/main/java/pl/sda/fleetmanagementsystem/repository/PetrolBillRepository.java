package pl.sda.fleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fleetmanagementsystem.model.PetrolBill;

import java.util.Set;

/**
 * @author Mariusz Kowalczuk
 */
public interface PetrolBillRepository extends JpaRepository<PetrolBill, Integer> {
    public Set<PetrolBill> findByDriver_Id(Integer driverId);

}
