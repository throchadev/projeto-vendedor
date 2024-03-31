package com.throchadev.projetovendedor.message;

import com.throchadev.projetovendedor.mappers.PaymentMapper;
import com.throchadev.projetovendedor.model.PaymentMessage;
import com.throchadev.projetovendedor.service.ConfirmPaymentService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class Consumer {

    @Autowired
    private ConfirmPaymentService confirmPaymentService;

    @SqsListener("queuePaymentTotal")
    public void consumeMessageTotal(PaymentMessage paymentMessage) {
        log.info("Message from SQS {}", paymentMessage);
        System.out.println(paymentMessage);
        var payment = PaymentMapper.messageToEntity(paymentMessage);
        confirmPaymentService.updatePayment(payment);
    }

    @SqsListener("queuePaymentSurplus")
    public void consumeMessageSurplus(PaymentMessage paymentMessage) {
        log.info("Message from SQS {}", paymentMessage);
        System.out.println(paymentMessage);
        var payment = PaymentMapper.messageToEntity(paymentMessage);
        confirmPaymentService.updatePayment(payment);
    }

    @SqsListener("queuePaymentPartial")
    public void consumeMessagePartial(PaymentMessage paymentMessage) {
        log.info("Message from SQS {}", paymentMessage);
        System.out.println(paymentMessage);
        var payment = PaymentMapper.messageToEntity(paymentMessage);
        confirmPaymentService.updatePayment(payment);
    }
}
