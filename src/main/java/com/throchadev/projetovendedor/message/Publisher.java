package com.throchadev.projetovendedor.message;

import com.throchadev.projetovendedor.model.PaymentMessage;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class Publisher {

    @Autowired
    private SqsTemplate sqsTemplate;

    @Value("${spring.cloud.aws.sqs.queues.payment-total}")
    private String queuePaymentTotal;

    @Value("${spring.cloud.aws.sqs.queues.payment-surplus}")
    private String queuePaymentSurplus;

    @Value("${spring.cloud.aws.sqs.queues.payment-partial}")
    private String queuePaymentPartial;

    public void publishMessageTotal(PaymentMessage message) {
        log.info("Send message queue {}", queuePaymentTotal);
        try {
            sqsTemplate.send(queuePaymentTotal, message);
        } catch (Exception e) {
            log.error("JsonProcessingException e : {} and stacktrace : {}", e.getMessage(), e);
        }
    }

    public void publishMessageSurplus(PaymentMessage message) {
        log.info("Send message queue {}", queuePaymentSurplus);
        try {
            sqsTemplate.send(queuePaymentSurplus, message);
        } catch (Exception e) {
            log.error("JsonProcessingException e : {} and stacktrace : {}", e.getMessage(), e);
        }
    }

    public void publishMessagePartial(PaymentMessage message) {
        log.info("Send message queue {}", queuePaymentPartial);
        try {
            sqsTemplate.send(queuePaymentPartial, message);
        } catch (Exception e) {
            log.error("JsonProcessingException e : {} and stacktrace : {}", e.getMessage(), e);
        }
    }
}
