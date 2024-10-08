package cl.colivares.kata.camel.HelloWorld;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class HelloWorld {

	public static void main(String[] args) throws Exception {
		try (CamelContext context = new DefaultCamelContext()) {
			context.addRoutes(new HelloWorldRoute());
			context.start();
		}
	}
}
