package com.throchadev.projetovendedor.usecase;

import com.throchadev.projetovendedor.dto.PaymentDTO;
import com.throchadev.projetovendedor.dto.PaymentItemDTO;
import com.throchadev.projetovendedor.dto.SellerDTO;
import com.throchadev.projetovendedor.enums.PaymentStatus;
import com.throchadev.projetovendedor.mappers.PaymentMapper;
import com.throchadev.projetovendedor.message.Publisher;
import com.throchadev.projetovendedor.service.ConfirmPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfirmPaymentUseCase {

    @Autowired
    private ConfirmPaymentService confirmPaymentService;

    @Autowired
    private Publisher publisher;

    public ResponseEntity<?> confirmSeller(PaymentDTO request) {

        var seller = confirmPaymentService.getSeller(request.getSellerCode());
        if (seller.isPresent()) {
            return confirmPayment(request);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SellerDTO.builder().sellerCode(request.getSellerCode()).message("Seller does not exist").build());
    }

    public ResponseEntity<?> confirmPayment(PaymentDTO request) {

        List<PaymentItemDTO> itemDTOS = new ArrayList<>();
        for (PaymentItemDTO paymentItem : request.getPaymentItems()) {
            var payment = confirmPaymentService.getPaymentItem(paymentItem.getPaymentId());
            if(payment.isEmpty()) {
                var paymentItemDTO = PaymentItemDTO.builder()
                        .paymentId(paymentItem.getPaymentId())
                        .paymentValue(paymentItem.getPaymentValue())
                        .paymentStatus(paymentItem.getPaymentStatus())
                        .message("Billing code not found")
                        .build();
                itemDTOS.add(paymentItemDTO);
            } else {
                var validatePayment = paymentItem.getPaymentValue().compareTo(payment.get().getPaymentValue());
                if (validatePayment == 0) {
                    paymentItem.setPaymentStatus(PaymentStatus.TOTAL.name());
                    var message = PaymentMapper.dtoToMessage(paymentItem);
                    publisher.publishMessageTotal(message);
                } else if (validatePayment > 0) {
                    paymentItem.setPaymentStatus(PaymentStatus.SURPLUS.name());
                    var message = PaymentMapper.dtoToMessage(paymentItem);
                    publisher.publishMessageSurplus(message);
                } else {
                    paymentItem.setPaymentStatus(PaymentStatus.PARTIAL.name());
                    var message = PaymentMapper.dtoToMessage(paymentItem);
                    publisher.publishMessagePartial(message);
                }
                var paymentItemDTO = PaymentItemDTO.builder()
                        .paymentId(paymentItem.getPaymentId())
                        .paymentValue(paymentItem.getPaymentValue())
                        .paymentStatus(paymentItem.getPaymentStatus())
                        .build();
                itemDTOS.add(paymentItemDTO);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(PaymentDTO.builder().sellerCode(request.getSellerCode()).paymentItems(itemDTOS).build());
    }
}
