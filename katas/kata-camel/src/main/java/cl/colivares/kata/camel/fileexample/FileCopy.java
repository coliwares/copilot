package cl.colivares.kata.camel.fileexample;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileCopy {

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("file:input_box?noop=true").to("file:output_box");

			}
		});
		
		while (true) {
			context.start();
		}
	}
}
