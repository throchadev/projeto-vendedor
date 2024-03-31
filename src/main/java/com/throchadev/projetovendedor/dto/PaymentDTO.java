package com.throchadev.projetovendedor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO {

    @JsonProperty("seller_code")
    private String sellerCode;
    @JsonProperty("payment_items")
    private List<PaymentItemDTO> paymentItems;
    @JsonProperty("error")
    private String error;
}
