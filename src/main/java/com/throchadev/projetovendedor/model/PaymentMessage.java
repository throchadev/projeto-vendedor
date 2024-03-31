package com.throchadev.projetovendedor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMessage {

    private String paymentId;
    private BigDecimal paymentValue;
    private String paymentStatus;
}
