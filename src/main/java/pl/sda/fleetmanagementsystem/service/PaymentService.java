package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.dto.PetrolBillDriverAssignment;
import pl.sda.fleetmanagementsystem.model.Driver;
import pl.sda.fleetmanagementsystem.model.Payment;
import pl.sda.fleetmanagementsystem.repository.DriverRepository;
import pl.sda.fleetmanagementsystem.repository.PaymentRepository;

/**
 * @author Mariusz Kowalczuk
 */
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final DriverRepository driverRepository;

    public void create(PetrolBillDriverAssignment petrolBillAssignment){

        Driver driver = driverRepository.findById(petrolBillAssignment.getDriverId()).orElseThrow(IllegalArgumentException::new);
        Payment payment = new Payment();
        payment.setValue(petrolBillAssignment.getValue());
        payment.setDriver(driver);
        payment.setSettled(false);
        paymentRepository.save(payment);


    }
}
