package cl.colivares.kata.camel.consumerproducer;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ConsumerProducerExample {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		CamelContext context =  new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				from("direct:start")
				.to("seda:end");
			}
		});
		
		context.start();
		
		ProducerTemplate producer =  context.createProducerTemplate();
		producer.sendBody("direct:start", "HEllo everyone");
		
		ConsumerTemplate consumer = context.createConsumerTemplate();
		String response = consumer.receiveBody("seda:end", String.class);
		System.out.println(response);
	}
}
