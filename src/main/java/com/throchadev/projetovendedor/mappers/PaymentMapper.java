package com.throchadev.projetovendedor.mappers;

import com.throchadev.projetovendedor.dto.PaymentItemDTO;
import com.throchadev.projetovendedor.model.Payment;
import com.throchadev.projetovendedor.model.PaymentMessage;
import org.springframework.beans.BeanUtils;

public class PaymentMapper {

    public static PaymentMessage dtoToMessage(PaymentItemDTO paymentItemDTO) {
        PaymentMessage paymentMessage = new PaymentMessage();
        BeanUtils.copyProperties(paymentItemDTO, paymentMessage);
        return paymentMessage;
    }

    public static Payment messageToEntity(PaymentMessage paymentMessage) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentMessage, payment);
        return payment;
    }
}
