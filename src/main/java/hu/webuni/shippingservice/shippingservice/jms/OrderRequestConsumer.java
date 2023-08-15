package hu.webuni.shippingservice.shippingservice.jms;

import hu.webuni.jms.OrderRequest;
import hu.webuni.jms.OrderResponse;
import hu.webuni.shippingservice.shippingservice.xmlws.OrderXmlWs;
import jakarta.jms.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderRequestConsumer {

    private final JmsTemplate jmsTemplate;
    private final OrderXmlWs orderXmlWs;

    @JmsListener(destination = "shippingstatus")
    public void onEntrustShippingRequest(Message<OrderRequest> request) {
        Long orderId = request.getPayload().getOrderId();
        String shippingAddress = request.getPayload().getShippingAddress();
        List<String> products = request.getPayload().getProducts();
        String pickingUpAddress = request.getPayload().getPickingUpAddress();
        String orderStatus = orderXmlWs.entrustDeliveryOrder(orderId, shippingAddress, products, pickingUpAddress);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(orderId);
        orderResponse.setOrderStatus(orderStatus);
        String shippingId = UUID.randomUUID().toString();
        orderResponse.setShippingId(shippingId);
        //TODO: implementalni, hogy azonnal kuldjon shipmentId, es ez a metodus kesobb a szallitas eredmenyet jms-sel
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        jmsTemplate.convertAndSend(
                (Topic)request.getHeaders().get(JmsHeaders.REPLY_TO),
                orderResponse);
    }
}
