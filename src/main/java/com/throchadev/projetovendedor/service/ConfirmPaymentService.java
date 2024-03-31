package com.throchadev.projetovendedor.service;

import com.throchadev.projetovendedor.model.Payment;
import com.throchadev.projetovendedor.model.Seller;

import java.util.Optional;
public interface ConfirmPaymentService {
    Optional<Seller> getSeller(String id) ;
    Optional<Payment> getPaymentItem(String paymentId);
    void updatePayment(Payment payment);
}
