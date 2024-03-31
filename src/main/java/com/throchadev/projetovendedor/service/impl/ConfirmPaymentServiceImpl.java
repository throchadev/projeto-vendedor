package com.throchadev.projetovendedor.service.impl;

import com.throchadev.projetovendedor.model.Payment;
import com.throchadev.projetovendedor.model.Seller;
import com.throchadev.projetovendedor.repository.PaymentRepository;
import com.throchadev.projetovendedor.repository.SellerRepository;
import com.throchadev.projetovendedor.service.ConfirmPaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class ConfirmPaymentServiceImpl implements ConfirmPaymentService {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    public Optional<Seller> getSeller(String id) {
        log.info("Method getSeller id {}", id);
        var sellerCode = Long.parseLong(id);
        return sellerRepository.findById(sellerCode);
    }

    public Optional<Payment> getPaymentItem(String paymentId) {
        log.info("Method getPaymentItem id {}", paymentId);
        return paymentRepository.findById(paymentId);
    }

    public void updatePayment(Payment payment) {
        log.info("Method updatePayment {}", payment);
        paymentRepository.save(payment);
    }
}
