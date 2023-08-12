package hu.webuni.shippingservice.shippingservice.xmlws;

import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface OrderXmlWs {

    String entrustDeliveryOrder(Long orderId, String shippingAddress, List<String> products, String pickingUpAddress);
}
