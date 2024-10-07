package com.example.copilot.transaction.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelConfig extends RouteBuilder {
    @Override
    public void configure() {
        from("timer:trigger?period=10s")
                .log("Camel route triggered")
                .to("direct:processTransaction");

        from("direct:processTransaction")
                .log("Processing bank transaction")
                .bean("transactionProcessor", "process");
    }
}