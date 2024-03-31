package com.throchadev.projetovendedor.usecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.throchadev.projetovendedor.dto.PaymentDTO;
import com.throchadev.projetovendedor.dto.PaymentItemDTO;
import com.throchadev.projetovendedor.message.Publisher;
import com.throchadev.projetovendedor.model.Payment;
import com.throchadev.projetovendedor.model.Seller;
import com.throchadev.projetovendedor.service.ConfirmPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ConfirmPaymentUseCaseTest {

    @Mock
    ConfirmPaymentService confirmPaymentService;
    @Mock
    Publisher publisher;
    @InjectMocks
    ConfirmPaymentUseCase confirmPaymentUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConfirmSellerAndConfirmPaymentIsOk() {

        var seller = new Seller();
        seller.setId(1L);
        seller.setName("test");

        var payment = new Payment();
        payment.setPaymentId("1");
        payment.setPaymentValue(BigDecimal.ONE);
        payment.setPaymentStatus("TOTAL");

        var paymentDTO = PaymentDTO.builder()
                .sellerCode("1")
                .paymentItems(List.of(PaymentItemDTO.builder()
                                .paymentId("1")
                                .paymentValue(BigDecimal.ONE)
                                .paymentStatus("TOTAL")
                        .build()))
                .build();

        when(confirmPaymentService.getSeller(anyString())).thenReturn(Optional.of(seller));
        when(confirmPaymentService.getPaymentItem(anyString())).thenReturn(Optional.of(payment));

        ResponseEntity<?> result = confirmPaymentUseCase.confirmSeller(paymentDTO);
        verify(publisher).publishMessageTotal(any());
        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
    }

    @Test
    void testConfirmSellerIsNotFound() {

        var paymentDTO = PaymentDTO.builder()
                .sellerCode("2")
                .paymentItems(List.of(PaymentItemDTO.builder()
                        .paymentId("1")
                        .paymentValue(BigDecimal.ONE)
                        .paymentStatus("TOTAL")
                        .build()))
                .build();

        when(confirmPaymentService.getSeller(anyString())).thenReturn(Optional.ofNullable(null));

        ResponseEntity<?> result = confirmPaymentUseCase.confirmSeller(paymentDTO);
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatusCode().value());
    }

    @Test
    void testConfirmPaymentIsNotFound() throws IOException {

        var seller = new Seller();
        seller.setId(1L);
        seller.setName("test");

        var paymentDTO = PaymentDTO.builder()
                .sellerCode("1")
                .paymentItems(List.of(PaymentItemDTO.builder()
                        .paymentId("1")
                        .paymentValue(BigDecimal.ONE)
                        .paymentStatus("TOTAL")
                        .build()))
                .build();

        when(confirmPaymentService.getSeller(anyString())).thenReturn(Optional.of(seller));
        when(confirmPaymentService.getPaymentItem(anyString())).thenReturn(Optional.ofNullable(null));

        ResponseEntity<?> result = confirmPaymentUseCase.confirmSeller(paymentDTO);

        final ObjectMapper mapper = new ObjectMapper();
        var jsonString = mapper.writeValueAsString(result.getBody());
        PaymentDTO person = mapper.readValue(jsonString, PaymentDTO.class);

        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
        assertEquals("Billing code not found", person.getPaymentItems().get(0).getMessage());
    }
}