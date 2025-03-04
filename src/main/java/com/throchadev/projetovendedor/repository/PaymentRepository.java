package com.throchadev.projetovendedor.repository;

import com.throchadev.projetovendedor.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, String> {
}
