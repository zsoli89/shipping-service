package hu.webuni.shippingservice.shippingservice.xmlws;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderXmlWsImpl implements OrderXmlWs {

    @Override
    public String entrustDeliveryOrder(Long orderId, String shippingAddress, List<String> products, String pickingUpAddress) {
        return null;
    }
}
