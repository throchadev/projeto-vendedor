package com.throchadev.projetovendedor.service.impl;

import com.throchadev.projetovendedor.model.Payment;
import com.throchadev.projetovendedor.model.Seller;
import com.throchadev.projetovendedor.repository.PaymentRepository;
import com.throchadev.projetovendedor.repository.SellerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ConfirmPaymentServiceImplTest {

    @Mock
    SellerRepository sellerRepository;
    @Mock
    PaymentRepository paymentRepository;
    @InjectMocks
    ConfirmPaymentServiceImpl confirmPaymentServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar um Seller")
    void testGetSeller() {

        var seller = new Seller();
        seller.setId(1L);
        seller.setName("test");

        when(sellerRepository.findById(any())).thenReturn(Optional.of(seller));

        Optional<Seller> result = confirmPaymentServiceImpl.getSeller("1");
        Assertions.assertEquals(seller, result.get());
    }

    @Test
    void testGetPaymentItem() {

        var payment = new Payment();
        payment.setPaymentId("1");
        payment.setPaymentValue(BigDecimal.ONE);
        payment.setPaymentStatus("TOTAL");

        when(paymentRepository.findById(any())).thenReturn(Optional.of(payment));

        Optional<Payment> result = confirmPaymentServiceImpl.getPaymentItem("paymentId");
        Assertions.assertEquals(payment, result.get());
    }

    @Test
    void testUpdatePayment() {

        var payment = new Payment();
        payment.setPaymentId("1");
        payment.setPaymentValue(BigDecimal.ONE);
        payment.setPaymentStatus("TOTAL");

        when(paymentRepository.save(any())).thenReturn(null);

        confirmPaymentServiceImpl.updatePayment(payment);

    }
}