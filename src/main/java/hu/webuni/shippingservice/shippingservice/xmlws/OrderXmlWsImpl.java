package hu.webuni.shippingservice.shippingservice.xmlws;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderXmlWsImpl implements OrderXmlWs {

    @Override
    public String entrustDeliveryOrder(Long orderId, String shippingAddress, List<String> products, String pickingUpAddress) {
        String[] options = {"SHIPMENT_FAILED", "DELIVERED"};
        int random = (int) (Math.random() * options.length);
        String response = options[random];
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
