package hu.webuni.shippingservice.shippingservice.jms;

import hu.webuni.shippingservice.jms.dto.OrderRequest;
import hu.webuni.shippingservice.jms.dto.OrderResponse;
import hu.webuni.shippingservice.shippingservice.xmlws.OrderXmlWs;
import jakarta.jms.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderRequestConsumer {

    private final JmsTemplate jmsTemplate;
    private final OrderXmlWs orderXmlWs;

    @JmsListener(destination = "free_semester_requests")
    public void onFreeSemesterRequest(Message<OrderRequest> request) {
        Long orderId = request.getPayload().getOrderId();
        String shippingAddress = request.getPayload().getShippingAddress();
        List<String> products = request.getPayload().getProducts();
        String pickingUpAddress = request.getPayload().getPickingUpAddress();
        String orderStatus = orderXmlWs.entrustDeliveryOrder(orderId, shippingAddress, products, pickingUpAddress);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(orderId);
        orderResponse.setOrderStatus(orderStatus);

        jmsTemplate.convertAndSend(
                (Topic)request.getHeaders().get(JmsHeaders.REPLY_TO),
                orderResponse);
    }
}
