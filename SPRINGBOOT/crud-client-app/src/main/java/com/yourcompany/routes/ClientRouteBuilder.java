package com.yourcompany.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ClientRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Consume data from the API using Apache Camel
        from("timer:apiTimer?period=5000")
            .setHeader("CamelHttpMethod", constant("GET"))
            .setHeader("Content-Type", constant("application/json"))
            .to("https://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY")
            .to("direct:processData");

        from("direct:processData")
            .log("Received data: ${body}")
            .to("direct:saveData");

        from("direct:saveData")
            .log("Saving data: ${body}");
    }
}
