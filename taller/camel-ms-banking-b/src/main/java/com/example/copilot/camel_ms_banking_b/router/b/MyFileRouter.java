package com.example.copilot.camel_ms_banking_b.router.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .to("file:files/output");
    }

}
