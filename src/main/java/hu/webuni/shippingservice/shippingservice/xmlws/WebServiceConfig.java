package hu.webuni.shippingservice.shippingservice.xmlws;

import jakarta.xml.ws.Endpoint;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WebServiceConfig {

	private final Bus bus;
	private final OrderXmlWs orderXmlWs;
	
	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, orderXmlWs);
		endpoint.publish("/shipping");
		
		return endpoint;
	}
	
}
