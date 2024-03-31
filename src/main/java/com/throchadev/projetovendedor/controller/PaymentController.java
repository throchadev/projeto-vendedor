package com.throchadev.projetovendedor.controller;

import com.throchadev.projetovendedor.dto.PaymentDTO;
import com.throchadev.projetovendedor.usecase.ConfirmPaymentUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api")
public class PaymentController {

    private final ConfirmPaymentUseCase confirmPaymentUseCase;

    public PaymentController(ConfirmPaymentUseCase confirmPaymentUseCase) {
        this.confirmPaymentUseCase = confirmPaymentUseCase;
    }

    @PutMapping(path = "/payment")
    public ResponseEntity<?> setPayment(@RequestBody PaymentDTO request) {
        log.info("Request {}", request);
        return confirmPaymentUseCase.confirmSeller(request);
    }
}
