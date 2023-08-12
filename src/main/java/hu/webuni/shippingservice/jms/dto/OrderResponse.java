package hu.webuni.shippingservice.jms.dto;

import lombok.Data;

@Data
public class OrderResponse {
    private Long orderId;
    private String orderStatus;
}
