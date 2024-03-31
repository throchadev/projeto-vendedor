package com.throchadev.projetovendedor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "payment_id")
    private String paymentId;
    @Column(name = "payment_value")
    private BigDecimal paymentValue;
    @Column(name = "payment_status")
    private String paymentStatus;
}
