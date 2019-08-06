package pl.sda.fleetmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.fleetmanagementsystem.dto.PaymentDto;
import pl.sda.fleetmanagementsystem.model.Payment;
import pl.sda.fleetmanagementsystem.repository.PaymentRepository;

/**
 * @author Mariusz Kowalczuk
 */
@Service
@RequiredArgsConstructor
public class PaymentFinder {
    private final PaymentRepository paymentRepository;

    public PaymentDto findById(Integer id){
        return paymentRepository.findById(id).map(Payment::toDto).orElseThrow(IllegalStateException::new);

    }
}
