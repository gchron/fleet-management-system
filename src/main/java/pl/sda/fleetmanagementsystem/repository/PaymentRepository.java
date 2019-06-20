package pl.sda.fleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fleetmanagementsystem.model.Payment;

/**
 * @author Mariusz Kowalczuk
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
